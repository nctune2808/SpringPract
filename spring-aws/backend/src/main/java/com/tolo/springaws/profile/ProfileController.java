package com.tolo.springaws.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/profile")
@CrossOrigin("*")
public class ProfileController {


    private ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

//    @GetMapping
//    public List<Profile> getProfiles() {
//        return profileService.getProfiles();
//    }

    @GetMapping
    public List<Profile> getProfiles() {
        return profileService.findAll();
    }

    @GetMapping("{profileId}")
    public Profile getProfileByName(@PathVariable("profileId") UUID profileId){
        return profileService.getById(profileId);
    }

    @PostMapping(
            path = "{profileId}/image/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void uploadImage(@PathVariable("profileId") UUID profileId,
                            @RequestParam("file")MultipartFile file) {

        profileService.uploadImage(profileId, file);
    }

    @GetMapping("{profileId}/image/download")
    public byte[] downloadImage(@PathVariable("profileId") UUID profileId) {
        return profileService.downloadImage(profileId);
    }
}
