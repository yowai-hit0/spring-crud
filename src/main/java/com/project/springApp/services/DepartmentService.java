package com.project.springApp.services;

import com.project.springApp.exceptions.DepartmentNotFoundException;
import com.project.springApp.models.Department;
import org.springframework.stereotype.Service;

import java.util.List;

public interface DepartmentService {
    Department saveDepartment(Department department);

    List<Department> getDepartmentList();

    Department getDepartmentById(Long departmentId) throws DepartmentNotFoundException;

    void deleteDepartmentById(Long departmentId) throws DepartmentNotFoundException;

    Department updateDepartmentById(Long departmentId, Department department) throws DepartmentNotFoundException;

    Department getDepartmentByName(String departmentName);
}
