CREATE TABLE if not exists ecotechgroup_erp.customer_region
(
    customer_id BIGINT NOT NULL,
    region_id   BIGINT NOT NULL,
    CONSTRAINT pk_customer_region PRIMARY KEY (customer_id, region_id),
    constraint fk_customer_region
        foreign key (customer_id) references ecotechgroup_erp.customer,
    constraint fk_customer_region2
        foreign key (region_id) references ecotechgroup_erp.region
);

CREATE TABLE if not exists ecotechgroup_erp.region
(
    id          BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name        VARCHAR(50)                             NOT NULL,
    description VARCHAR(1000),
    CONSTRAINT pk_region PRIMARY KEY (id)
);

CREATE TABLE if not exists ecotechgroup_erp.region_user
(
    region_id   BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    CONSTRAINT pk_region_user PRIMARY KEY (region_id, user_id),
    CONSTRAINT fk_region_user foreign key (region_id) references ecotechgroup_erp.region,
    CONSTRAINT fk_region_user2 foreign key (user_id) references ecotechgroup_erp."user"
    );