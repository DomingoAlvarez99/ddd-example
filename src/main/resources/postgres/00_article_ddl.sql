create sequence shop_core.article_seq
    start with 100
    increment by 100
    cache 1;

alter sequence shop_core.article_seq owner to shop_admin;
grant all on shop_core.article_seq to shop_admin;
--alter sequence article_seq restart;

create table shop_core.article (
    id bigint not null default nextval('shop_core.article_seq'),
    description varchar(600) not null,
    name varchar(255) not null,
    price double precision not null,
    stock bigint not null,
    uuid varchar(36) not null unique,
    created_at timestamp not null,
    updated_at timestamp not null,
    constraint article_pk primary key (id)
);

alter table shop_core.article owner to shop_admin;
grant all on table shop_core.article to shop_admin;

--create unique index article_uuid_idx
--    on shop.article using btree (uuid);