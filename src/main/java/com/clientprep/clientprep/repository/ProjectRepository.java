package com.clientprep.clientprep.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clientprep.clientprep.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    
}
