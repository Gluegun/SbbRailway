insert into passengers (active, birth_date, first_name, last_name, password, username)
values (1, STR_TO_DATE('19/02/1991', '%d/%m/%Y %H:%i:%s'), 'Ivan', 'Titov', '1', '1');
insert into roles (passenger_id, roles)
values (1, 'ADMIN');
insert into passengers (active, birth_date, first_name, last_name, password, username)
values (1, STR_TO_DATE('20/05/1949', '%d/%m/%Y %H:%i:%s'), 'Petr', 'Smirnov', '1', '2');
insert into roles (passenger_id, roles)
values (2, 'ADMIN');
insert into passengers (active, birth_date, first_name, last_name, password, username)
values (1, STR_TO_DATE('07/04/1978', '%d/%m/%Y %H:%i:%s'), 'Maria', 'Veselova', '1', '3');
insert into roles (passenger_id, roles)
values (3, 'USER');
insert into passengers (active, birth_date, first_name, last_name, password, username)
values (1, STR_TO_DATE('14/10/2000', '%d/%m/%Y %H:%i:%s'), 'Alexandra', 'Mishina', '1', '4');
insert into roles (passenger_id, roles)
values (4, 'USER');
insert into passengers (active, birth_date, first_name, last_name, password, username)
values (1, STR_TO_DATE('12/09/1987', '%d/%m/%Y %H:%i:%s'), 'Maxim', 'Ivanov', '1', '5');
insert into roles (passenger_id, roles)
values (5, 'USER');


insert into stations (name)
values ('GHY1');
insert into stations (name)
values ('GHY2');
insert into stations (name)
values ('GHY3');
insert into stations (name)
values ('GHY4');
insert into stations (name)
values ('GHY5');
insert into stations (name)
values ('GHY6');

insert into trains(seats_amount, number)
values (1, 'dsg1');
insert into trains(seats_amount, number)
values (15, 'dsg2');

insert into schedule(departure_time, station_id, train_id)
values ('10:00', 1, 1);
insert into schedule(departure_time, station_id, train_id)
values ('11:00', 2, 1);
insert into schedule(departure_time, station_id, train_id)
values ('12:00', 3, 1);
insert into schedule(departure_time, station_id, train_id)
values ('13:00', 6, 1);
insert into schedule(departure_time, station_id, train_id)
values ('19:10', 3, 2);
insert into schedule(departure_time, station_id, train_id)
values ('20:00', 4, 2);
insert into schedule(departure_time, station_id, train_id)
values ('21:00', 5, 2);
insert into schedule(departure_time, station_id, train_id)
values ('22:00', 6, 2);

INSERT INTO tickets(departureTime, passenger_id, train_id)
VALUES ('10:00', 2, 1);

INSERT INTO tickets(departureTime, passenger_id, train_id)
VALUES ('11:00', 4, 2);
INSERT INTO tickets(departureTime, passenger_id, train_id)
VALUES ('11:10', 1, 1);
