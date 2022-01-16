create database `db-univer`;

use `db-univer`;

create table `groups`
(
    id   int auto_increment
        primary key,
    name varchar(25) charset utf8 not null
);

create table users
(
    id       int auto_increment
        primary key,
    username varchar(255) not null,
    age      int          null,
    email    varchar(255) not null
);

create table users_groups
(
    user_id  int not null,
    group_id int not null,
    constraint users_groups_ibfk_1
        foreign key (user_id) references users (id),
    constraint users_groups_ibfk_2
        foreign key (group_id) references `groups` (id)
);

create index group_id
    on users_groups (group_id);

create index user_id
    on users_groups (user_id);