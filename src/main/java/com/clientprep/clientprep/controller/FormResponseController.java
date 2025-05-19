package com.clientprep.clientprep.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clientprep.clientprep.entity.FormResponse;
import com.clientprep.clientprep.service.FormResponseService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/forms")
@RequiredArgsConstructor
public class FormResponseController {
    private final FormResponseService formResponseService;

    @PostMapping("/{projectId}/submit")
    public ResponseEntity<String> submitResponses(
            @PathVariable Long projectId,
            @RequestBody List<FormResponse> responses
    ) {
        formResponseService.submitResponses(projectId, responses);
        return ResponseEntity.ok("Responses submitted successfully.");
    }

    @GetMapping("/{projectId}/responses")
    public ResponseEntity<List<FormResponse>> getResponses(@PathVariable Long projectId) {
        return ResponseEntity.ok(formResponseService.getResponses(projectId));
    }
}
