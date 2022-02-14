package com.rapid.vit.listing.model;

import com.rapid.vit.appUser.AppUserDetail;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class HousingListing extends Listing{
    //details for a housing post\
    private LocalDateTime listingHousingAvailableOn;
    private Long listingHousingSize; // sq ft
    private HousingType listingHousingType;
    private int listingHousingNumberOfBedroom;
    private int listingHousingNumberOfBathroom;
    private boolean listingHousingHasParking;

    public HousingListing(AppUserDetail userDetail, String listingTitle, String listingCategory, String listingSubCategory, String listingCity, String listingDescription, BigDecimal listingPrice, List<ListingPhoto> listingPhotos, boolean isListingsVerified, LocalDateTime listingCreatedDate, LocalDateTime listingHousingAvailableOn, Long listingHousingSize, HousingType listingHousingType, int listingHousingNumberOfBedroom, int listingHousingNumberOfBathroom, boolean listingHousingHasParking) {
        super(userDetail, listingTitle, listingCategory, listingSubCategory, listingCity, listingDescription, listingPrice, listingPhotos, isListingsVerified, listingCreatedDate);
        this.listingHousingAvailableOn = listingHousingAvailableOn;
        this.listingHousingSize = listingHousingSize;
        this.listingHousingType = listingHousingType;
        this.listingHousingNumberOfBedroom = listingHousingNumberOfBedroom;
        this.listingHousingNumberOfBathroom = listingHousingNumberOfBathroom;
        this.listingHousingHasParking = listingHousingHasParking;
    }



    public LocalDateTime getListingHousingAvailableOn() {
        return listingHousingAvailableOn;
    }

    public void setListingHousingAvailableOn(LocalDateTime listingHousingAvailableOn) {
        this.listingHousingAvailableOn = listingHousingAvailableOn;
    }

    public Long getListingHousingSize() {
        return listingHousingSize;
    }

    public void setListingHousingSize(Long listingHousingSize) {
        this.listingHousingSize = listingHousingSize;
    }

    public HousingType getListingHousingType() {
        return listingHousingType;
    }

    public void setListingHousingType(HousingType listingHousingType) {
        this.listingHousingType = listingHousingType;
    }

    public int getListingHousingNumberOfBedroom() {
        return listingHousingNumberOfBedroom;
    }

    public void setListingHousingNumberOfBedroom(int listingHousingNumberOfBedroom) {
        this.listingHousingNumberOfBedroom = listingHousingNumberOfBedroom;
    }

    public int getListingHousingNumberOfBathroom() {
        return listingHousingNumberOfBathroom;
    }

    public void setListingHousingNumberOfBathroom(int listingHousingNumberOfBathroom) {
        this.listingHousingNumberOfBathroom = listingHousingNumberOfBathroom;
    }

    public boolean isListingHousingHasParking() {
        return listingHousingHasParking;
    }

    public void setListingHousingHasParking(boolean listingHousingHasParking) {
        this.listingHousingHasParking = listingHousingHasParking;
    }

    @Override
    public String toString() {
        return "HousingListing{" +
                super.toString()+
                "listingHousingAvailableOn=" + listingHousingAvailableOn +
                ", listingHousingSize=" + listingHousingSize +
                ", listingHousingType=" + listingHousingType +
                ", listingHousingNumberOfBedroom=" + listingHousingNumberOfBedroom +
                ", listingHousingNumberOfBathroom=" + listingHousingNumberOfBathroom +
                ", listingHousingNumberOfParking=" + listingHousingHasParking +
                "} " ;
    }
}
