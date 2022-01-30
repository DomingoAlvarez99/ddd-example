create table shop_core.article (
    id varchar(36) not null unique,
    description varchar(600) not null,
    name varchar(200) not null,
    price double precision not null,
    stock smallint not null,
    category_id varchar(36),
    constraint article_pk primary key (id),
    constraint article_category_fk foreign key (category_id) references shop_core.category(id)
);

alter table shop_core.article owner to shop_admin;
grant all on table shop_core.article to shop_admin;