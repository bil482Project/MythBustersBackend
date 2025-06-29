package com.tobbathon.mythbusters.controller;

import com.tobbathon.mythbusters.model.dto.LoginRequestDTO;
import com.tobbathon.mythbusters.model.dto.ProfileCreateDTO;
import com.tobbathon.mythbusters.model.dto.ProfileDTO;
import com.tobbathon.mythbusters.service.ProfileService;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/profiles")
public class ProfileController {
    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping
    public ProfileDTO createProfile(@Valid @RequestBody ProfileCreateDTO createDTO) {
        return profileService.createProfile(createDTO);
    }

    @PostMapping("/login")
    public ProfileDTO login(@RequestBody LoginRequestDTO loginRequest) {
        return profileService.login(loginRequest.getEmail(), loginRequest.getPassword());
    }

}