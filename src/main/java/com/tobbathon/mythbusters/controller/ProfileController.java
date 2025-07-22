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
        ProfileCreateDTO dto = new ProfileCreateDTO.Builder()
                .username(createDTO.getUsername())
                .email(createDTO.getEmail())
                .password(createDTO.getPassword())
                .build();

        return profileService.createProfile(dto);
    }

    @PostMapping("/login")
    public ProfileDTO login(@RequestBody LoginRequestDTO loginRequestDTO) {
        return profileService.login(loginRequestDTO);
    }

    // @GetMapping("/{id}")
    // public ProfileDTO getProfile(@PathVariable Long id) {
    //     return profileService.getAvatarByProfileId(id);
    // }
}