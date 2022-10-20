create sequence hibernate_sequence start 1 increment 1;
create table geo_coordinate_entity
(
    id  int8 not null,
    lat float8,
    lon float8,
    primary key (id)
);
create table hop_arrival_entity
(
    id          int8 not null,
    code        varchar(255),
    date_time   timestamp,
    description varchar(255),
    parcel_id   int8,
    primary key (id)
);
create table hop_entity
(
    id                      int8 not null,
    code                    varchar(255),
    description             varchar(255),
    hop_type                varchar(255),
    location_name           varchar(255),
    processing_delay_mins   int4,
    location_coordinates_id int8,
    primary key (id)
);
create table parcel_entity
(
    id              int8 not null,
    delivery_status int4,
    tracking_id     varchar(255),
    weight          float4,
    fk_recipient    int8 not null,
    fk_sender       int8,
    primary key (id)
);
create table parcel_entity_future_hops
(
    parcel_entity_id int8 not null,
    future_hops_id   int8 not null
);
create table parcel_entity_visited_hops
(
    parcel_entity_id int8 not null,
    visited_hops_id  int8 not null
);
create table recipient_entity
(
    id          int8 not null,
    city        varchar(255),
    country     varchar(255),
    name        varchar(255),
    postal_code varchar(255),
    street      varchar(255),
    primary key (id)
);
create table truck_entity
(
    id              int8 not null,
    number_plate    varchar(255),
    region_geo_json varchar(255),
    primary key (id)
);
create table warehouse_entity
(
    id    int8 not null,
    level int4,
    primary key (id)
);
create table warehouse_entity_next_hops
(
    warehouse_entity_id int8 not null,
    next_hops_id        int8 not null
);
create table warehouse_next_hops_entity
(
    id              int8 not null,
    traveltime_mins int4,
    hop_entity_id   int8,
    primary key (id)
);
alter table if exists parcel_entity_future_hops add constraint UK_h4xjheyukoru5935ud2l5cxbd unique (future_hops_id);
alter table if exists parcel_entity_visited_hops add constraint UK_6tab2ksw3d26y6jmp4vnec37p unique (visited_hops_id);
alter table if exists warehouse_entity_next_hops add constraint UK_jmvaiawnuch0u668kq1ftxfwd unique (next_hops_id);
alter table if exists hop_arrival_entity add constraint FKti6b8aggtdaf7d70b7fpumjo6 foreign key (parcel_id) references parcel_entity;
alter table if exists hop_entity add constraint FK5yblle9ud3l1utu87rssr7oo8 foreign key (location_coordinates_id) references geo_coordinate_entity;
alter table if exists parcel_entity add constraint FKefq80o21mwb6ivi0ebf6fowwa foreign key (fk_recipient) references recipient_entity;
alter table if exists parcel_entity add constraint FK17kpdl2wfyys1pb8hle59n8k9 foreign key (fk_sender) references recipient_entity;
alter table if exists parcel_entity_future_hops add constraint FK8mpoahfsrgrhocuicuop6ve3h foreign key (future_hops_id) references hop_arrival_entity;
alter table if exists parcel_entity_future_hops add constraint FK83o1a9l7ywvowbge4v60coxwl foreign key (parcel_entity_id) references parcel_entity;
alter table if exists parcel_entity_visited_hops add constraint FKa8w2g90wjyv4p7apfr8d1seiu foreign key (visited_hops_id) references hop_arrival_entity;
alter table if exists parcel_entity_visited_hops add constraint FKjaar2x72rcxgiesumcdl838ds foreign key (parcel_entity_id) references parcel_entity;
alter table if exists warehouse_entity_next_hops add constraint FKqfddtogpttl84vm0tigx3u0nh foreign key (next_hops_id) references warehouse_next_hops_entity;
alter table if exists warehouse_entity_next_hops add constraint FK4ayh0rjwcdtptk828jfcbimxr foreign key (warehouse_entity_id) references warehouse_entity;
alter table if exists warehouse_next_hops_entity add constraint FKf3932rgu7ow5ylxad68wqdvlh foreign key (hop_entity_id) references hop_entity;
create sequence hibernate_sequence start 1 increment 1;
create table geo_coordinate_entity
(
    id  int8 not null,
    lat float8,
    lon float8,
    primary key (id)
);
create table hop_arrival_entity
(
    id          int8 not null,
    code        varchar(255),
    date_time   timestamp,
    description varchar(255),
    parcel_id   int8,
    primary key (id)
);
create table hop_entity
(
    id                      int8 not null,
    code                    varchar(255),
    description             varchar(255),
    hop_type                varchar(255),
    location_name           varchar(255),
    processing_delay_mins   int4,
    location_coordinates_id int8,
    primary key (id)
);
create table parcel_entity
(
    id              int8 not null,
    delivery_status int4,
    tracking_id     varchar(255),
    weight          float4,
    fk_recipient    int8 not null,
    fk_sender       int8,
    primary key (id)
);
create table parcel_entity_future_hops
(
    parcel_entity_id int8 not null,
    future_hops_id   int8 not null
);
create table parcel_entity_visited_hops
(
    parcel_entity_id int8 not null,
    visited_hops_id  int8 not null
);
create table recipient_entity
(
    id          int8 not null,
    city        varchar(255),
    country     varchar(255),
    name        varchar(255),
    postal_code varchar(255),
    street      varchar(255),
    primary key (id)
);
create table truck_entity
(
    id              int8 not null,
    number_plate    varchar(255),
    region_geo_json varchar(255),
    primary key (id)
);
create table warehouse_entity
(
    id    int8 not null,
    level int4,
    primary key (id)
);
create table warehouse_entity_next_hops
(
    warehouse_entity_id int8 not null,
    next_hops_id        int8 not null
);
create table warehouse_next_hops_entity
(
    id              int8 not null,
    traveltime_mins int4,
    hop_entity_id   int8,
    primary key (id)
);
alter table if exists parcel_entity_future_hops add constraint UK_h4xjheyukoru5935ud2l5cxbd unique (future_hops_id);
alter table if exists parcel_entity_visited_hops add constraint UK_6tab2ksw3d26y6jmp4vnec37p unique (visited_hops_id);
alter table if exists warehouse_entity_next_hops add constraint UK_jmvaiawnuch0u668kq1ftxfwd unique (next_hops_id);
alter table if exists hop_arrival_entity add constraint FKti6b8aggtdaf7d70b7fpumjo6 foreign key (parcel_id) references parcel_entity;
alter table if exists hop_entity add constraint FK5yblle9ud3l1utu87rssr7oo8 foreign key (location_coordinates_id) references geo_coordinate_entity;
alter table if exists parcel_entity add constraint FKefq80o21mwb6ivi0ebf6fowwa foreign key (fk_recipient) references recipient_entity;
alter table if exists parcel_entity add constraint FK17kpdl2wfyys1pb8hle59n8k9 foreign key (fk_sender) references recipient_entity;
alter table if exists parcel_entity_future_hops add constraint FK8mpoahfsrgrhocuicuop6ve3h foreign key (future_hops_id) references hop_arrival_entity;
alter table if exists parcel_entity_future_hops add constraint FK83o1a9l7ywvowbge4v60coxwl foreign key (parcel_entity_id) references parcel_entity;
alter table if exists parcel_entity_visited_hops add constraint FKa8w2g90wjyv4p7apfr8d1seiu foreign key (visited_hops_id) references hop_arrival_entity;
alter table if exists parcel_entity_visited_hops add constraint FKjaar2x72rcxgiesumcdl838ds foreign key (parcel_entity_id) references parcel_entity;
alter table if exists warehouse_entity_next_hops add constraint FKqfddtogpttl84vm0tigx3u0nh foreign key (next_hops_id) references warehouse_next_hops_entity;
alter table if exists warehouse_entity_next_hops add constraint FK4ayh0rjwcdtptk828jfcbimxr foreign key (warehouse_entity_id) references warehouse_entity;
alter table if exists warehouse_next_hops_entity add constraint FKf3932rgu7ow5ylxad68wqdvlh foreign key (hop_entity_id) references hop_entity;
create sequence hibernate_sequence start 1 increment 1;
create table geo_coordinate_entity
(
    id  int8 not null,
    lat float8,
    lon float8,
    primary key (id)
);
create table hop_arrival_entity
(
    id          int8 not null,
    code        varchar(255),
    date_time   timestamp,
    description varchar(255),
    parcel_id   int8,
    primary key (id)
);
create table hop_entity
(
    id                      int8 not null,
    code                    varchar(255),
    description             varchar(255),
    hop_type                varchar(255),
    location_name           varchar(255),
    processing_delay_mins   int4,
    location_coordinates_id int8,
    primary key (id)
);
create table parcel_entity
(
    id              int8 not null,
    delivery_status int4,
    tracking_id     varchar(255),
    weight          float4,
    fk_recipient    int8 not null,
    fk_sender       int8,
    primary key (id)
);
create table parcel_entity_future_hops
(
    parcel_entity_id int8 not null,
    future_hops_id   int8 not null
);
create table parcel_entity_visited_hops
(
    parcel_entity_id int8 not null,
    visited_hops_id  int8 not null
);
create table recipient_entity
(
    id          int8 not null,
    city        varchar(255),
    country     varchar(255),
    name        varchar(255),
    postal_code varchar(255),
    street      varchar(255),
    primary key (id)
);
create table truck_entity
(
    id              int8 not null,
    number_plate    varchar(255),
    region_geo_json varchar(255),
    primary key (id)
);
create table warehouse_entity
(
    id    int8 not null,
    level int4,
    primary key (id)
);
create table warehouse_entity_next_hops
(
    warehouse_entity_id int8 not null,
    next_hops_id        int8 not null
);
create table warehouse_next_hops_entity
(
    id              int8 not null,
    traveltime_mins int4,
    hop_entity_id   int8,
    primary key (id)
);
alter table if exists parcel_entity_future_hops add constraint UK_h4xjheyukoru5935ud2l5cxbd unique (future_hops_id);
alter table if exists parcel_entity_visited_hops add constraint UK_6tab2ksw3d26y6jmp4vnec37p unique (visited_hops_id);
alter table if exists warehouse_entity_next_hops add constraint UK_jmvaiawnuch0u668kq1ftxfwd unique (next_hops_id);
alter table if exists hop_arrival_entity add constraint FKti6b8aggtdaf7d70b7fpumjo6 foreign key (parcel_id) references parcel_entity;
alter table if exists hop_entity add constraint FK5yblle9ud3l1utu87rssr7oo8 foreign key (location_coordinates_id) references geo_coordinate_entity;
alter table if exists parcel_entity add constraint FKefq80o21mwb6ivi0ebf6fowwa foreign key (fk_recipient) references recipient_entity;
alter table if exists parcel_entity add constraint FK17kpdl2wfyys1pb8hle59n8k9 foreign key (fk_sender) references recipient_entity;
alter table if exists parcel_entity_future_hops add constraint FK8mpoahfsrgrhocuicuop6ve3h foreign key (future_hops_id) references hop_arrival_entity;
alter table if exists parcel_entity_future_hops add constraint FK83o1a9l7ywvowbge4v60coxwl foreign key (parcel_entity_id) references parcel_entity;
alter table if exists parcel_entity_visited_hops add constraint FKa8w2g90wjyv4p7apfr8d1seiu foreign key (visited_hops_id) references hop_arrival_entity;
alter table if exists parcel_entity_visited_hops add constraint FKjaar2x72rcxgiesumcdl838ds foreign key (parcel_entity_id) references parcel_entity;
alter table if exists warehouse_entity_next_hops add constraint FKqfddtogpttl84vm0tigx3u0nh foreign key (next_hops_id) references warehouse_next_hops_entity;
alter table if exists warehouse_entity_next_hops add constraint FK4ayh0rjwcdtptk828jfcbimxr foreign key (warehouse_entity_id) references warehouse_entity;
alter table if exists warehouse_next_hops_entity add constraint FKf3932rgu7ow5ylxad68wqdvlh foreign key (hop_entity_id) references hop_entity;
