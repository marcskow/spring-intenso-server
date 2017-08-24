package com.marcskow.springserver.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marcskow.springserver.model.todo.TodoItem;
import com.marcskow.springserver.model.todo.TodoList;
import com.marcskow.springserver.model.todo.TodoProject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TodoRestService {

    public ResponseEntity<String> todoRestCall() {
        return todoRestCall("");
    }

    public ResponseEntity<String> todoRestCall(String token) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("token", token);
        map.add("sync_token","*");
        map.add("resource_types","[\"all\"]");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        return restTemplate.postForEntity("https://todoist.com/api/v7/sync", request, String.class);
    }

    public TodoList buildTodoList(String content) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        TodoList todoList = mapper.readValue(content, TodoList.class);

        TodoItem[] todoItems = todoList.getTodoItems();
        List<TodoItem> items;
        for(TodoProject project : todoList.getTodoProjects()) {
            items = new ArrayList<>();
            for(TodoItem item : todoItems) {
                if(item.getProjectId() == project.getId()) {
                    items.add(item);
                }
            }
            project.setItems(items);
        }

        return todoList;
    }
}
