create table project (id bigint generated by default as identity, description varchar(255), name varchar(255), primary key (id));
create table task (id bigint generated by default as identity, description varchar(255), name varchar(255), status varchar(255), project_id bigint, primary key (id));

create table blabla (id bigint generated by default as identity, description varchar(255), name varchar(255), primary key (id));
