drop table if exists passport;
drop table if exists passport_office;
drop table if exists enrollee_statement_diploma;
drop table if exists enrollee_statement;
drop table if exists edu_document;
drop table if exists education_office;

create table passport_office (
	pass_office_id integer not null,
	pass_office_name varchar(100) not null,
	primary key (pass_office_id)
);

create table passport (
	passport_id varchar(10) not null,
	pass_surname varchar(50) not null,
	pass_name varchar(50) not null,
	pass_patronymic varchar(50),
	pass_seria integer not null,
	pass_number integer not null,
	pass_date date not null,
	pass_office integer not null,
	primary key (passport_id),
	foreign key (pass_office) references passport_office(pass_office_id) on delete restrict
);

create table education_office (
	edu_office_id integer not null,
	edu_office_name varchar(100) not null,
	primary key (edu_office_id)
);

create table edu_document (
	edu_document_id varchar(10) not null,
	edu_surname varchar(50) not null,
    edu_name varchar(50) not null,
    edu_patronymic varchar(50),
	edu_seria varchar(10) not null,
	edu_number varchar(30) not null,
	edu_date date not null,
	edu_office integer not null,
	primary key(edu_document_id),
	foreign key (edu_office) references education_office(edu_office_id) on delete restrict
);

create table enrollee_statement (
    enr_order_id serial not null,
    statement_status integer not null,
	statement_date timestamp not null,
    enr_name varchar(50) not null,
    enr_surname varchar(50) not null,
    enr_patronymic varchar(50),
    passport_seria integer not null,
    passport_number integer not null,
    passport_office_id integer not null,
    primary key (enr_order_id)
);

create table enrollee_statement_diploma (
	diploma_id serial not null,
    enrollee_statement_id integer not null,
    education_seria integer not null,
    education_number integer not null,
    education_office_id integer not null,
    primary key (diploma_id),
	foreign key (enrollee_statement_id) references enrollee_statement (enr_order_id) on delete restrict,
	foreign key (education_office_id) references education_office (edu_office_id) on delete restrict
);

create index idx_statement_status ON enrollee_statement(statement_status);
create index idx_statement_diploma_id ON enrollee_statement_diploma(enrollee_statement_id);