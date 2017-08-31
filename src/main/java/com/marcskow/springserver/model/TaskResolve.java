package com.marcskow.springserver.model;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class TaskResolve {
    @Id
    private String id;

    @NotNull
    @NotEmpty
    private boolean resolved;
}