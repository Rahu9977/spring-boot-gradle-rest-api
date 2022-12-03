package com.gradle.restapi.service;

import com.gradle.restapi.dto.EmployeeRequestDTO;
import com.gradle.restapi.dto.EmployeeResponseDTO;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface EmployeeService {

    EmployeeResponseDTO save(EmployeeRequestDTO employeeRequestDTO);
    EmployeeResponseDTO update(Integer id,EmployeeRequestDTO employeeRequestDTO);
    List<EmployeeResponseDTO> findAllEmployee(PageRequest pageRequest);
    EmployeeResponseDTO findById(Integer id);
    void softDeleteById(Integer id,boolean isDeleted);
}
