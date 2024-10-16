package com.example.taskmanagement.adapter.inbound.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TaskRequest {
    @NotBlank(message = "title is required.")
    @Size(max=100, message = "Please enter the title within 100 characters")
    private String title;

    @Size(max=500, message = "Please enter the description within 500 characters")
    private String description;

    @Size(max=100, message = "Please enter the status within 100 characters")
    private String status;

    @Size(max=100, message = "Please enter the assignee within 100 characters")
    private String assignee;

    private boolean completed;
}
