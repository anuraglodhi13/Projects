package com.nagarro.mainapplication.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class SuccessTransactionUtil {
    ObjectMapper objectMapper = new ObjectMapper();
    public JsonNode getSuccess(String accountNumber) throws JsonProcessingException {
        WebClient webClient = WebClient.create();

        String url = "http://localhost:8082/backendserver2/success/"+accountNumber;

        String response = webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        JsonNode parsedData = parseData(response);
        return parsedData;
    }
    public JsonNode parseData(String response) throws JsonProcessingException {

        JsonNode responseJson = objectMapper.readTree(response);
        JsonNode dataNode = responseJson.get("data");

        return dataNode;

    }
}
