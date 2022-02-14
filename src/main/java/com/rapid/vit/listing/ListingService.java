package com.rapid.vit.listing;

import com.rapid.vit.appUser.AppUserDetail;
import com.rapid.vit.appUser.AppUserRepo;
import com.rapid.vit.appUser.registration.AppUserResponse;
import com.rapid.vit.listing.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpServerErrorException;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class ListingService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ListingService.class);

    private final ListingRepo listingRepo;
    private final AppUserRepo userRepo;

    public ListingService(ListingRepo listingRepo, AppUserRepo userRepo) {
        this.listingRepo = listingRepo;
        this.userRepo = userRepo;
    }

    @Transactional
    public Optional<AppUserResponse> saveListing(ListingForm listingForm, String category) throws Throwable {
        Optional<AppUserResponse> appUserResponse = Optional.empty();
        Optional<AppUserDetail> userDetail = userRepo.findByUsername(listingForm.getListingOwnerUsername());//get the userDetail because we need the userID
        //TODO: avoid saving the listing in the db
        if (userDetail.isPresent()) {
            Listing listing = getListingBaseOnCategory(category,userDetail.get(),listingForm);
            if (listing==null){
                appUserResponse = Optional.of(new AppUserResponse(userDetail.get().getUserFName(), userDetail.get().getUsername(), userDetail.get().getUserPhoneNumber(), "Listing validation error"));
                return appUserResponse;
            }
            LOGGER.info("this is inside of ListingService / save listing and the listing is {}",listing);
            LOGGER.info("this is inside of ListingService and the user is {} ", userDetail);
            Long isListingSavedThenID = listingRepo.saveListing(userDetail.get().getUserID(), listing);
            LOGGER.info("this is inside of ListingService and the listingID is {} ", isListingSavedThenID);
            if (isListingSavedThenID != null) {
                //save the listing pictures
                listingForm.getListingPhotos().forEach(postPic -> listingRepo.saveListingImage(isListingSavedThenID, new ListingPhoto(postPic.getListingPhotoName(), postPic.getListingPhotoPath())));
                String msg = "Listing titled: '" + listingForm.getListingTitle() + "' is created";
                appUserResponse = Optional.of(new AppUserResponse(userDetail.get().getUserFName(), userDetail.get().getUsername(), userDetail.get().getUserPhoneNumber(), msg));
            }
        }
        return appUserResponse;
    }

    private Listing getListingBaseOnCategory(String category, AppUserDetail userDetail, ListingForm listingForm) throws Throwable {

        Listing listing = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime listingEventDate=null;
        if (category.equalsIgnoreCase(Category.COMMUNITY.getCategoryTypeString())) {
            try {
                listingEventDate = LocalDateTime.parse(listingForm.getListingEventDate(), formatter);
            }catch (DateTimeException e){
                LOGGER.error("String date format is invalid");
                throw new DateTimeException("String date format is invalid");
            }
            listing = new CommunityListing(userDetail, listingForm.getListingTitle(), listingForm.getListingCategory(), listingForm.getListingSubCategory(),
                    listingForm.getListingCity(), listingForm.getListingDescription(), listingForm.getListingPrice(), listingForm.getListingPhotos(),
                    true, LocalDateTime.now(),listingForm.getListingEventNumberOfTicket(), listingEventDate, listingForm.getListingEventVenue());
        } else if (category.equalsIgnoreCase(Category.HOUSING.getCategoryTypeString())) {
            LocalDateTime listingHousingAvailableOn = null;
            try {
                listingHousingAvailableOn = LocalDateTime.parse(listingForm.getListingHousingAvailableOn(), formatter);
            }catch (DateTimeException e){
                LOGGER.error("String date format is invalid");
                throw new DateTimeException("String date format is invalid");
            }
            listing = new HousingListing(userDetail, listingForm.getListingTitle(), listingForm.getListingCategory(), listingForm.getListingSubCategory(),
                    listingForm.getListingCity(), listingForm.getListingDescription(), listingForm.getListingPrice(), listingForm.getListingPhotos(),
                    true, LocalDateTime.now(),listingHousingAvailableOn, listingForm.getListingHousingSize(), HousingType.returnHousingType(listingForm.getListingHousingType()), listingForm.getListingHousingNumberOfBedroom(), listingForm.getListingHousingNumberOfBathroom(), listingForm.isListingHousingHasParking());
        } else if (category.equalsIgnoreCase(Category.JOB.getCategoryTypeString())) {
            listing = new JobListing(userDetail, listingForm.getListingTitle(), listingForm.getListingCategory(), listingForm.getListingSubCategory(),
                    listingForm.getListingCity(), listingForm.getListingDescription(), listingForm.getListingPrice(), listingForm.getListingPhotos(),
                    true, LocalDateTime.now(),EmploymentType.returnEnumStringValue(listingForm.getListingJobType()), listingForm.getListingJobTitle(),
                    listingForm.getListingJobCompanyName());
        } else if (category.equalsIgnoreCase(Category.SALE.getCategoryTypeString())) {
            listing = new SaleListing(userDetail, listingForm.getListingTitle(), listingForm.getListingCategory(), listingForm.getListingSubCategory(),
                    listingForm.getListingCity(), listingForm.getListingDescription(), listingForm.getListingPrice(), listingForm.getListingPhotos(),
                    true, LocalDateTime.now(),listingForm.getListingSaleMake(), listingForm.getListingSaleModel(), listingForm.getListingSaleSize(), ListingCondition.returnEnumStringValue(listingForm.getListingSaleCondition()));
        } else if (category.equalsIgnoreCase(Category.SERVICE.getCategoryTypeString())) {
            listing = new ServiceListing(userDetail, listingForm.getListingTitle(), listingForm.getListingCategory(), listingForm.getListingSubCategory(),
                    listingForm.getListingCity(), listingForm.getListingDescription(), listingForm.getListingPrice(), listingForm.getListingPhotos(),
                    true, LocalDateTime.now());
        }
        return listing;
    }

    public List<Listing> getAllListingForCategory(String category) {
        //is category valid
        Category categoryType = Category.returnCategoryEnum(category);
        if(categoryType != null) {
            return listingRepo.getAllListingByCategory(categoryType.getCategoryTypeString());
        }else {
            throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "category does not exist");
        }
    }

    public List<Listing> getAllListings() {
       return listingRepo.getAllListing();
    }

    public int deleteListingByID(Long listingID) {
        Optional<Listing> listing = listingRepo.getListingByID(listingID);
        return listing.map(value -> listingRepo.deleteListing(value.getListingID())).orElse(0);
    }

    public AppUserResponse updateListing(Long listingID, ListingForm listingForm) {
        AppUserResponse appUserResponse = null;
        Optional<Listing> listing = listingRepo.getListingByID(listingID);
        if (!listing.isPresent()) {
            throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "listing does not exist");
        }
        int result = listingRepo.updateListing(listingID, listingForm);
        if (result == 1) {
            String msg = "Listing titled: '" + listingForm.getListingTitle() + "' is updated";
            appUserResponse = new AppUserResponse(null, listingForm.getListingOwnerUsername(), null, msg);
        }
        return appUserResponse;

    }

    public List<Listing> findAllListingByUserID(String listingOwnerUsername) {
        Optional<AppUserDetail> userDetail = userRepo.findByUsername(listingOwnerUsername);//get the userDetail because we need the userID
        if(!userDetail.isPresent()){
            throw new UsernameNotFoundException(String.format("username %s not found", listingOwnerUsername));
        }
        return listingRepo.getAllListingByUserID(userDetail.get().getUserID());
    }
}