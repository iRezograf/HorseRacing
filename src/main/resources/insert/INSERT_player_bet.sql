USE [HorseRacing]
GO

INSERT INTO [dbo].[player_bet]
           ([id_player]
           ,[id_ippo]
           ,[date_ride]
           ,[num_ride]
           ,[id_horse]
           ,[id_type_bet]
           ,[bet]
           ,[payout])
     VALUES
           (<id_player, int,>
           ,<id_ippo, int,>
           ,<date_ride, date,>
           ,<num_ride, int,>
           ,<id_horse, int,>
           ,<id_type_bet, int,>
           ,<bet, int,>
           ,<payout, int,>)
GO

