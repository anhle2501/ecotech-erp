alter table ecotechgroup_erp.customer
    alter column name set not null;

create unique index customer_name_uindex
    on ecotechgroup_erp.customer (name);

create unique index customer_code_uindex
    on ecotechgroup_erp.customer (code);

alter table ecotechgroup_erp.customer
    alter column phone set not null;

alter table ecotechgroup_erp.customer
    alter column create_by set not null;

alter table ecotechgroup_erp.customer
    alter column create_at set not null;

alter table ecotechgroup_erp.product
    alter column name set not null;

create unique index product_name_uindex
    on ecotechgroup_erp.product (name);

