package com.rapid.vit.listing.model;

import com.rapid.vit.appUser.AppUserDetail;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class ServiceListing extends Listing{

    public ServiceListing(AppUserDetail userDetail, String listingTitle, String listingCategory, String listingSubCategory, String listingCity, String listingDescription, BigDecimal listingPrice, List<ListingPhoto> listingPhotos, boolean isListingsVerified, LocalDateTime listingCreatedDate) {
        super(userDetail, listingTitle, listingCategory, listingSubCategory, listingCity, listingDescription, listingPrice, listingPhotos, isListingsVerified, listingCreatedDate);
    }
}
