package com.insightlab.sgpo.controllers;

import com.insightlab.sgpo.data.dtos.v1.security.UserResponseDTO;
import com.insightlab.sgpo.data.dtos.v1.security.UserUpdateRequestDTO;
import com.insightlab.sgpo.data.dtos.v1.security.UserUpdateResponseDTO;
import com.insightlab.sgpo.services.UserService;
import com.insightlab.sgpo.utils.swagger.UserControllerSwaggerOptions;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "User", description = "Endpoints for managing users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    @UserControllerSwaggerOptions.GetAllUsersOptions
    public ResponseEntity<List<UserResponseDTO>> getAllUsers(){
        List<UserResponseDTO> usersList = this.userService.getAllUsers();

        return ResponseEntity.ok().body(usersList);
    }

    @PutMapping(value = "/{id}")
    @UserControllerSwaggerOptions.UpdateUserOptions
    public ResponseEntity<UserUpdateResponseDTO> updateUser(
            @PathVariable(value = "id") String id,
            @RequestBody UserUpdateRequestDTO userUpdateRequest
    ){
        UserUpdateResponseDTO userUpdated = this.userService.updateUser(id, userUpdateRequest);

        return ResponseEntity.ok().body(userUpdated);
    }

    @DeleteMapping(value = "/{id}")
    @UserControllerSwaggerOptions.DeleteUserOptions
    public ResponseEntity<?> deleteUser(
            @PathVariable(value = "id") String id
    ){
        this.userService.deleteUser(id);

        return ResponseEntity.noContent().build();
    }

}
