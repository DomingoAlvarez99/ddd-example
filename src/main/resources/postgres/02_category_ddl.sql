create sequence shop.category_seq
    start with 100
    increment by 100
    cache 1;

alter sequence shop.category_seq owner to shop_admin;
grant all on shop.category_seq to shop_admin;
--alter sequence category_seq restart;

create table shop.category (
    id bigint not null default nextval('shop.category_seq'),
    name varchar(255) not null,
    parent_id bigint null,
    uuid varchar(36) not null unique,
    created_at timestamp not null,
    updated_at timestamp not null,
    constraint category_pk primary key (id),
    constraint article_category_parent_fk foreign key (parent_id) references category(id)
);

alter table shop.category owner to shop_admin;
grant all on table shop.category to shop_admin;