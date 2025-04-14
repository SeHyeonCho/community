package project.community.service.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import project.community.dto.AiResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OpenAiService {

    @Value("${openai.api.key}")
    private String apiKey;
    @Value("${openai.api.url}")
    private String url;
    @Value("${openai.model}")
    private String model;

    private final RestTemplate restTemplate;

    public OpenAiService() {
        restTemplate = new RestTemplate();
    }

    public String getChatResponse(String prompt) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> body = new HashMap<>();
        body.put("model", model);
        body.put("messages", List.of(
                Map.of(
                        "role", "user",
                        "content", prompt)
                )
        );
        body.put("max_tokens", 100);

        HttpEntity<Map<String, Object>> httpEntity = new HttpEntity<>(body, headers);


        ResponseEntity<AiResponse> response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, AiResponse.class);

        return response.getBody().getContent();
    }

}
