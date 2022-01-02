create table shop_core.category (
    id varchar(36) not null unique,
    name varchar(255) not null,
    parent_id varchar(36) not null unique,
    constraint category_pk primary key (id),
    constraint article_category_parent_fk foreign key (parent_id) references shop_core.category(id)
);

alter table shop_core.category owner to shop_admin;
grant all on table shop_core.category to shop_admin;