package com.marcskow.springserver.model.todo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TodoItem {

    @JsonProperty("id")
    private long id;

    @JsonProperty(value = "project_id", access = JsonProperty.Access.WRITE_ONLY)
    private long projectId;

    @JsonProperty("content")
    private String content;
}