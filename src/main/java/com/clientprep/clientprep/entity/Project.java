package com.clientprep.clientprep.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name; // e.g., "Email Marketing Setup"
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User createdBy; // The freelancer

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<FormField> formFields;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<FormResponse> formResponses;
    
}
