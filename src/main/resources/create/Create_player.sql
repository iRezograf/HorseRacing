create table player
(
    id       int identity
        constraint PK_player
            primary key,
    first_n  varchar(50) not null,
    last_n   varchar(50) not null,
    login    varchar(50),
    password varchar(50)
)
go


