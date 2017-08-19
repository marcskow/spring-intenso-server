package com.marcskow.springserver.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marcskow.springserver.model.todo.TodoList;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
public class TodoRestService {

    public ResponseEntity<String> todoRestCall() {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("token", "currentlyfakewillbeprovidedfromsomewhere");
        map.add("sync_token","*");
        map.add("resource_types","[\"projects\"]");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        return restTemplate.postForEntity("https://todoist.com/api/v7/sync", request, String.class);
    }

    public TodoList buildTodoList(String content) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(content, TodoList.class);
    }
}
