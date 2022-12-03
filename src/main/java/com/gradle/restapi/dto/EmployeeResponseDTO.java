package com.gradle.restapi.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gradle.restapi.constant.EmployeeShift;
import com.gradle.restapi.constant.EmployeeType;
import com.gradle.restapi.entities.Employees;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponseDTO {

    private Integer id;
    private String fullName;
    private String email;
    private String mobile;
    private String fatherMobile;
    private String address;
    @Enumerated(EnumType.STRING)
    private EmployeeType employeeType;
    @Enumerated(EnumType.STRING)
    private EmployeeShift employeeShift;
    private BigDecimal diamondPrice;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date created;
    @JsonIgnore
    public static EmployeeResponseDTO fromEmployee(Employees employee) {
        return EmployeeResponseDTO.builder()
                .id(employee.getId())
                .fullName(employee.getFullName())
                .mobile(employee.getMobile())
                .email(employee.getEmail())
                .fatherMobile(employee.getFatherMobile())
                .address(employee.getAddress())
                .employeeType(employee.getEmployeeType())
                .employeeShift(employee.getEmployeeShift())
                .diamondPrice(employee.getDiamondPrice())
                .created(employee.getCreated())
                .build();
    }
}
