package com.clientprep.clientprep.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clientprep.clientprep.dto.ProjectRequest;
import com.clientprep.clientprep.entity.Project;
import com.clientprep.clientprep.entity.User;
import com.clientprep.clientprep.repository.UserRepository;
import com.clientprep.clientprep.service.ProjectService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;
    private final UserRepository userRepository;

    @PostMapping
    public ResponseEntity<Project> createProject(
            @RequestBody ProjectRequest projectRequest,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        User createdBy = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Project project = new Project();
        project.setName(projectRequest.getName());
        project.setDescription(projectRequest.getDescription());

        Project saved = projectService.createProject(project, projectRequest.getQuestions(), createdBy);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getProject(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.getProject(id));
    }


}
