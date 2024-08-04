CREATE SCHEMA IF NOT EXISTS ecotechgroup_erp;

SET SCHEMA ecotechgroup_erp;


create table ecotechgroup_erp.permission
(
    id          bigserial
        constraint idx_16422_primary
            primary key,
    name        varchar(45),
    description varchar(1000)
);

create unique index idx_16422_name_unique
    on ecotechgroup_erp.permission (name);

create table ecotechgroup_erp.role
(
    id          bigserial
        constraint idx_16434_primary
            primary key,
    name        varchar(45) not null,
    description varchar(1000)
);


create unique index idx_16434_name_unique
    on ecotechgroup_erp.role (name);

create table ecotechgroup_erp.role_permission
(
    role_id       bigint not null
        constraint fk_role_id
            references ecotechgroup_erp.role
            on update restrict on delete restrict,
    permission_id bigint not null
        constraint fk_privilege_id
            references ecotechgroup_erp.permission
            on update cascade on delete restrict
);

create index idx_16440_fk_privilege_id_idx
    on role_permission (permission_id);

create table ecotechgroup_erp."user"
(
    id             bigserial
        constraint idx_16444_primary
            primary key,
    user_name      varchar(45)   not null,
    password       varchar(1000) not null,
    first_name     varchar(45),
    last_name      varchar(45),
    full_name      varchar(255),
    mobile_phone   varchar(45),
    description    varchar(1000),
    enable         boolean default true,
    non_expired    boolean default true,
    non_lock       boolean default true,
    pw_non_expired boolean default true
);


create table ecotechgroup_erp.customer
(
    id                 bigserial
        constraint idx_16400_primary
            primary key,
    code               varchar(45),
    name               varchar(200),
    address            varchar(1000),
    phone              varchar(45),
    tax_code           varchar(45),
    created_by         bigint
        constraint fk_create_by
            references ecotechgroup_erp."user"
            on update cascade on delete restrict,
    created_date       timestamp with time zone,
    description        varchar(1000),
    last_modified_by   bigint
        constraint fk_last_modified_by
            references ecotechgroup_erp."user"
            on update cascade on delete restrict,
    last_modified_date timestamp with time zone,
    id_user_belong     bigint
        constraint fk_user
            references ecotechgroup_erp."user"
);



create table ecotechgroup_erp.payment_type
(
    id  bigserial
        constraint idx_16414_primary
            primary key,
    name               varchar(45),
    description        varchar(1000),
    debt_day                integer default 0,
    created_date       timestamp with time zone,
    last_modified_date timestamp with time zone,
    created_by         bigint
        constraint fk_payment_type_on_created_by
            references ecotechgroup_erp."user",
    last_modified_by   bigint
        constraint fk_payment_type_on_last_modified_by
            references ecotechgroup_erp."user"
);


create table ecotechgroup_erp.an_order
(
    id                 bigserial
        constraint idx_16391_primary
            primary key,
    customer_id        bigint
        constraint fk_customer_id
            references customer
            on update cascade on delete restrict,
    description        varchar(1000),
    create_at          timestamp with time zone,
    payment_type_id    bigint
        constraint fk_payment_type_id
            references payment_type
            on update cascade on delete restrict,
    user_id            bigint
        constraint fk_user_id
            references ecotechgroup_erp."user"
            on update cascade on delete restrict,
    is_confirm         boolean default false,
    confirm_at         timestamp with time zone,
    confirm_by         bigint
        constraint fk_confirm_by
            references ecotechgroup_erp."user"
            on update cascade on delete restrict,
    last_modified_date timestamp with time zone,
    last_modified_by   bigint
        constraint fk_an_order_on_last_modified_by
            references ecotechgroup_erp."user",
    total_price        bigint  default 0
);



create table ecotechgroup_erp.product
(
    id                 bigserial
        constraint idx_16427_primary
            primary key,
    code               varchar(45),
    name               varchar(100),
    description        varchar(1000),
    unit               varchar(10),
    last_modified_by   bigint
        constraint fk_last_modified_by2
            references ecotechgroup_erp."user"
            on update cascade on delete restrict,
    last_modified_date timestamp with time zone,
    created_date       timestamp with time zone,
    created_by         bigint
        constraint fk_product_on_created_by
            references ecotechgroup_erp."user"
);


create table ecotechgroup_erp.order_product
(
    id         bigserial
        constraint idx_16407_primary
            primary key,
    order_id   bigint
        constraint fk_order_id
            references an_order
            on update cascade on delete cascade,
    product_id bigint
        constraint fk_product_id
            references product
            on update cascade on delete restrict,
    price      bigint default '0'::bigint,
    total      bigint default 0,
    quantity   integer
);



create unique index product_name_uindex
    on ecotechgroup_erp.product (name);

create unique index product_code_uindex
    on ecotechgroup_erp.product (code);

create unique index idx_16444_user_name_unique
    on ecotechgroup_erp."user" (user_name);

create table ecotechgroup_erp.user_role
(
    user_id_au bigint not null
        constraint fk_user_id_au
            references ecotechgroup_erp."user"
            on update cascade on delete cascade,
    role_id    bigint not null
        constraint fk_user_role_on_role
            references ecotechgroup_erp.role
);


create table flyway_schema_history
(
    installed_rank integer                 not null
        constraint flyway_schema_history_pk
            primary key,
    version        varchar(50),
    description    varchar(200)            not null,
    type           varchar(20)             not null,
    script         varchar(1000)           not null,
    checksum       integer,
    installed_by   varchar(100)            not null,
    installed_on   timestamp default now() not null,
    execution_time integer                 not null,
    success        boolean                 not null
);


create index flyway_schema_history_s_idx
    on flyway_schema_history (success);

create table region
(
    id          bigint generated by default as identity
        constraint pk_region
            primary key,
    name        varchar(50) not null
        constraint ukixr2itih2n9q41fv3qx6mbkrp
            unique,
    description varchar(1000)
);


create table customer_region
(
    customer_id bigint not null
        constraint fk_customer_region
            references ecotechgroup_erp.customer,
    region_id   bigint not null
        constraint fk_customer_region2
            references region,
    constraint pk_customer_region
        primary key (customer_id, region_id)
);



create table ecotechgroup_erp.region_user
(
    region_id bigint not null
        constraint fk_region_user
            references region,
    user_id   bigint not null
        constraint fk_region_user2
            references ecotechgroup_erp."user",
    constraint pk_region_user
        primary key (region_id, user_id)
);