package com.rapid.vit.listing.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public abstract class Listing {
    private Long listingID;
    private String listingCategory;
    private String listingTitle;
    private String listingCity;
    private String listingDescription;
    private BigDecimal listingPrice;
    private ListingPhoto listingPhoto;
    private boolean listingIsVerified; //by admin, but by default is true unless otherwise
    private LocalDateTime listingCreatedDate;
    private LocalDateTime listingUpdatedDate;
    private LocalDateTime listingPostedDate;

















}
