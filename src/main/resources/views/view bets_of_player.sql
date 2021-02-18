USE [HorseRacing]
GO

SELECT [last_n]
      ,[first_n]
      ,[date_ride]
      ,[num_ride]
      ,[horse]
      ,[type_bet]
      ,[bet(roubles)]
      ,[rate]
      ,[payout]
  FROM [dbo].[bets_of_player]
GO

