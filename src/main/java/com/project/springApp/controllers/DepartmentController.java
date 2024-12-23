package com.project.springApp.controllers;
import com.project.springApp.exceptions.DepartmentNotFoundException;
import com.project.springApp.models.ApiResponse;
import com.project.springApp.models.Department;
import com.project.springApp.services.DepartmentService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/depart")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;
    @PostMapping
    public ResponseEntity<ApiResponse<Department>> saveDepartment(@Valid @RequestBody Department department){
//        log.info("saved a department using lombok slf4j"); // configure logback.xml to change where logs are saved (console, file)
        log.info("Saving department: {}", department.getDepartmentName());
        Department response = departmentService.saveDepartment(department);
        return new ApiResponse<>(response,"Department created successfully", HttpStatus.CREATED).toResponseEntity();
    }
    @GetMapping
    public ResponseEntity<ApiResponse<List<Department>>> getAllDepartment(){
        log.info("Retrieving all departments ");
        List<Department> response =  departmentService.getDepartmentList();
        return new ApiResponse<>(response, "retrieved departments successfully", HttpStatus.OK).toResponseEntity();

    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Department>> getDepartmentById(@PathVariable("id") Long departmentId) throws DepartmentNotFoundException {
        log.info("Retrieving department by ID: {}", departmentId);
        Department response =  departmentService.getDepartmentById(departmentId);
        return new ApiResponse<>(response,"department create successfully", HttpStatus.OK ).toResponseEntity();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> deleteDepartment(@PathVariable("id") Long departmentId) throws DepartmentNotFoundException {
        log.info("Deleting department with ID: {}", departmentId);
        departmentService.deleteDepartmentById(departmentId);
        return new ApiResponse<>(null, "Department deleted successfully", HttpStatus.NO_CONTENT).toResponseEntity();  // Empty response body for DELETE
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Department>> updateDepartment(@PathVariable("id") Long departmentId, @RequestBody Department department) throws DepartmentNotFoundException{
        Department response =  departmentService.updateDepartmentById(departmentId,department);
        log.info("Updating department with ID: {}", departmentId);
        return new ApiResponse<>(response, "department updated succesfuflly", HttpStatus.OK).toResponseEntity();
    }
    @GetMapping("filter/")
    public ResponseEntity<ApiResponse<Department>> getDepartmentByName(@RequestParam("name") String departmentName){
        Department  response = departmentService.getDepartmentByName(departmentName);
        log.info("Retrieving department by name: {}", departmentName);
        return new ApiResponse<>(response,"department retrieved successfully", HttpStatus.OK).toResponseEntity();
    }
}
