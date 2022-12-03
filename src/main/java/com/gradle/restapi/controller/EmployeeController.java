package com.gradle.restapi.controller;

import com.gradle.restapi.dto.EmployeeRequestDTO;
import com.gradle.restapi.dto.EmployeeResponseDTO;
import com.gradle.restapi.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

//    @Secured({"ROLE_ADMINISTRATOR","ROLE_EMPLOYEE_ADD"})
    @PostMapping
    public ResponseEntity<EmployeeResponseDTO> save(@Valid @RequestBody EmployeeRequestDTO employeeRequestDTO) {
        return ResponseEntity.ok(employeeService.save(employeeRequestDTO));
    }
//    @Secured({"ROLE_ADMINISTRATOR","ROLE_EMPLOYEE_UPDATE"})
    @PutMapping("{id}")
    public ResponseEntity<EmployeeResponseDTO> update(@Valid @RequestBody EmployeeRequestDTO employeeRequestDTO, @PathVariable Integer id) {
        return ResponseEntity.ok(employeeService.update(id,employeeRequestDTO));
    }
//    @Secured({"ROLE_ADMINISTRATOR","ROLE_EMPLOYEE_GET"})
    @GetMapping
    public ResponseEntity<List<EmployeeResponseDTO>> findAll(@RequestParam(name = "page", defaultValue = "0") int page,
                                                             @RequestParam(name = "size", defaultValue = "10") int size) {
        var pageRequest = PageRequest.of(page,size);
        return ResponseEntity.ok(employeeService.findAllEmployee(pageRequest));
    }

//    @Secured({"ROLE_ADMINISTRATOR","ROLE_EMPLOYEE_GET"})
    @GetMapping("{id}")
    public ResponseEntity<EmployeeResponseDTO> findById(@PathVariable Integer id) {
        return  ResponseEntity.ok(employeeService.findById(id));
    }
//    @Secured({"ROLE_ADMINISTRATOR","ROLE_EMPLOYEE_DELETE"})
    @DeleteMapping("{id}")
    public ResponseEntity<Void> enableDisableEmployee(@PathVariable("id") Integer id,
                                                      @RequestParam boolean isDeleted){
        employeeService.softDeleteById(id,isDeleted);
        return ResponseEntity.ok().build();
    }
}

