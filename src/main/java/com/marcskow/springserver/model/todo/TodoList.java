package com.marcskow.springserver.model.todo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TodoList {
    @JsonProperty("projects")
    private Todo[] todoList;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Todo {
        @JsonProperty("id")
        private long id;
        @JsonProperty("name")
        private String name;
    }
}
