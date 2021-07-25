create role shop_admin login password 'admin';

create database shop;
grant create on database shop TO shop_admin;

--\c shop_core

create schema shop_core;

create schema shop_other;

alter role shop_admin in database shop set search_path to shop_core, shop_other;
-- Exit and enter again to update the search path
--show search_path;