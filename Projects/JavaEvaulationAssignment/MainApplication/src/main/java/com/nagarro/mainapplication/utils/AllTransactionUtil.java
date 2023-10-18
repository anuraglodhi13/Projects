package com.nagarro.mainapplication.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class AllTransactionUtil {
    ObjectMapper objectMapper = new ObjectMapper();
    public List<Object> getAll(String accountNumber, String status) {
        WebClient webClient = WebClient.create();

        String url1 = "http://localhost:8081/backendserver1/pending/"+accountNumber;;
        String url2 = "http://localhost:8082/backendserver2/success/"+accountNumber;;
        String url3 = "http://localhost:8083/backendserver3/failure/"+accountNumber;

        CompletableFuture<Mono<String>> response1Future = CompletableFuture.supplyAsync(() -> webClient.get()
                .uri(url1)
                .retrieve()
                .bodyToMono(String.class));

        CompletableFuture<Mono<String>> response2Future = CompletableFuture.supplyAsync(() -> webClient.get()
                .uri(url2)
                .retrieve()
                .bodyToMono(String.class));

        CompletableFuture<Mono<String>> response3Future = CompletableFuture.supplyAsync(() -> webClient.get()
                .uri(url3)
                .retrieve()
                .bodyToMono(String.class));

        CompletableFuture<Void> allResponsesFuture = CompletableFuture.allOf(response1Future, response2Future, response3Future);

        allResponsesFuture.join();

        String[] responses = new String[3];
        try {
            responses[0] = response1Future.join().blockOptional().orElse("");
            responses[1] = response2Future.join().blockOptional().orElse("");
            responses[2] = response3Future.join().blockOptional().orElse("");
            List<Object> combinedResponses = Stream.of(parseData(responses[0],"pending"), parseData(responses[1],"success"), parseData(responses[2],"failure"))
                    .collect(Collectors.toList());

            return combinedResponses;
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            return null;
        }
    }

    public JsonNode parseData(String response,String status) throws JsonProcessingException {

        JsonNode responseJson = objectMapper.readTree(response);
        JsonNode dataNode = responseJson.get("data");

        return dataNode;

    }
}
