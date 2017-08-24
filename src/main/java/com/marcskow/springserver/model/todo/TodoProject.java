package com.marcskow.springserver.model.todo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonProperty.Access;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TodoProject {

    @JsonProperty("id")
    private long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty(value = "items", access = Access.READ_ONLY)
    private TodoItem[] todoItems;

    public void setItems(List<TodoItem> itemsList) {
        todoItems = itemsList.toArray(new TodoItem[itemsList.size()]);
    }
}