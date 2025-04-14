package project.community.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import project.community.service.api.OpenAiService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ai")
public class OpenAiController {
    private final OpenAiService openAiService;


    @PostMapping
    public String answerQuestion(@RequestBody String prompt) {
        return openAiService.getChatResponse(prompt);
    }

}
