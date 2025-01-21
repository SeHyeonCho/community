package project.community.dto;

import lombok.Data;

@Data
public class ErrorData {
    private String error;
    private String message;

    public ErrorData(String error, String message) {
        this.error = error;
        this.message = message;
    }
}
