package com.marcskow.springserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

import static com.fasterxml.jackson.annotation.JsonProperty.Access;

@Getter
@Setter
@Document(collection = "tasks")
public class Task {
    @Id
    private String id;

    @NotNull
    @NotEmpty
    private String title;

    private String description;

    @JsonProperty(access = Access.READ_ONLY)
    private String addedDate;

    private String deadline;

    private boolean finished;
}
