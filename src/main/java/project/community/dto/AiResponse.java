package project.community.dto;


import lombok.Data;

import java.util.List;

@Data
public class AiResponse {

    List<choice> choices;

    public AiResponse(List<choice> choices) {
        this.choices = choices;
    }

    @Data
    public static class choice {
        private int index;
        private Message message;

    }

    @Data
    public static class Message {
        private String content;
    }

    public String getContent() {
        return choices.get(0).getMessage().getContent();
    }
}
