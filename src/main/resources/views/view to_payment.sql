USE [HorseRacing]
GO

SELECT [surname]
      ,[name]
      ,[date_ride]
      ,[num_ride]
      ,[horse]
      ,[prize_place]
      ,[type_bet]
      ,[bet(roubles)]
      ,[rate]
      ,[payout]
  FROM [dbo].[to_payment]

GO

