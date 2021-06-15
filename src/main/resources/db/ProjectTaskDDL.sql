-- we don't know how to generate root <with-no-name> (class Root) :(
create table CLIENT
(
    NAME VARCHAR not null,
    FAMILY_NAME VARCHAR not null,
    SECOND_NAME VARCHAR,
    ID INTEGER auto_increment
);

create unique index CLIENT_ID_UINDEX
    on CLIENT (ID);

alter table CLIENT
    add constraint CLIENT_PK
        primary key (ID);

create table ACCOUNT
(
    CURRENCY DECIMAL(19,4),
    ID INT auto_increment,
    ACCOUNT_NUMBER VARCHAR(20) not null,
    CLIENT_ID INT not null,
    constraint ACCOUNT_CLIENT_ID_FK
        foreign key (CLIENT_ID) references CLIENT (ID)
);

create unique index ACCOUNT_ID_UINDEX
    on ACCOUNT (ID);

alter table ACCOUNT
    add constraint ACCOUNT_PK
        primary key (ID);

create table CARD
(
    ID INT auto_increment,
    ACCOUNT_ID INT not null,
    CARD_NUMBER VARCHAR(16) not null,
    CARD_HOLDER VARCHAR,
    constraint CARD_ACCOUNT_ID_FK
        foreign key (ACCOUNT_ID) references ACCOUNT (ID)
);

create unique index CARD_CARD_NUMBER_UINDEX
    on CARD (CARD_NUMBER);

create unique index CARD_ID_UINDEX
    on CARD (ID);

alter table CARD
    add constraint CARD_PK
        primary key (ID);

