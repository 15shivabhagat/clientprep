package com.clientprep.clientprep.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.clientprep.clientprep.entity.FormField;
import com.clientprep.clientprep.entity.Project;
import com.clientprep.clientprep.entity.User;
import com.clientprep.clientprep.repository.FormFieldRepository;
import com.clientprep.clientprep.repository.ProjectRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final FormFieldRepository formFieldRepository;

    public Project createProject(Project project, List<FormField> questions, User createdBy) {
        project.setCreatedBy(createdBy);
        Project savedProject = projectRepository.save(project);

        for (FormField question : questions) {
            question.setProject(savedProject);
        }

        formFieldRepository.saveAll(questions);
        savedProject.setFormFields(questions);
        return savedProject;
    }

    public Project getProject(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
    }
}
