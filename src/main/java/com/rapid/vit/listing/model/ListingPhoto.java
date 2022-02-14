package com.rapid.vit.listing.model;

public class ListingPhoto {
    private Long listingPhotoID;
    private Long listingID;
    private String listingPhotoName;
    private String listingPhotoPath;

    public ListingPhoto(String listingPhotoName, String listingPhotoPath) {
        this.listingPhotoName = listingPhotoName;
        this.listingPhotoPath = listingPhotoPath;
    }

    public ListingPhoto(Long listingPhotoID, Long listingID, String listingPhotoName, String listingPhotoPath) {
        this.listingPhotoID = listingPhotoID;
        this.listingID = listingID;
        this.listingPhotoName = listingPhotoName;
        this.listingPhotoPath = listingPhotoPath;
    }

    public Long getListingPhotoID() {
        return listingPhotoID;
    }

    public Long getListingID() {
        return listingID;
    }

    public void setListingID(Long listingID) {
        this.listingID = listingID;
    }

    public void setListingPhotoID(Long listingPhotoID) {
        this.listingPhotoID = listingPhotoID;
    }

    public String getListingPhotoName() {
        return listingPhotoName;
    }

    public void setListingPhotoName(String listingPhotoName) {
        this.listingPhotoName = listingPhotoName;
    }

    public String getListingPhotoPath() {
        return listingPhotoPath;
    }

    public void setListingPhotoPath(String listingPhotoPath) {
        this.listingPhotoPath = listingPhotoPath;
    }
}
