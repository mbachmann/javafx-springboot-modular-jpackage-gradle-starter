

create table project (id bigint not null auto_increment, description varchar(255), name varchar(255), primary key (id) ) engine=InnoDB;

create table blabla (id bigint not null auto_increment, description varchar(255), name varchar(255), primary key (id) ) engine=InnoDB;

create table task (
       id bigint not null auto_increment,
        description varchar(255),
        name varchar(255),
        status varchar(255),
        project_id bigint,
        primary key (id)
    ) engine=InnoDB;

alter table task
       add constraint FK_project_task
       foreign key (project_id)
       references project (id);
