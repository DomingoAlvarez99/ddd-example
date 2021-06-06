create role sonarqube_admin login password 'admin';

create database sonarqube;
grant create on database sonarqube TO sonarqube_admin;

create schema sonar;
alter schema sonar owner to sonarqube_admin;

--show search_path;
alter role sonarqube_admin in database sonarqube set search_path to sonar;