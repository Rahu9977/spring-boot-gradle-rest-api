package com.gradle.restapi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gradle.restapi.constant.EmployeeShift;
import com.gradle.restapi.constant.EmployeeType;
import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
@Entity
@DynamicInsert
@DynamicUpdate
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE,region = "employee")
@Where(clause = "deleted <> '1'")
@Table(name="employees")
@NoArgsConstructor
public class Employees {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    @Column(columnDefinition = "BIGINT(20)")
    private Integer id;

    @NotNull
    @NotEmpty
    @Column(name = "full_name", columnDefinition = "VARCHAR(256)")
    private String fullName;

    @NotNull
    @NotEmpty
    @Column(name = "email", columnDefinition = "VARCHAR(64)")
    private String email;

    @NotNull
    @NotEmpty
    @Column(name = "mobile", columnDefinition = "VARCHAR(15)")
    private String mobile;

    @NotNull
    @NotEmpty
    @Column(name = "father_mobile", columnDefinition = "VARCHAR(15)")
    private String fatherMobile;

    @NotNull
    @NotEmpty
    @Column(name = "address", columnDefinition = "VARCHAR(256)")
    private String address;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "employee_type", columnDefinition = "VARCHAR(64)")
    private EmployeeType employeeType;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "employee_shift", columnDefinition = "VARCHAR(64)")
    private EmployeeShift employeeShift;

    @NotNull
    @Column(name = "diamond_price", nullable = false)
    private BigDecimal diamondPrice;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created",insertable = false,updatable = false,columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP")
    private Date created;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated",columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP")
    private Date updated;

    @JsonIgnore
    @Column(name = "deleted", nullable = false,columnDefinition = "BIT(1) default '0'")
    private boolean deleted;

    @Column(name = "created_by",columnDefinition = "BIGINT(20) default 0 ")
    private Integer createdBy;

    @Column(name = "updated_by",columnDefinition = "BIGINT(20) default 0 ")
    private Integer updatedBy;
}
