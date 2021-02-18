USE [HorseRacing]
GO

UPDATE [dbo].[player_bet]
   SET [id_player] = <id_player, int,>
      ,[id_ippo] = <id_ippo, int,>
      ,[date_ride] = <date_ride, date,>
      ,[num_ride] = <num_ride, int,>
      ,[id_horse] = <id_horse, int,>
      ,[id_type_bet] = <id_type_bet, int,>
      ,[bet] = <bet, int,>
      ,[payout] = <payout, int,>
 WHERE <Search Conditions,,>
GO

