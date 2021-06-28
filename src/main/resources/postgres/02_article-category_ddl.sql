create sequence shop.article_category_seq
    start with 100
    increment by 100
    cache 1;

alter sequence shop.article_category_seq owner to shop_admin;
grant all on shop.article_category_seq to shop_admin;
--alter sequence article_category_seq restart;

create table shop.article_category (
    id bigint not null default nextval('shop.article_category_seq'),
    article_id bigint not null,
    category_id bigint not null,
    uuid varchar(36) not null unique,
    created_at timestamp not null,
    updated_at timestamp not null,
    constraint article_category_pk primary key (id),
    constraint article_category_article_fk foreign key (article_id) references article(id),
    constraint article_category_category_fk foreign key (category_id) references category(id)
);

alter table shop.article_category owner to shop_admin;
grant all on table shop.article_category to shop_admin;