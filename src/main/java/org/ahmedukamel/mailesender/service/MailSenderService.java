package org.ahmedukamel.mailesender.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.ahmedukamel.mailesender.dto.ApiResponse;
import org.ahmedukamel.mailesender.exception.CustomException;
import org.ahmedukamel.mailesender.model.Template;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class MailSenderService {
    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    public MailSenderService(JavaMailSender javaMailSender, TemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    @Value(value = "spring.mail.username")
    private String from;

    public ApiResponse process(String templateName, Map<String, ?> attributes) throws MessagingException {
        Template template = getTemplate(templateName);
        validateAttributes(template, attributes);

        send(template, attributes);
        return new ApiResponse(true, "Email sent successfully.", null);
    }

    private void send(Template template, Map<String, ?> attributes) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom(from);
        helper.setSubject(template.getSubject());

        if (attributes.containsKey("receivers")) {
            helper.setTo(((List<String>) attributes.get("receivers")).toArray(new String[0]));
        } else {
            helper.setTo(template.getReceivers());
        }

        Context context = new Context();
        for (String attribute : template.getAttributes()) {
            context.setVariable(attribute, attributes.get(attribute));
        }

        String content = templateEngine.process(template.getView(), context);
        helper.setText(content, true);

        javaMailSender.send(message);
    }

    private Template getTemplate(String templateName) {
        return Template.valueOf(templateName.toUpperCase());
    }

    private void validateAttributes(Template template, Map<String, ?> attributes) {
        List<String> errorList = new ArrayList<>();
        template.getAttributes().forEach(attribute -> {
            if (!attributes.containsKey(attribute)) {
                errorList.add(attribute + ": not found!");
            }

            Object value = attributes.get(attribute);
            if (value == null) {
                errorList.add(attribute + ": cannot be null!");
            }

            if (value instanceof String str && str.isBlank()) {
                errorList.add(attribute + ": cannot be blank!");
            }
        });

        if (errorList.isEmpty()) {
            return;
        }

        throw new CustomException("Invalid request body.", errorList);
    }
}