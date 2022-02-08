package com.rapid.vit.listing;

import com.rapid.vit.appUser.registration.AppUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import javax.validation.Valid;

@RestController
@RequestMapping("/listing")
public class ListingController {

    private final ListingService listingService;

    @Autowired
    public ListingController(ListingService listingService) {
        this.listingService = listingService;
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ROLE_SUPER_USER')")
    public AppUserResponse addListing(@RequestBody @Valid ListingForm listingForm){
        return listingService.savePost(listingForm)
                .orElseThrow(() -> new HttpServerErrorException(HttpStatus.BAD_REQUEST, "details error"));
    }
}
