USE [HorseRacing]
GO

SELECT  player.id,
       player.last_n,
       player.first_n,
       player_bet.date_ride,
       player_bet.num_ride,
       horse.name AS horse,
       type_bet.type_bet,
       player_bet.bet AS [bet(roubles)],
       type_bet.rate,
       player_bet.payout
FROM    player INNER JOIN
        player_bet ON player.id = player_bet.id_player INNER JOIN
        horse ON player_bet.id_horse = horse.id INNER JOIN
        type_bet ON player_bet.id_type_bet = type_bet.id
WHERE        (player.id = 41)
GO

