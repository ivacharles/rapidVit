package com.rapid.vit.listing.model;

import com.rapid.vit.appUser.AppUserDetail;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public abstract class Listing {
    private Long listingID;
    private AppUserDetail userDetail;
    private String listingTitle;
    private String listingCategory;
    private String listingSubCategory;
    private String listingCity;
    private String listingDescription;
    private BigDecimal listingPrice;
    private List<ListingPhoto> listingPhotos;
    private boolean isListingsVerified; //by admin, but by default is true unless otherwise
    private LocalDateTime listingCreatedDate;
    private LocalDateTime listingUpdatedDate;
    private LocalDateTime listingPostedDate;

    public Listing(AppUserDetail userDetail, String listingTitle, String listingCategory, String listingSubCategory, String listingCity, String listingDescription, BigDecimal listingPrice, List<ListingPhoto> listingPhotos, boolean isListingsVerified, LocalDateTime listingCreatedDate) {
        this.userDetail = userDetail;
        this.listingTitle = listingTitle;
        this.listingCategory = listingCategory;
        this.listingSubCategory = listingSubCategory;
        this.listingCity = listingCity;
        this.listingDescription = listingDescription;
        this.listingPrice = listingPrice;
        this.listingPhotos = listingPhotos;
        this.isListingsVerified = isListingsVerified;
        this.listingCreatedDate = listingCreatedDate;
    }

    public Long getListingID() {
        return listingID;
    }

    public void setListingID(Long listingID) {
        this.listingID = listingID;
    }

    public AppUserDetail getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(AppUserDetail userDetail) {
        this.userDetail = userDetail;
    }

    public String getListingTitle() {
        return listingTitle;
    }

    public void setListingTitle(String listingTitle) {
        this.listingTitle = listingTitle;
    }

    public String getListingCategory() {
        return listingCategory;
    }

    public void setListingCategory(String listingCategory) {
        this.listingCategory = listingCategory;
    }

    public String getListingSubCategory() {
        return listingSubCategory;
    }

    public void setListingSubCategory(String listingSubCategory) {
        this.listingSubCategory = listingSubCategory;
    }

    public String getListingCity() {
        return listingCity;
    }

    public void setListingCity(String listingCity) {
        this.listingCity = listingCity;
    }

    public String getListingDescription() {
        return listingDescription;
    }

    public void setListingDescription(String listingDescription) {
        this.listingDescription = listingDescription;
    }

    public BigDecimal getListingPrice() {
        return listingPrice;
    }

    public void setListingPrice(BigDecimal listingPrice) {
        this.listingPrice = listingPrice;
    }

    public List<ListingPhoto> getListingPhotos() {
        return listingPhotos;
    }

    public void setListingPhotos(List<ListingPhoto> listingPhotos) {
        this.listingPhotos = listingPhotos;
    }

    public boolean isListingsVerified() {
        return isListingsVerified;
    }

    public void setListingsVerified(boolean listingsVerified) {
        isListingsVerified = listingsVerified;
    }

    public LocalDateTime getListingCreatedDate() {
        return listingCreatedDate;
    }

    public void setListingCreatedDate(LocalDateTime listingCreatedDate) {
        this.listingCreatedDate = listingCreatedDate;
    }

    public LocalDateTime getListingUpdatedDate() {
        return listingUpdatedDate;
    }

    public void setListingUpdatedDate(LocalDateTime listingUpdatedDate) {
        this.listingUpdatedDate = listingUpdatedDate;
    }

    public LocalDateTime getListingPostedDate() {
        return listingPostedDate;
    }

    public void setListingPostedDate(LocalDateTime listingPostedDate) {
        this.listingPostedDate = listingPostedDate;
    }

    @Override
    public String toString() {
        return "Listing{" +
                "listingID=" + listingID +
                ", userDetail=" + userDetail +
                ", listingTitle='" + listingTitle + '\'' +
                ", listingCategory='" + listingCategory + '\'' +
                ", listingSubCategory='" + listingSubCategory + '\'' +
                ", listingCity='" + listingCity + '\'' +
                ", listingDescription='" + listingDescription + '\'' +
                ", listingPrice=" + listingPrice +
                ", listingPhotos=" + listingPhotos +
                ", isListingsVerified=" + isListingsVerified +
                ", listingCreatedDate=" + listingCreatedDate +
                ", listingUpdatedDate=" + listingUpdatedDate +
                ", listingPostedDate=" + listingPostedDate +
                '}';
    }
}