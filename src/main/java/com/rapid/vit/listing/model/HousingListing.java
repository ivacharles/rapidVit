package com.rapid.vit.listing.model;

import java.time.LocalDateTime;

public class HousingListing extends Listing{
    //details for a housing post\
    private LocalDateTime listingHousingAvailableOn;
    private Long listingHousingSize; // sq ft
    private HousingType listingHousingType;
    private int listingHousingNumberOfBedroom;
    private int listingHousingNumberOfBathroom;
    private boolean listingHousingNumberOfParking;

}
