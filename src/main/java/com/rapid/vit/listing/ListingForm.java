package com.rapid.vit.listing;

import com.rapid.vit.listing.model.ListingPhoto;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.List;

public class ListingForm {
    @Email(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message = "email invalid")
    private String listingOwnerUsername;
    @NotBlank
    private String listingTitle;
    @NotBlank
    private String listingCategory;
    @NotBlank
    private String listingSubCategory;
    @NotBlank
    private String listingCity;
    @NotBlank
    private String listingDescription;
    private BigDecimal listingPrice;
    private List<ListingPhoto> listingPhotos;

    //details for a housing post\
    private String listingHousingAvailableOn;
    @Digits(integer = 20, fraction = 2)
    private Long listingHousingSize; // sq ft
    private String listingHousingType;
    private int listingHousingNumberOfBedroom;
    private int listingHousingNumberOfBathroom;
    private boolean listingHousingHasParking;

    //details for job post
    private String listingJobType;
    private String listingJobTitle;
    private String listingJobCompanyName;

    //details for community post
    @Digits(integer = 10, fraction = 0)
    private Long listingEventNumberOfTicket;
    private String listingEventDate;
    private String listingEventVenue;

    //details for sale post
    private String listingSaleMake;
    private String listingSaleModel;
    private String listingSaleSize;
    private String listingSaleCondition;

    public ListingForm(String listingOwnerUsername, String listingTitle, String listingCategory, String listingSubCategory, String listingCity, String listingDescription, BigDecimal listingPrice, List<ListingPhoto> listingPhotos, String listingHousingAvailableOn, Long listingHousingSize, String listingHousingType, int listingHousingNumberOfBedroom, int listingHousingNumberOfBathroom, boolean listingHousingHasParking, String listingJobType, String listingJobTitle, String listingJobCompanyName, Long listingEventNumberOfTicket, String listingEventDate, String listingEventVenue, String listingSaleMake, String listingSaleModel, String listingSaleSize, String listingSaleCondition) {
        this.listingOwnerUsername=listingOwnerUsername;
        this.listingTitle = listingTitle;
        this.listingCategory = listingCategory;
        this.listingSubCategory = listingSubCategory;
        this.listingCity = listingCity;
        this.listingDescription = listingDescription;
        this.listingPrice = listingPrice;
        this.listingPhotos = listingPhotos;
        this.listingHousingAvailableOn = listingHousingAvailableOn;
        this.listingHousingSize = listingHousingSize;
        this.listingHousingType = listingHousingType;
        this.listingHousingNumberOfBedroom = listingHousingNumberOfBedroom;
        this.listingHousingNumberOfBathroom = listingHousingNumberOfBathroom;
        this.listingHousingHasParking = listingHousingHasParking;
        this.listingJobType = listingJobType;
        this.listingJobTitle = listingJobTitle;
        this.listingJobCompanyName = listingJobCompanyName;
        this.listingEventNumberOfTicket = listingEventNumberOfTicket;
        this.listingEventDate = listingEventDate;
        this.listingEventVenue = listingEventVenue;
        this.listingSaleMake = listingSaleMake;
        this.listingSaleModel = listingSaleModel;
        this.listingSaleSize = listingSaleSize;
        this.listingSaleCondition = listingSaleCondition;
    }

    public String getListingOwnerUsername() {
        return listingOwnerUsername;
    }

    public void setListingOwnerUsername(String listingOwnerUsername) {
        this.listingOwnerUsername = listingOwnerUsername;
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

    public String getListingTitle() {
        return listingTitle;
    }

    public void setListingTitle(String listingTitle) {
        this.listingTitle = listingTitle;
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

    public String getListingHousingAvailableOn() {
        return listingHousingAvailableOn;
    }

    public void setListingHousingAvailableOn(String listingHousingAvailableOn) {
        this.listingHousingAvailableOn = listingHousingAvailableOn;
    }

    public Long getListingHousingSize() {
        return listingHousingSize;
    }

    public void setListingHousingSize(Long listingHousingSize) {
        this.listingHousingSize = listingHousingSize;
    }

    public String getListingHousingType() {
        return listingHousingType;
    }

    public void setListingHousingType(String listingHousingType) {
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

    public void setListingHousingHasParking(boolean listingHousingNumberOfParking) {
        this.listingHousingHasParking = listingHousingNumberOfParking;
    }

    public Long getListingEventNumberOfTicket() {
        return listingEventNumberOfTicket;
    }

    public void setListingEventNumberOfTicket(Long listingEventNumberOfTicket) {
        this.listingEventNumberOfTicket = listingEventNumberOfTicket;
    }

    public String getListingEventDate() {
        return listingEventDate;
    }

    public void setListingEventDate(String listingEventDate) {
        this.listingEventDate = listingEventDate;
    }

    public String getListingEventVenue() {
        return listingEventVenue;
    }

    public void setListingEventVenue(String listingEventVenue) {
        this.listingEventVenue = listingEventVenue;
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

    public String  getListingSaleCondition() {
        return listingSaleCondition;
    }

    public void setListingSaleCondition(String listingSaleCondition) {
        this.listingSaleCondition = listingSaleCondition;
    }

    public String getListingJobType() {
        return listingJobType;
    }

    public void setListingJobType(String listingJobType) {
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
        return "ListingForm{" +
                "listingTitle='" + listingTitle + '\'' +
                ", listingCategory='" + listingCategory + '\'' +
                ", listingSubCategory='" + listingSubCategory + '\'' +
                ", listingCity='" + listingCity + '\'' +
                ", listingDescription='" + listingDescription + '\'' +
                ", listingPrice=" + listingPrice +
                ", listingPhotos=" + listingPhotos +
                ", listingHousingAvailableOn=" + listingHousingAvailableOn +
                ", listingHousingSize=" + listingHousingSize +
                ", listingHousingType=" + listingHousingType +
                ", listingHousingNumberOfBedroom=" + listingHousingNumberOfBedroom +
                ", listingHousingNumberOfBathroom=" + listingHousingNumberOfBathroom +
                ", listingHousingHasParking=" + listingHousingHasParking +
                ", listingJobType=" + listingJobType +
                ", listingJobTitle='" + listingJobTitle + '\'' +
                ", listingJobCompanyName='" + listingJobCompanyName + '\'' +
                ", listingEventNumberOfTicket=" + listingEventNumberOfTicket +
                ", listingEventDate=" + listingEventDate +
                ", listingEventVenue='" + listingEventVenue + '\'' +
                ", listingSaleMake='" + listingSaleMake + '\'' +
                ", listingSaleModel='" + listingSaleModel + '\'' +
                ", listingSaleSize='" + listingSaleSize + '\'' +
                ", listingSaleCondition=" + listingSaleCondition +
                '}';
    }
}
