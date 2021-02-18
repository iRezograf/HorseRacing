create table player_bet
(
    id_player   int  not null
        constraint FK_player_bet_player
            references player
            on delete cascade,
    id_ippo     int  not null,
    date_ride   date not null,
    num_ride    int  not null,
    id_horse    int  not null,
    id_type_bet int
        constraint FK_player_bet_type_bet
            references type_bet
            on delete cascade,
    bet         int,
    payout      int,
    constraint FK_player_bet_racing_map
        foreign key (id_ippo, date_ride, num_ride, id_horse) references racing_map
            on delete cascade
)
go

create index player_bet
    on player_bet (id_player, date_ride, num_ride, id_horse)
go

create index racing_map
    on player_bet (id_ippo, date_ride, num_ride, id_horse)
go

create index player_bet_type_bet
    on player_bet (id_type_bet)
go


