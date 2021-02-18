USE [HorseRacing]
GO

UPDATE [dbo].[racing_map]
   SET [id_ippo] = <id_ippo, int,>
      ,[date_ride] = <date_ride, date,>
      ,[num_ride] = <num_ride, int,>
      ,[id_horse] = <id_horse, int,>
      ,[id_jokey] = <id_jokey, int,>
      ,[id_coach] = <id_coach, int,>
      ,[weight] = <weight, int,>
      ,[last_ride] = <last_ride, date,>
      ,[distance] = <distance, int,>
      ,[rating] = <rating, numeric(5,2),>
      ,[prize_place] = <prize_place, int,>
 WHERE <Search Conditions,,>
GO

