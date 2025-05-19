package com.clientprep.clientprep.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clientprep.clientprep.entity.FormField;

public interface FormFieldRepository extends JpaRepository<FormField, Long>{
  
} 