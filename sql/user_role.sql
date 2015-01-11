create table user_roles(
username	varchar(20) not null,
rolename        varchar(20) not null,
foreign key(username) references users(username) on delete cascade,
primary key (username, rolename)
);

insert into user_roles values ('david', 'registered');
insert into user_roles values ('nestor', 'registered');
insert into user_roles values ('ruben', 'registered');
insert into user_roles values ('yifei', 'registered');
