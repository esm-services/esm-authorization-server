-- Database: AUTH_SERVER_DB

-- DROP DATABASE "AUTH_SERVER_DB";

CREATE DATABASE "AUTH_SERVER_DB"
    WITH 
    OWNER = rdurgesh
    ENCODING = 'UTF8'
    LC_COLLATE = 'English_United States.1252'
    LC_CTYPE = 'English_United States.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = 2;

COMMENT ON DATABASE "AUTH_SERVER_DB"
    IS 'DB for authentication and authorization';

GRANT ALL ON DATABASE "AUTH_SERVER_DB" TO rdurgesh;

GRANT TEMPORARY, CONNECT ON DATABASE "AUTH_SERVER_DB" TO PUBLIC;
--------------------------------------------------------------------------

drop table if exists oauth_client_details;
create table oauth_client_details (
  client_id VARCHAR(255) PRIMARY KEY,
  resource_ids VARCHAR(255),
  client_secret VARCHAR(255),
  scope VARCHAR(255),
  authorized_grant_types VARCHAR(255),
  web_server_redirect_uri VARCHAR(255),
  authorities VARCHAR(255),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096),
  autoapprove VARCHAR(255)
);

INSERT INTO oauth_client_details(client_id,resource_ids,client_secret,scope,authorized_grant_types,
web_server_redirect_uri,authorities,access_token_validity,refresh_token_validity,additional_information,
autoapprove)VALUES('commandlineinterface','rest_api','commandlineinterface','read,write','password,refresh_token',
'http://localhost:8080/home','ROLE_CLIENT,ROLE_TRUSTED_CLIENT',120,600,'trusted','yes');

ALTER TABLE oauth_client_details DROP COLUMN additional_information;
ALTER TABLE oauth_client_details ADD COLUMN additional_information VARCHAR(50);
 
drop table if exists oauth_client_token;
create table oauth_client_token (
  token_id VARCHAR(255),
  token BYTEA,
  authentication_id VARCHAR(255) PRIMARY KEY,
  user_name VARCHAR(255),
  client_id VARCHAR(255)
);
 
drop table if exists oauth_access_token;
create table oauth_access_token (
  token_id VARCHAR(255),
  token BYTEA,
  authentication_id VARCHAR(255) PRIMARY KEY,
  user_name VARCHAR(255),
  client_id VARCHAR(255),
  authentication BYTEA,
  refresh_token VARCHAR(255)
);
 
drop table if exists oauth_refresh_token;
create table oauth_refresh_token (
  token_id VARCHAR(255),
  token BYTEA,
  authentication BYTEA
);
 
drop table if exists oauth_code;
create table oauth_code (
  code VARCHAR(255),
  authentication BYTEA
);
 
drop table if exists oauth_approvals;
create table oauth_approvals (
    userId VARCHAR(255),
    clientId VARCHAR(255),
    scope VARCHAR(255),
    status VARCHAR(10),
    expiresAt TIMESTAMP,
    lastModifiedAt TIMESTAMP
);
 
drop table if exists clientDetails;
create table clientDetails (
  appId VARCHAR(256) PRIMARY KEY,
  resourceIds VARCHAR(256),
  appSecret VARCHAR(256),
  scope VARCHAR(256),
  grantTypes VARCHAR(256),
  redirectUrl VARCHAR(256),
  authorities VARCHAR(256),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additionalInformation VARCHAR(4096),
  autoApproveScopes VARCHAR(256)
);

CREATE SEQUENCE S_USERS START 1001;
drop table if exists T_USERS;
CREATE TABLE T_USERS(
	USER_ID BIGINT PRIMARY KEY,
	USERNAME VARCHAR(10) NOT NULL UNIQUE,
	PASSWORD VARCHAR(10) NOT NULL,
	ENABLED BOOLEAN
);

ALTER TABLE T_USERS ALTER COLUMN PASSWORD TYPE VARCHAR(256);

CREATE SEQUENCE S_ROLES START 1001;
drop table if exists T_ROLES;
CREATE TABLE T_ROLES(
   ROLE_ID BIGINT PRIMARY KEY,
   ROLE_NAME VARCHAR(10) UNIQUE
);

DROP TABLE IF EXISTS T_USER_ROLE;
CREATE TABLE T_USER_ROLE(
  USER_ID BIGINT NOT NULL,
  ROLE_ID BIGINT NOT NULL,
  PRIMARY KEY(USER_ID,ROLE_ID),
  CONSTRAINT FK_USER FOREIGN KEY(USER_ID) REFERENCES T_USERS(USER_ID),
  CONSTRAINT FK_ROLE FOREIGN KEY(ROLE_ID) REFERENCES T_ROLES(ROLE_ID)
);

