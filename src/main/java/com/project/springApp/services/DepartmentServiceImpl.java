package com.project.springApp.services;

import com.project.springApp.exceptions.DepartmentNotFoundException;
import com.project.springApp.models.Department;
import com.project.springApp.repositories.DepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService{
    @Autowired
    private DepartmentRepo departmentRepo;
    @Override
    public Department saveDepartment(Department department) {
        return departmentRepo.save(department);
    }

    @Override
    public List<Department> getDepartmentList() {
        return departmentRepo.findAll();
    }

    @Override
    public Department getDepartmentById(Long departmentId) throws DepartmentNotFoundException {
        return departmentRepo.findById(departmentId)
                .orElseThrow(()->new DepartmentNotFoundException("Department not found with ID: " + departmentId));
    }

    @Override
    public void deleteDepartmentById(Long departmentId) throws DepartmentNotFoundException{
        if(!departmentRepo.existsById(departmentId)){
            throw new DepartmentNotFoundException("Department not found with ID: " + departmentId);
        }
        departmentRepo.deleteById(departmentId);
    }

    @Override
    public Department updateDepartmentById(Long departmentId, Department department) throws DepartmentNotFoundException {
        Department departDb = departmentRepo.findById(departmentId).orElseThrow(()->new DepartmentNotFoundException("Department not found with ID: " + departmentId));
        if(Objects.nonNull(department.getDepartmentName()) && !"".equalsIgnoreCase(department.getDepartmentName())){
            departDb.setDepartmentName(department.getDepartmentName());
        }
        if(Objects.nonNull(department.getDepartmentCode()) && !"".equalsIgnoreCase(department.getDepartmentCode())){
            departDb.setDepartmentCode(department.getDepartmentCode());
        }
        if(Objects.nonNull(department.getDepartmentAddress()) && !"".equalsIgnoreCase(department.getDepartmentAddress())){
            departDb.setDepartmentAddress(department.getDepartmentAddress());
        }
        return departmentRepo.save(departDb);
    }

    @Override
    public Department getDepartmentByName(String departmentName) {
        return departmentRepo.findByDepartmentNameIgnoreCase(departmentName);
    }
}
