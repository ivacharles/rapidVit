
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

    CONSTRAINT pk_rapidvit_user PRIMARY KEY(user_id)
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
