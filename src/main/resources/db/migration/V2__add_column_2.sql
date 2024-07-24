alter table ecotechgroup_erp.customer
    add id_user_belong bigint;

alter table ecotechgroup_erp.customer
    add constraint fk_user
        foreign key (id_user_belong) references ecotechgroup_erp."user";
        
 