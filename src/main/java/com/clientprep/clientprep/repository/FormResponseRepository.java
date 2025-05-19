package com.clientprep.clientprep.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clientprep.clientprep.entity.FormResponse;

public interface FormResponseRepository extends JpaRepository<FormResponse, Long> {
    
}
