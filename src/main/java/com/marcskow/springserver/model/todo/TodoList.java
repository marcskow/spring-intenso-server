package com.marcskow.springserver.model.todo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import static com.fasterxml.jackson.annotation.JsonProperty.Access;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TodoList {

    @JsonProperty("sync_token")
    private String syncToken;

    @JsonProperty("projects")
    private TodoProject[] todoProjects;

    @JsonProperty(value = "items", access = Access.WRITE_ONLY)
    private TodoItem[] todoItems;
}
