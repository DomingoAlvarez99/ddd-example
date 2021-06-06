create role shop_admin login password 'admin';

create database shop_core;
grant create on database shop_core TO shop_admin;

\c sonarqube

create schema shop;

--show search_path;
alter role shop_admin in database shop_core set search_path to shop;