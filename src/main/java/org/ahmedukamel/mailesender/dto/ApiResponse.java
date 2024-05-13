package org.ahmedukamel.mailesender.dto;

public record ApiResponse(boolean success, String message, Object data) {
}
