package com.rapid.vit.listing.model;


import com.rapid.vit.appUser.AppUserDetail;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class SaleListing extends Listing{
    //details for sale post
    private String listingSaleMake;
    private String listingSaleModel;
    private String listingSaleSize;
    private ListingCondition listingSaleCondition;

    public SaleListing(AppUserDetail userDetail, String listingTitle, String listingCategory, String listingSubCategory, String listingCity, String listingDescription, BigDecimal listingPrice, List<ListingPhoto> listingPhotos, boolean isListingsVerified, LocalDateTime listingCreatedDate, String listingSaleMake, String listingSaleModel, String listingSaleSize, ListingCondition listingSaleCondition) {
        super(userDetail, listingTitle, listingCategory, listingSubCategory, listingCity, listingDescription, listingPrice, listingPhotos, isListingsVerified, listingCreatedDate);
        this.listingSaleMake = listingSaleMake;
        this.listingSaleModel = listingSaleModel;
        this.listingSaleSize = listingSaleSize;
        this.listingSaleCondition = listingSaleCondition;
    }

    public String getListingSaleMake() {
        return listingSaleMake;
    }

    public void setListingSaleMake(String listingSaleMake) {
        this.listingSaleMake = listingSaleMake;
    }

    public String getListingSaleModel() {
        return listingSaleModel;
    }

    public void setListingSaleModel(String listingSaleModel) {
        this.listingSaleModel = listingSaleModel;
    }

    public String getListingSaleSize() {
        return listingSaleSize;
    }

    public void setListingSaleSize(String listingSaleSize) {
        this.listingSaleSize = listingSaleSize;
    }

    public ListingCondition getListingSaleCondition() {
        return listingSaleCondition;
    }

    public void setListingSaleCondition(ListingCondition listingSaleCondition) {
        this.listingSaleCondition = listingSaleCondition;
    }

    @Override
    public String toString() {
        return "SaleListing{" +
                super.toString()+
                "listingSaleMake='" + listingSaleMake + '\'' +
                ", listingSaleModel='" + listingSaleModel + '\'' +
                ", listingSaleSize='" + listingSaleSize + '\'' +
                ", listingSaleCondition=" + listingSaleCondition +
                '}';
    }
}
