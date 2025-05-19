package com.clientprep.clientprep.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.clientprep.clientprep.entity.FormResponse;
import com.clientprep.clientprep.entity.Project;
import com.clientprep.clientprep.repository.FormResponseRepository;
import com.clientprep.clientprep.repository.ProjectRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FormResponseService {
    private final FormResponseRepository formResponseRepository;
    private final ProjectRepository projectRepository;

    public void submitResponses(Long projectId, List<FormResponse> responses) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        for (FormResponse response : responses) {
            response.setProject(project);
        }

        formResponseRepository.saveAll(responses);
    }

    public List<FormResponse> getResponses(Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        return project.getFormResponses();
    }

}
