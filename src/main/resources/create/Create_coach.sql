create table coach
(
    id   int identity
        constraint PK_coach
            primary key,
    name varchar(100) not null
)
go


