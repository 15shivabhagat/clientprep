package com.clientprep.clientprep.dto;

import java.util.List;

import com.clientprep.clientprep.entity.FormField;

import lombok.Data;

@Data
public class ProjectRequest {
    private String name;
    private String description;
    private List<FormField> questions;
}
