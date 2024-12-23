package com.project.springApp.repositories;

import com.project.springApp.models.Department;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepo extends JpaRepository<Department, Long> {
    public Department findByDepartmentName(String departmentName);
//    @Query(value = "",nativeQuery = true)
    public Department findByDepartmentNameIgnoreCase(String departmentCode);
}
