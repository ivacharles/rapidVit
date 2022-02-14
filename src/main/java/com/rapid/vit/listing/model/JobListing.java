package com.rapid.vit.listing.model;

import com.rapid.vit.appUser.AppUserDetail;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class JobListing extends Listing{
    //details for job post
    private EmploymentType listingJobType;
    private String listingJobTitle;
    private String listingJobCompanyName;

    public JobListing(AppUserDetail userDetail, String listingTitle, String listingCategory, String listingSubCategory, String listingCity, String listingDescription, BigDecimal listingPrice, List<ListingPhoto> listingPhotos, boolean isListingsVerified, LocalDateTime listingCreatedDate, EmploymentType listingJobType, String listingJobTitle, String listingJobCompanyName) {
        super(userDetail, listingTitle, listingCategory, listingSubCategory, listingCity, listingDescription, listingPrice, listingPhotos, isListingsVerified, listingCreatedDate);
        this.listingJobType = listingJobType;
        this.listingJobTitle = listingJobTitle;
        this.listingJobCompanyName = listingJobCompanyName;
    }

    public EmploymentType getListingJobType() {
        return listingJobType;
    }

    public void setListingJobType(EmploymentType listingJobType) {
        this.listingJobType = listingJobType;
    }

    public String getListingJobTitle() {
        return listingJobTitle;
    }

    public void setListingJobTitle(String listingJobTitle) {
        this.listingJobTitle = listingJobTitle;
    }

    public String getListingJobCompanyName() {
        return listingJobCompanyName;
    }

    public void setListingJobCompanyName(String listingJobCompanyName) {
        this.listingJobCompanyName = listingJobCompanyName;
    }

    @Override
    public String toString() {
        return "JobListing{" +
                super.toString() +
                "listingJobType=" + listingJobType +
                ", listingJobTitle='" + listingJobTitle + '\'' +
                ", listingJobCompanyName='" + listingJobCompanyName + '\'' +
                "} " ;
    }
}
