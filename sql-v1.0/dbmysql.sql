/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2022/4/18 17:45:36                           */
/*==============================================================*/


drop table if exists "tbl_ middle";

drop table if exists tbl_classUse;

drop table if exists tbl_classroom;

drop table if exists tbl_dorm;

drop table if exists tbl_libSeat;

drop table if exists tbl_libUse;

drop table if exists tbl_library;

drop table if exists tbl_student;

drop table if exists tbl_tutor;

drop table if exists tbl_user;

/*==============================================================*/
/* Table: "tbl_ middle"                                         */
/*==============================================================*/
create table "tbl_ middle"
(
   id                   char(32) not null,
   stu_id               char(32),
   dorm_id              char(32),
   primary key (id)
);

/*==============================================================*/
/* Table: tbl_classUse                                          */
/*==============================================================*/
create table tbl_classUse
(
   id                   char(32) not null,
   stu_id               char(32),
   classroom_id         char(32),
   start_time           char(19),
   end_time             char(19),
   primary key (id)
);

/*==============================================================*/
/* Table: tbl_classroom                                         */
/*==============================================================*/
create table tbl_classroom
(
   id                   char(32) not null,
   room_no              varchar(255),
   build_name           varchar(255),
   elect_inf            varchar(255),
   is_use               varchar(8),
   is_open              char(2),
   is_clean             varchar(6),
   primary key (id)
);

/*==============================================================*/
/* Table: tbl_dorm                                              */
/*==============================================================*/
create table tbl_dorm
(
   id                   char(32) not null,
   doorno               varchar(255),
   water                int,
   power                int,
   size                 int,
   have_wc              char(2),
   remark               varchar(255),
   primary key (id)
);

/*==============================================================*/
/* Table: tbl_libSeat                                           */
/*==============================================================*/
create table tbl_libSeat
(
   id                   char(32) not null,
   floor                char(5),
   area                 varchar(255),
   seat_no              varchar(255),
   primary key (id)
);

/*==============================================================*/
/* Table: tbl_libUse                                            */
/*==============================================================*/
create table tbl_libUse
(
   id                   char(32) not null,
   use_people           char(32),
   start_time           char(19),
   end_time             char(19),
   use_no               char(32),
   primary key (id)
);

/*==============================================================*/
/* Table: tbl_library                                           */
/*==============================================================*/
create table tbl_library
(
   id                   char(32) not null,
   open                 char(19),
   close                char(19),
   is_clean             varchar(6),
   is_open              varchar(8),
   primary key (id)
);

/*==============================================================*/
/* Table: tbl_student                                           */
/*==============================================================*/
create table tbl_student
(
   id                   char(32) not null,
   sno                  char(10),
   class                varchar(255),
   enter_date           char(19),
   idcard               char(18),
   address              varchar(255),
   email                varchar(255),
   school_sys           char(3),
   remark               varchar(255),
   dorm_id              char(32),
   college              varchar(255),
   tutor_id             char(32),
   primary key (id)
);

/*==============================================================*/
/* Table: tbl_tutor                                             */
/*==============================================================*/
create table tbl_tutor
(
   id                   char(32) not null,
   address              varchar(255),
   college              varchar(255),
   dorm_id              char(32),
   remark               varchar(255),
   primary key (id)
);

/*==============================================================*/
/* Table: tbl_user                                              */
/*==============================================================*/
create table tbl_user
(
   id                   char(32) not null,
   username             varchar(20),
   password             varchar(255),
   salt                 varchar(255),
   name                 varchar(10),
   sex                  varchar(2),
   age                  int,
   tel                  varchar(255),
   ident                char(18),
   ident_id             char(32),
   primary key (id)
);

