package org.ahmedukamel.mailesender.model;

import lombok.Getter;

import java.util.List;

@Getter
public enum Template {
    BIONIMAROC_ORDER(List.of("fullName", "phone", "address"),
            new String[]{"xtech.official.eg@gmail.com"},
            "Order from BIONIMAROC", "bionimaroc-order"),

    VITA_CONTACT_US(List.of("name", "email", "message"),
            new String[]{"xtech.official.eg@gmail.com"},
            "Message from Vita Para Pharma", "vita-message"),

    VITA_ACTIVATE_ACCOUNT(List.of("code", "link", "expiration", "receivers"),
            null, "Activate Vitaparapharma Account", "vita-activate-account"),

    VITA_FORGET_PASSWORD(List.of("code", "link", "expiration", "receivers"),
            null, "Forget Vitaparapharma Account Password", "vita-forget-password"),

    VITA_PASSWORD_CHANGED(List.of("requested", "changed", "receivers"),
            null, "Password for Vitaparapharma Account Changed", "vita-password-changed");

    private final List<String> attributes;
    private final String[] receivers;
    private final String subject;
    private final String view;

    Template(List<String> attributes, String[] receivers, String subject, String view) {
        this.attributes = attributes;
        this.receivers = receivers;
        this.subject = subject;
        this.view = view;
    }
}
