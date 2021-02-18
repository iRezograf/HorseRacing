create table racing_map
(
    id_ippo     int  not null
        constraint FK_racing_map_ippo
            references ippo
            on delete cascade,
    date_ride   date not null,
    num_ride    int  not null,
    id_horse    int  not null
        constraint FK_racing_map_horse
            references horse
            on delete cascade,
    id_jokey    int  not null
        constraint FK_racing_map_jokey
            references jokey
            on delete cascade,
    id_coach    int  not null
        constraint FK_racing_map_coach
            references coach
            on delete cascade,
    weight      int  not null,
    last_ride   date,
    distance    int,
    rating      numeric(5, 2),
    prize_place int,
    constraint PK_racing_map
        primary key (id_ippo, date_ride, num_ride, id_horse)
)
go

create index IX_racing_map
    on racing_map (id_ippo)
go


