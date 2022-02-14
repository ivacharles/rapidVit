package com.rapid.vit.listing;

import com.rapid.vit.appUser.registration.AppUserResponse;
import com.rapid.vit.listing.model.Listing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/listing")
public class ListingController {

    private final ListingService listingService;

    @Autowired
    public ListingController(ListingService listingService) {
        this.listingService = listingService;
    }

    @PostMapping("/{category}/create")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ROLE_REGULAR_USER')")
    public AppUserResponse addListing(@RequestBody @Valid ListingForm listingForm, @PathVariable("category") String category) throws Throwable {
        return listingService.saveListing(listingForm, category)
                .orElseThrow(() -> new HttpServerErrorException(HttpStatus.BAD_REQUEST, "duplicated listing"));
    }

    @GetMapping("/{category}/all")
    @ResponseStatus(HttpStatus.FOUND)
    public List<Listing> getAllListingWithCategory(@PathVariable("category") String category) {
        return listingService.getAllListingForCategory(category);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.FOUND)
    public List<Listing> getAllListings() {
        return listingService.getAllListings();
    }

    @DeleteMapping("/{category}/{listingID}/delete")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_REGULAR_USER')")
    public int deleteListingByID(@PathVariable("listingID") Long listingID, @PathVariable("category") String category) {
        return listingService.deleteListingByID(listingID);
    }
    @PutMapping("/{category}/{listingID}/update")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_REGULAR_USER')")
    public AppUserResponse updateListing(@RequestBody @Valid ListingForm listingForm, @PathVariable("category") String category,
                                 @PathVariable("listingID") Long listingID){
        return listingService.updateListing(listingID, listingForm);
    }

    @GetMapping("/all/{listingOwnerUsername}")
    @PreAuthorize("hasRole('ROLE_REGULAR_USER')")
    @ResponseStatus(HttpStatus.FOUND)
    public List<Listing> getAllListingForUserByUsername(@PathVariable("listingOwnerUsername") String listingOwnerUsername){
        return listingService.findAllListingByUserID(listingOwnerUsername);
    }

}
