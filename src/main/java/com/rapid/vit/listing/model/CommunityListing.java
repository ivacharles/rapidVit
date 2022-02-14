package com.rapid.vit.listing.model;

import com.rapid.vit.appUser.AppUserDetail;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class CommunityListing extends Listing{
    //details for community post
    private Long listingEventNumberOfTicket;
    private LocalDateTime listingEventDate;
    private String listingEventVenue;

    public CommunityListing(AppUserDetail userDetail, String listingTitle, String listingCategory, String listingSubCategory, String listingCity, String listingDescription, BigDecimal listingPrice, List<ListingPhoto> listingPhotos, boolean isListingsVerified, LocalDateTime listingCreatedDate, Long listingEventNumberOfTicket, LocalDateTime listingEventDate, String listingEventVenue) {
        super(userDetail, listingTitle, listingCategory, listingSubCategory, listingCity, listingDescription, listingPrice, listingPhotos, isListingsVerified, listingCreatedDate);
        this.listingEventNumberOfTicket = listingEventNumberOfTicket;
        this.listingEventDate = listingEventDate;
        this.listingEventVenue = listingEventVenue;
    }



    public Long getListingEventNumberOfTicket() {
        return listingEventNumberOfTicket;
    }

    public void setListingEventNumberOfTicket(Long listingEventNumberOfTicket) {
        this.listingEventNumberOfTicket = listingEventNumberOfTicket;
    }

    public LocalDateTime getListingEventDate() {
        return listingEventDate;
    }

    public void setListingEventDate(LocalDateTime listingEventDate) {
        this.listingEventDate = listingEventDate;
    }

    public String getListingEventVenue() {
        return listingEventVenue;
    }

    public void setListingEventVenue(String listingEventVenue) {
        this.listingEventVenue = listingEventVenue;
    }

    @Override
    public String toString() {
        return "CommunityListing{" +
                super.toString()+
                "listingEventNumberOfTicket=" + listingEventNumberOfTicket +
                ", listingEventDate=" + listingEventDate +
                ", listingEventVenue='" + listingEventVenue + '\'' +
                '}';
    }
}

