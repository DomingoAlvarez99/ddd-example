create table shop.article (
    id bigint not null primary key,
    description varchar(600) not null,
    name varchar(255) not null,
    price double precision not null,
    stock bigint not null,
    uuid varchar(36) not null unique
);

alter table shop.article owner to shop_admin;
grant all on table shop.article to shop_admin;

create sequence shop.article_seq
    start with 100
    increment by 100
    cache 1;

alter sequence shop.article_seq owner to shop_admin;
grant all on shop.article_seq to shop_admin;
alter table shop.article alter column id set default nextval('article_seq');
--alter sequence article_seq restart;