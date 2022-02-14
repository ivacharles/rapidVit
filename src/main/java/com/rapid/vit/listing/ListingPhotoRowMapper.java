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
        return new ListingPhoto(
                rs.getLong("listingPhotoID"),
                rs.getLong("listingID"),
                rs.getString("listingPhotoName"),
                rs.getString("listingPhotoPath")
        );
    }
}
