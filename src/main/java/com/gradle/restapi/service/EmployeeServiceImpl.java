package com.gradle.restapi.service;

import com.gradle.restapi.dto.EmployeeRequestDTO;
import com.gradle.restapi.dto.EmployeeResponseDTO;
import com.gradle.restapi.entities.Employees;
import com.gradle.restapi.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public EmployeeResponseDTO save(EmployeeRequestDTO employeeRequestDTO) {
        Employees employee = EmployeeRequestDTO.fromEmployeeDTO(employeeRequestDTO);

        employeeRepository.findByEmail(employeeRequestDTO.getEmail()).ifPresent(d -> {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Employee Email Id  : '%s' Already Exist !..", employeeRequestDTO.getEmail()));
        });

        employeeRepository.findByMobile(employeeRequestDTO.getMobile()).ifPresent(d -> {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Employee Mobile Number : %s Already Exist !..", employeeRequestDTO.getMobile()));
        });

        return Optional.of(employeeRepository.save(employee))
                .map(EmployeeResponseDTO::fromEmployee)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Employee Not Saved")
                );
    }

    @Override
    public EmployeeResponseDTO update(Integer id,EmployeeRequestDTO employeeRequestDTO) {
        employeeRepository.findById(id).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND,"Employee not found")
        );

        employeeRepository.checkByEmailAndIdForUpdate(employeeRequestDTO.getEmail(),id).ifPresent(p->{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,String.format("Employee Email Id : %s Already Exist !..",employeeRequestDTO.getEmail()));
        });

        employeeRepository.checkByMobileAndIdForUpdate(employeeRequestDTO.getMobile(),id).ifPresent(p->{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,String.format("Employee Mobile Number : %s Already Exist !..",employeeRequestDTO.getMobile()));
        });

        Employees employeeUpdate = EmployeeRequestDTO.fromEmployeeDTO(employeeRequestDTO);
        employeeUpdate.setId(id);
        return Optional.of(employeeRepository.saveAndFlush(employeeUpdate)).map(EmployeeResponseDTO::fromEmployee)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Employee "+id+" not updated")
                );
    }

    @Transactional(readOnly = true)
    @Override
    public List<EmployeeResponseDTO> findAllEmployee(PageRequest pageRequest) {
        var employeeList = employeeRepository.findAll(pageRequest);
        return employeeList.stream().map(EmployeeResponseDTO::fromEmployee).collect(Collectors.toList());
    }

    @Override
    public EmployeeResponseDTO findById(Integer id) {
        return employeeRepository.findById(id).map(EmployeeResponseDTO::fromEmployee).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee Not Found")
        );
    }
    @Override
    public void softDeleteById(Integer id,boolean isDeleted){
        employeeRepository.findById(id)
                .map(emp->{
                    emp.setDeleted(isDeleted);
                    return employeeRepository.save(emp);
                }).orElseThrow(()->{
                    log.error(String.format("Employee Id '%s' Not Found ",id));
                    return new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Employee Id '%s' Not Found ",id));
                });
    }
}

