package com.gradle.restapi.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.gradle.restapi.constant.EmployeeShift;
import com.gradle.restapi.constant.EmployeeType;
import com.gradle.restapi.entities.Employees;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeRequestDTO {

    @NotNull
    @NotEmpty(message = "Please enter your full name")
    @Size(min = 3,max= 256, message = "employee full name should have at least 3 characters")
    private String fullName;

    @NotNull
    @NotEmpty
    @Size(max=64)
    @Email(message = "Please enter a valid e-mail address")
    private String email;

    @NotNull
    @NotEmpty(message="Please enter your mobile number")
    @Size(min = 10, max = 10)
    private String mobile;

    @NotNull
    @NotEmpty(message="Please enter your father's mobile number")
    @Size(min = 10, max = 10)
    private String fatherMobile;

    @NotNull
    @Size(max=256)
    @NotEmpty(message="Please enter your address")
    private String address;

    @NotNull
    @Enumerated(EnumType.STRING)
    private EmployeeType employeeType;

    @NotNull
    @Enumerated(EnumType.STRING)
    private EmployeeShift employeeShift;

    @NotNull
    private BigDecimal diamondPrice;

    @JsonIgnore
    public static Employees fromEmployeeDTO(EmployeeRequestDTO employeeRequestDTO) {
        return Employees.builder()
                .fullName(employeeRequestDTO.getFullName())
                .email(employeeRequestDTO.getEmail())
                .mobile(employeeRequestDTO.getMobile())
                .fatherMobile(employeeRequestDTO.getFatherMobile())
                .address(employeeRequestDTO.getAddress())
                .employeeType(employeeRequestDTO.getEmployeeType())
                .employeeShift(employeeRequestDTO.getEmployeeShift())
                .diamondPrice(employeeRequestDTO.getDiamondPrice())
                .build();
    }

}
