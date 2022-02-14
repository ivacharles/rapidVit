package com.rapid.vit.listing;

import com.rapid.vit.listing.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class ListingRowMapper implements RowMapper<Listing> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ListingRowMapper.class);

    @Override
    public Listing mapRow(ResultSet rs, int rowNum) throws SQLException {
        Listing listing = null;
        Long listingID = rs.getLong("listingID");
        Long listingOwnerID = rs.getLong("listingOwnerID");
        String title = rs.getString("listingTitle");
        String category = rs.getString("listingCategory");
        String subCategory = rs.getString("listingSubCategory");
        String city = rs.getString("listingCity");
        String listingDescription = rs.getString("listingDescription");
        BigDecimal listingPrice = rs.getBigDecimal("listingPrice");
        boolean isListingVerified = rs.getBoolean("isListingVerified");
        LocalDateTime listingCreatedDate = rs.getTimestamp("listingCreatedDate").toLocalDateTime();
        LocalDateTime listingUpdatedDate = rs.getTimestamp("listingUpdatedDate") ==null ? null : rs.getTimestamp("listingUpdatedDate").toLocalDateTime();
        LocalDateTime listingPostedDate = rs.getTimestamp("listingPostedDate") == null ? null : rs.getTimestamp("listingPostedDate").toLocalDateTime();

        LocalDateTime listingHousingAvailableOn = rs.getTimestamp("listingHousingAvailableOn") ==null ? null : rs.getTimestamp("listingHousingAvailableOn").toLocalDateTime();
        Long listingHousingSize = rs.getLong("listingHousingSize");
        String listingHousingType = rs.getString("listingHousingType");
        String listingHousingNumberOfBedroom = rs.getString("listingHousingNumberOfBedroom");
        String listingHousingNumberOfBathroom = rs.getString("listingHousingNumberOfBathroom");
        boolean listingHousingHasParking = rs.getBoolean("listingHousingHasParking");

        String listingJobType = rs.getString("listingJobType");
        String listingJobTitle = rs.getString("listingJobTitle");
        String listingJobCompanyName = rs.getString("listingJobCompanyName");

        String listingSaleMake = rs.getString("listingSaleMake");
        String listingSaleModel = rs.getString("listingSaleModel");
        String listingSaleSize = rs.getString("listingSaleSize");
        String listingSaleCondition = rs.getString("listingSaleCondition");

        Long listingEventNumberOfTicket = rs.getLong("listingEventNumberOfTicket");
        LocalDateTime listingEventDate = rs.getTimestamp("listingEventDate") ==null ? null : rs.getTimestamp("listingEventDate").toLocalDateTime();
        String listingEventVenue = rs.getString("listingEventVenue");

        if (category.equalsIgnoreCase(Category.COMMUNITY.getCategoryTypeString())) {
            listing = new CommunityListing(null, title, category, subCategory,
                    city, listingDescription, listingPrice, null, isListingVerified, listingCreatedDate,
                    listingEventNumberOfTicket, listingEventDate, listingEventVenue);
        } else if (category.equalsIgnoreCase(Category.HOUSING.getCategoryTypeString())) {
            try {
                listing = new HousingListing(null, title,category,subCategory,city,listingDescription,listingPrice,
                        null,isListingVerified,listingCreatedDate,listingHousingAvailableOn,listingHousingSize,
                        HousingType.returnHousingType(listingHousingType),Integer.parseInt(listingHousingNumberOfBedroom),
                        Integer.parseInt(listingHousingNumberOfBathroom),listingHousingHasParking);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        } else if (category.equalsIgnoreCase(Category.JOB.getCategoryTypeString())) {
            try {
                listing = new JobListing(null, title,category,subCategory,city,listingDescription,listingPrice,
                        null,isListingVerified,listingCreatedDate,
                        EmploymentType.returnEnumStringValue(listingJobType),listingJobTitle,listingJobCompanyName);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        } else if (category.equalsIgnoreCase(Category.SALE.getCategoryTypeString())) {
            try {
                listing = new SaleListing(null,title,category,subCategory,
                        city,listingDescription,listingPrice,null, isListingVerified, listingCreatedDate,listingSaleMake,listingSaleModel,listingSaleSize,ListingCondition.returnEnumStringValue(listingSaleCondition));
            } catch (Throwable e) {
                e.printStackTrace();
            }
        } else if (category.equalsIgnoreCase(Category.SERVICE.getCategoryTypeString())) {
            listing = new ServiceListing(null,title,category,subCategory,city,listingDescription,listingPrice,
                    null,isListingVerified,listingCreatedDate);
        }
        assert listing != null;
        listing.setListingID(listingID);
        listing.setListingUpdatedDate(listingUpdatedDate);
        listing.setListingPostedDate(listingPostedDate);
        LOGGER.info("this is inside of the listing row mapper, before the return statement, the listing is  {}", listing);
        return listing;
    }
}
