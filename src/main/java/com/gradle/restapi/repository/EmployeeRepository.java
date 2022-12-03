package com.gradle.restapi.repository;

import com.gradle.restapi.entities.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employees,Integer> {

    @QueryHints(value = {
            @QueryHint(name="org.hibernate.cacheable",value="true"),
            @QueryHint(name="org.hibernate.cacheRegion",value="employee")
    })
    Optional<Employees> findById(@Param("id") Integer id);

    @Query("SELECT r FROM Employees r WHERE r.email=:email AND r.id <> :empId ")
    Optional<Employees> checkByEmailAndIdForUpdate(@Param("email") String email,@Param("empId") Integer id);

    @Query("SELECT l FROM Employees l WHERE l.mobile = :mobile AND id <>:empId")
    Optional<Employees> checkByMobileAndIdForUpdate(@Param("mobile") String email,@Param("empId") Integer id);

    Optional<Employees> findByEmail(@Param("email") String email);

    Optional<Employees> findByMobile(@Param("mobile") String mobile);
}
