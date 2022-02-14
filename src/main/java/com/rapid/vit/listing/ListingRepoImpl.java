package com.rapid.vit.listing;

import com.rapid.vit.listing.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class ListingRepoImpl implements ListingRepo{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ListingRepoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Long saveListing(Long listingOwnerID, Listing listing) {
        String sql = getSQLStatementListingBaseOnCategory(listing.getListingCategory());
        KeyHolder key = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"listingid"});
            ps.setLong(1, listingOwnerID);
            ps.setString(2, listing.getListingTitle());
            ps.setString(3, listing.getListingCategory());
            ps.setString(4, listing.getListingSubCategory());
            ps.setString(5, listing.getListingCity());
            ps.setString(6, listing.getListingDescription());
            ps.setBigDecimal(7, listing.getListingPrice());
            ps.setBoolean(8, listing.isListingsVerified());
            ps.setTimestamp(9, Timestamp.valueOf(listing.getListingCreatedDate()));
            //add more prepare statement base on the listing category
            ps = getPSListingBaseOnCategory(listing.getListingCategory(), ps, listing);
            return ps;
        }, key);
        return Objects.requireNonNull(key.getKey()).longValue();
    }

    private String getSQLStatementListingBaseOnCategory(String listingCategory) {
        String sql =null;
        String housingSQL = "INSERT INTO rapidvit_listing(listingOwnerID, listingTitle, listingCategory, listingSubCategory, listingCity, listingDescription, listingPrice, isListingVerified, listingCreatedDate, listingHousingAvailableOn, listingHousingSize,listingHousingType,listingHousingNumberOfBedroom,listingHousingNumberOfBathroom, listingHousingHasParking) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        String jobSQL = "INSERT INTO rapidvit_listing(listingOwnerID, listingTitle, listingCategory, listingSubCategory, listingCity, listingDescription, listingPrice, isListingVerified, listingCreatedDate, listingJobType, listingJobTitle ,listingJobCompanyName) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
        String communitySQL = "INSERT INTO rapidvit_listing(listingOwnerID, listingTitle, listingCategory, listingSubCategory, listingCity, listingDescription, listingPrice, isListingVerified, listingCreatedDate, listingEventNumberOfTicket, listingEventDate, listingEventVenue) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
        String  saleSQL= "INSERT INTO rapidvit_listing(listingOwnerID, listingTitle, listingCategory, listingSubCategory, listingCity, listingDescription, listingPrice, isListingVerified, listingCreatedDate, listingSaleMake, listingSaleModel, listingSaleSize, listingSaleCondition) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        String  serviceSQL= "INSERT INTO rapidvit_listing(listingOwnerID, listingTitle, listingCategory, listingSubCategory, listingCity, listingDescription, listingPrice, isListingVerified, listingCreatedDate) VALUES(?,?,?,?,?,?,?,?,?)";
        if(listingCategory.equalsIgnoreCase(Category.HOUSING.getCategoryTypeString())){
            sql =housingSQL;
        }else if(listingCategory.equalsIgnoreCase(Category.JOB.getCategoryTypeString())) {
            sql =jobSQL;
        }else if(listingCategory.equalsIgnoreCase(Category.COMMUNITY.getCategoryTypeString())) {
            sql =communitySQL;
        }else if(listingCategory.equalsIgnoreCase(Category.SALE.getCategoryTypeString())) {
            sql =saleSQL;
        }else if(listingCategory.equalsIgnoreCase(Category.SERVICE.getCategoryTypeString())) {
            sql =serviceSQL;
        }
        return sql;
    }

    private PreparedStatement getHousingPSReady(PreparedStatement ps , HousingListing listing) throws SQLException {
        ps.setTimestamp(10, Timestamp.valueOf(listing.getListingHousingAvailableOn()));
        ps.setLong(11, listing.getListingHousingSize());
        ps.setString(12, listing.getListingHousingType().getHousingTypeStringValue());
        ps.setInt(13, listing.getListingHousingNumberOfBedroom());
        ps.setInt(14, listing.getListingHousingNumberOfBathroom());
        ps.setBoolean(15, listing.isListingHousingHasParking());
        return ps;
    }

    private PreparedStatement getJobPSReady(PreparedStatement ps , JobListing listing) throws SQLException {
        ps.setString(10, listing.getListingJobType().getEmploymentTypeString());
        ps.setString(11, listing.getListingJobTitle());
        ps.setString(12, listing.getListingJobCompanyName());
        return ps;
    }

    private PreparedStatement getCommunityPSReady(PreparedStatement ps , CommunityListing listing) throws SQLException {
        ps.setLong(10, listing.getListingEventNumberOfTicket());
        ps.setTimestamp(11, Timestamp.valueOf(listing.getListingEventDate()));
        ps.setString(12, listing.getListingEventVenue());
        return ps;
    }

    private PreparedStatement getSalePSReady(PreparedStatement ps , SaleListing listing) throws SQLException {
        ps.setString(10, listing.getListingSaleMake());
        ps.setString(11, listing.getListingSaleModel());
        ps.setString(12, listing.getListingSaleSize());
        ps.setString(13, listing.getListingSaleCondition().getListingConditionStringValue());
        return ps;
    }

    private PreparedStatement getPSListingBaseOnCategory(String listingCategory, PreparedStatement ps, Listing listing) throws SQLException {
        if(listingCategory.equalsIgnoreCase(Category.HOUSING.getCategoryTypeString())){
            return getHousingPSReady(ps, (HousingListing) listing);
        }else if(listingCategory.equalsIgnoreCase(Category.JOB.getCategoryTypeString())) {
            return getJobPSReady(ps, (JobListing) listing);
        }else if(listingCategory.equalsIgnoreCase(Category.COMMUNITY.getCategoryTypeString())) {
            return getCommunityPSReady(ps, (CommunityListing) listing);
        }else if(listingCategory.equalsIgnoreCase(Category.SALE.getCategoryTypeString())) {
            return getSalePSReady(ps, (SaleListing) listing);
        }
        return ps;
    }

    @Override
    public int saveListingImage(Long listingID, ListingPhoto listingPhoto) {
        String sql = "INSERT INTO rapidvit_listing_img(listingID, listingPhotoName, listingPhotoPath) VALUES(?,?,?)";
        return jdbcTemplate.update(sql,listingID, listingPhoto.getListingPhotoName(), listingPhoto.getListingPhotoPath());
    }

    @Override
    public Optional<Listing> getListingByID(Long listingID) {
        String listingSQL = "SELECT * FROM rapidvit_listing WHERE listingID =?";
        return jdbcTemplate.query(listingSQL, new ListingRowMapper(), listingID)
                .stream().findFirst();
    }

    @Override
    public List<Listing> getAllListing() {
        String listingSQL = "SELECT * FROM rapidvit_listing";
        return jdbcTemplate.query(listingSQL, new ListingRowMapper());
    }

    @Override
    public List<Listing> getAllListingByCategory(String category) {
        String listingSQL = "SELECT * FROM rapidvit_listing WHERE listingCategory =?";
        String listingPictSQL = "SELECT * FROM rapidvit_listing_img WHERE listingID =?";

        List<Listing> listings = jdbcTemplate.query(listingSQL, new ListingRowMapper(),category);
        if(listings.size()>0) {
            listings.forEach(listing ->
                    listing.setListingPhotos(jdbcTemplate.query(listingPictSQL, new ListingPhotoRowMapper(), listing.getListingID())));
        }
        return listings;
    }

    @Override
    public List<Listing> getAllListingBySubCategory(String category) {
        return null;
    }

    @Override
    public List<Listing> getAllListingByUserID(Long listingOwnerID) {
        String listingSQL = "SELECT * FROM rapidvit_listing WHERE listingOwnerID =?";
        String listingPhotoSQL = "SELECT * FROM rapidvit_listing_img WHERE listingID =?";

        List<Listing> listings = jdbcTemplate.query(listingSQL, new ListingRowMapper(),listingOwnerID);
        if(listings.size()>0) {
            listings.forEach(listing ->
                    listing.setListingPhotos(jdbcTemplate.query(listingPhotoSQL, new ListingPhotoRowMapper(), listing.getListingID())));
        }
        return listings;
    }

    @Override
    public List<Listing> getAllListingForOneUser(String category) {
        return null;
    }

    @Override
    public int updateListing(Long listingID, ListingForm listing) {
        String housingSQL = "UPDATE rapidvit_listing SET listingTitle=?, listingCategory=?, listingSubCategory=?, listingCity=?, listingDescription=?, listingPrice=?, listingUpdatedDate=?, listingHousingAvailableOn=?, listingHousingSize=?, listingHousingType=?, listingHousingNumberOfBedroom=?, listingHousingNumberOfBathroom=?, listingHousingHasParking=?";
        String jobSQL = "UPDATE rapidvit_listing SET listingTitle=?, listingCategory=?, listingSubCategory=?, listingCity=?, listingDescription=?, listingPrice=?, listingUpdatedDate=?, listingJobType=?, listingJobTitle=?, listingJobCompanyName=?";
        String communitySQL = "UPDATE rapidvit_listing SET listingTitle=?, listingCategory=?, listingSubCategory=?, listingCity=?, listingDescription=?, listingPrice=?, listingUpdatedDate=?, listingEventNumberOfTicket=?, listingEventDate=?, listingEventVenue=?";
        String  saleSQL= "UPDATE rapidvit_listing SET listingTitle=?, listingCategory=?, listingSubCategory=?, listingCity=?, listingDescription=?, listingPrice=?, listingUpdatedDate=?, listingSaleMake=?, listingSaleModel=?, listingSaleSize=?, listingSaleCondition=?";
        String  serviceSQL= "UPDATE rapidvit_listing SET listingTitle=?, listingCategory=?, listingSubCategory=?, listingCity=?, listingDescription=?, listingPrice=?, listingUpdatedDate=?";

        if(listing.getListingCategory().equalsIgnoreCase(Category.HOUSING.getCategoryTypeString())){
            return jdbcTemplate.update(housingSQL, listing.getListingTitle(), listing.getListingCategory(), listing.getListingSubCategory(), listing.getListingCity(), listing.getListingDescription(), listing.getListingPrice(), Timestamp.valueOf(LocalDateTime.now()), listing.getListingHousingAvailableOn(), listing.getListingHousingSize(), listing.getListingHousingType(), listing.getListingHousingNumberOfBedroom(), listing.getListingHousingNumberOfBathroom(), listing.isListingHousingHasParking(), listingID);
        }else if(listing.getListingCategory().equalsIgnoreCase(Category.JOB.getCategoryTypeString())) {
            return jdbcTemplate.update(jobSQL, listing.getListingTitle(), listing.getListingCategory(), listing.getListingSubCategory(), listing.getListingCity(), listing.getListingDescription(), listing.getListingPrice(), Timestamp.valueOf(LocalDateTime.now()), listing.getListingJobTitle(), listing.getListingJobTitle(),listing.getListingJobCompanyName() ,listingID);
        }else if(listing.getListingCategory().equalsIgnoreCase(Category.COMMUNITY.getCategoryTypeString())) {
            return jdbcTemplate.update(communitySQL, listing.getListingTitle(), listing.getListingCategory(), listing.getListingSubCategory(), listing.getListingCity(), listing.getListingDescription(), listing.getListingPrice(), Timestamp.valueOf(LocalDateTime.now()),listing.getListingEventNumberOfTicket(), listing.getListingEventDate(),  listing.getListingEventVenue(), listingID);
        }else if(listing.getListingCategory().equalsIgnoreCase(Category.SALE.getCategoryTypeString())) {
            return jdbcTemplate.update(saleSQL, listing.getListingTitle(), listing.getListingCategory(), listing.getListingSubCategory(), listing.getListingCity(), listing.getListingDescription(), listing.getListingPrice(), Timestamp.valueOf(LocalDateTime.now()), listing.getListingSaleMake(), listing.getListingSaleModel(), listing.getListingSaleSize(), listing.getListingSaleCondition(), listingID);
        }else if(listing.getListingCategory().equalsIgnoreCase(Category.SERVICE.getCategoryTypeString())) {
            return jdbcTemplate.update(serviceSQL, listing.getListingTitle(), listing.getListingCategory(), listing.getListingSubCategory(), listing.getListingCity(), listing.getListingDescription(), listing.getListingPrice(), Timestamp.valueOf(LocalDateTime.now()), listingID);
        }
        return 0;
    }

    @Override
    public int deleteListing(Long listingID) {
        String sql = "DELETE FROM rapidvit_listing WHERE listingID = ?";
        return jdbcTemplate.update(sql,listingID);
    }



}
