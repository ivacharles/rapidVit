
CREATE TABLE rapidvit_user(
    user_id BIGSERIAL,
    user_f_name VARCHAR(50) NOT NULL,
    user_l_name VARCHAR(50) NOT NULL,
    user_phone_number VARCHAR(50) NOT NULL,
    user_email_address VARCHAR(50) NOT NULL,
    user_pwd VARCHAR(255) NOT NULL,
    isUserVerified BOOLEAN,
    isUserActivated BOOLEAN,
    user_account_created_date TIMESTAMP,

    CONSTRAINT pk_rapidvit_user PRIMARY KEY(user_id),
    CONSTRAINT uk_rapidvit_user UNIQUE (user_email_address, user_phone_number)
);

CREATE TABLE rapidvit_role(
     role_id SERIAL,
     role_name VARCHAR(50) NOT NULL,
     role_desc VARCHAR(255) NOT NULL,

     CONSTRAINT pk_rapidvit_role PRIMARY KEY(role_id)
);

INSERT INTO rapidvit_role (role_name, role_desc)
VALUES
        ('SUPER_USER', 'Administrator'),
        ('REGULAR_USER', 'Regular user');

CREATE TABLE user_role (
     user_id BIGINT NOT NULL,
     role_id BIGINT NOT NULL,

     CONSTRAINT FK_USER_ID FOREIGN KEY (user_id) REFERENCES rapidvit_user (user_id),
     CONSTRAINT FK_ROLE_ID FOREIGN KEY (role_id) REFERENCES rapidvit_role (role_id)
);

CREATE TABLE user_email_token_confirmation (
     token_id BIGSERIAL NOT NULL,
     user_id BIGINT NOT NULL,
     token VARCHAR(255) NOT NULL,
     user_token_created_date TIMESTAMP,
     user_token_expired_date TIMESTAMP,
     user_token_confirmed_date TIMESTAMP,

     CONSTRAINT pk_email_token_conf PRIMARY KEY(token_id),
     CONSTRAINT FK_email_token_conf FOREIGN KEY (user_id) REFERENCES rapidvit_user (user_id)
);

CREATE TABLE rapidvit_listing(
    listingID                      BIGSERIAL    NOT NULL,
    listingOwnerID                 BIGINT       NOT NULL,
    listingTitle                   VARCHAR(255) NOT NULL,
    listingCategory                VARCHAR(100) NOT NULL,
    listingSubCategory             VARCHAR(100) NOT NULL,
    listingCity                    VARCHAR(125) NOT NULL,
    listingDescription             VARCHAR(500) NOT NULL,
    listingPrice                   DECIMAL,
    isListingVerified              BOOLEAN,
    listingCreatedDate             TIMESTAMP    NOT NULL,
    listingUpdatedDate             TIMESTAMP,
    listingPostedDate              TIMESTAMP,

    listingHousingAvailableOn      TIMESTAMP,
    listingHousingSize             BIGINT,
    listingHousingType             VARCHAR(100),
    listingHousingNumberOfBedroom  VARCHAR(100),
    listingHousingNumberOfBathroom VARCHAR(100),
    listingHousingHasParking       BOOLEAN,

    listingJobType                 VARCHAR(100),
    listingJobTitle                VARCHAR(100),
    listingJobCompanyName          VARCHAR(100),

    listingSaleMake                VARCHAR(100),
    listingSaleModel               VARCHAR(100),
    listingSaleSize                VARCHAR(100),
    listingSaleCondition           VARCHAR(100),

    listingEventNumberOfTicket     BIGINT,
    listingEventDate               TIMESTAMP,
    listingEventVenue              VARCHAR(120),

    CONSTRAINT pk_rapidvit_listing PRIMARY KEY(listingID),
    CONSTRAINT fk_rapidvit_listing FOREIGN KEY (listingOwnerID) REFERENCES rapidvit_user (user_id)
);

CREATE TABLE rapidvit_listing_img (
     listingPhotoID BIGSERIAL NOT NULL,
     listingID BIGINT NOT NULL,
     listingPhotoName VARCHAR(255) NOT NULL,
     listingPhotoPath VARCHAR(255) NOT NULL,

     CONSTRAINT pk_rapidvit_listing_img PRIMARY KEY(listingPhotoID),
     CONSTRAINT FK_rapidvit_listing_img FOREIGN KEY (listingID) REFERENCES rapidvit_listing(listingID)
);
