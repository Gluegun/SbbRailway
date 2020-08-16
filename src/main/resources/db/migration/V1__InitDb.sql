create database if not exists railway;
use railway;

drop table if exists passengers;
drop table if exists roles;
drop table if exists route_station;
drop table if exists routes;
drop table if exists schedule;
drop table if exists stations;
drop table if exists stations_for_trains;
drop table if exists tickets;
drop table if exists trains;

create table passengers
(
    id         integer      not null auto_increment,
    active     bit          not null,
    birth_date date         not null,
    first_name varchar(255) not null,
    last_name  varchar(255) not null,
    password   varchar(255) not null,
    username   varchar(255) not null,
    primary key (id)
) engine = MyISAM;

create table roles
(
    passenger_id integer not null,
    roles        varchar(255)
) engine = MyISAM;

create table route_station
(
    route_id   integer not null,
    station_id integer not null,
    primary key (route_id, station_id)
) engine = MyISAM;

create table routes
(
    id integer not null auto_increment,
    primary key (id)
) engine = MyISAM;

create table schedule
(
    id             integer not null auto_increment,
    departure_time time,
    station_id     integer not null,
    train_id       integer not null,
    primary key (id)
) engine = MyISAM;

create table stations
(
    id   integer not null auto_increment,
    name varchar(255),
    primary key (id)
) engine = MyISAM;

create table stations_for_trains
(
    id         integer not null,
    station_id integer not null auto_increment,
    train_id   integer not null,
    primary key (station_id, train_id)
) engine = MyISAM;

create table tickets
(
    id            integer not null auto_increment,
    departureTime time,
    passenger_id  integer not null,
    train_id      integer not null,
    primary key (id)
) engine = MyISAM;

create table trains
(
    id           integer      not null auto_increment,
    seats_amount integer      not null,
    number       varchar(255) not null,
    primary key (id)
) engine = MyISAM;


alter table passengers
    add constraint UK_username unique (username(200));
alter table trains
    add constraint UK_number unique (number (200));
alter table roles
    add constraint FK_passenger foreign key (passenger_id) references passengers (id);
alter table route_station
    add constraint FK_station foreign key (station_id) references stations (id);
alter table route_station
    add constraint FK_route foreign key (route_id) references routes (id);
alter table schedule
    add constraint FK_station foreign key (station_id) references stations (id);
alter table schedule
    add constraint FK_train foreign key (train_id) references trains (id);
alter table stations_for_trains
    add constraint FK_station foreign key (station_id) references stations (id);
alter table stations_for_trains
    add constraint FK_train foreign key (train_id) references trains (id);
alter table tickets
    add constraint FK_passenger foreign key (passenger_id) references passengers (id);
alter table tickets
    add constraint FK_train foreign key (train_id) references trains (id);

