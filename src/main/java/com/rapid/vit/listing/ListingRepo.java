package com.rapid.vit.listing;


import com.rapid.vit.listing.model.Listing;
import com.rapid.vit.listing.model.ListingPhoto;

import java.util.List;
import java.util.Optional;

public interface ListingRepo {
    Long saveListing (Long listingOwnerID, Listing listing);
    Optional<Listing> getListingByID(Long listingID);
    List<Listing> getAllListing();
    List<Listing> getAllListingByCategory(String category);
    List<Listing> getAllListingBySubCategory(String category);
    List<Listing> getAllListingByUserID(Long listingOwnerID);
    List<Listing> getAllListingForOneUser(String category);
    int updateListing(Long listingID, ListingForm listing);
    int deleteListing(Long listingID);
    int saveListingImage(Long listingID, ListingPhoto listingPhoto);

}
