create table horse
(
    id      int identity
        constraint PK_horse
            primary key,
    name    varchar(50) not null,
    birth   date        not null,
    sex     varchar(20) not null,
    id_stud int         not null
        constraint FK_horse_stud
            references stud
            on delete cascade
)
go


