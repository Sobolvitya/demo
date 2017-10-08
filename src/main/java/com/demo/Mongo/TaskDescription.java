package com.demo.Mongo;


import lombok.AllArgsConstructor;
import lombok.Data;

import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
public class TaskDescription {
    @Id
    private String id;
    private String description;
}
