package org.ahmedukamel.mailesender.controller;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.ahmedukamel.mailesender.dto.ApiResponse;
import org.ahmedukamel.mailesender.model.Template;
import org.ahmedukamel.mailesender.service.MailSenderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/v1/email-template")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class MailController {
    private final MailSenderService service;

    @GetMapping(value = "all")
    public ResponseEntity<ApiResponse> getTemplates() {
        Map<String, ?> data = Arrays.stream(Template.values()).collect(Collectors.toMap(Template::name, Template::getAttributes));
        ApiResponse response = new ApiResponse(true, "Messages Templates", data);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @PostMapping(value = "{template}")
    public ResponseEntity<ApiResponse> sendEmail(
            @PathVariable(value = "template") String templateName,
            @RequestBody Map<String, ?> attributes) throws MessagingException {
        ApiResponse response = service.process(templateName, attributes);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
