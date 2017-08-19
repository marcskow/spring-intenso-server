package com.marcskow.springserver.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "notes")
public class Note {
    @Id
    private String id;
    private String content;
}
