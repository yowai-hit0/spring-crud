package com.project.springApp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    private T data;
    private String message;
    private HttpStatus status;

    public ResponseEntity<ApiResponse<T>> toResponseEntity(){
        assert this.status != null;
        return ResponseEntity.status(this.status).body(this);
    }
}
