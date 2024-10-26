package com.shms.api.controller;


import com.shms.api.dao.doctor.DoctorRepository;
import com.shms.api.dao.users.UserRepository;
import com.shms.api.dto.auth.users.UserDTO;
import com.shms.api.dto.doctor.DoctorDTO;
import com.shms.api.mapper.DoctorMapper;
import com.shms.api.mapper.UserMapper;
import com.shms.api.model.auth.user.UserEntity;
import com.shms.api.model.doctor.Doctor;
import com.shms.api.service.EntityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/users")
@Tag(name = "User")
public class UsersController {

    private final UserMapper userMapper;
    private final EntityService<UserEntity, UserDTO> userService;
    private final UserRepository userRepository;

    @Operation(summary = "Create a new doctor", description = "Creates a new user in the system and returns the created user object.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created"),
            @ApiResponse(responseCode = "400", description = "Request validation error"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO create(@Parameter(description = "Details of the user to be created", required = true)
                            @RequestBody @Valid UserDTO userDTO) {
        return userMapper.toDto(userService.create(userDTO));
    }
//
//    @Operation(
//            summary = "Updates doctor",
//            description = "Updates doctor by provided ID."
//    )
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Doctor updated"),
//            @ApiResponse(responseCode = "500", description = "Internal server error")
//    })
//    @PutMapping("/{id}")
//    public void update(@Parameter(description = "ID of the doctor to be updated", required = true)
//                       @PathVariable("id") String id, @RequestBody @Valid DoctorDTO doctorDTO) {
//        Doctor doctor = doctorService.getById(id);
//        doctorService.update(doctor, doctorDTO);
//    }
//
//    @Operation(
//            summary = "Deletes doctor",
//            description = "Deletes doctor by provided ID."
//    )
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Doctor deleted"),
//            @ApiResponse(responseCode = "500", description = "Internal server error")
//    })
//    @DeleteMapping("/{id}")
//    public void delete(@Parameter(description = "ID of the doctor to be deleted", required = true)
//                       @PathVariable("id") String id) {
//        doctorRepository.deleteById(id);
//    }
//
    @Operation(
            summary = "Get all users",
            description = "Returns all available active users. If there are no active user an empty array returns."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public List<UserDTO> getAll() {
        return userMapper.map(userRepository.findAll());
    }

}
