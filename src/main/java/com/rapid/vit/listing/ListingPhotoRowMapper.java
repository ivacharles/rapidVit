package com.rapid.vit.listing;

import com.rapid.vit.listing.model.ListingPhoto;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ListingPhotoRowMapper implements RowMapper<ListingPhoto> {
    @Override
    public ListingPhoto mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long pictID =rs.getLong("listingPhotoID");
        Long pictOwnerID =rs.getLong("listingID");
        String pictName = rs.getString("listingPhotoName");
        String pictPathName = rs.getString("listingPhotoPath");
        ListingPhoto listingPhoto = new ListingPhoto();
        listingPhoto.setListingPhotoID(pictID);
        listingPhoto.setListingID(pictOwnerID);
        listingPhoto.setListingPhotoName(pictName);
        listingPhoto.setListingPhotoPath(pictPathName);
        return listingPhoto;
    }
}
