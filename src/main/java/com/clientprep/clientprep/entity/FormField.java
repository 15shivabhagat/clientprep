package com.clientprep.clientprep.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "form_field")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FormField {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question;

    @Enumerated(EnumType.STRING)
    private FieldType type; // e.g., TEXT, FILE, DATE

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
}
