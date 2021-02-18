USE [HorseRacing]
GO

INSERT INTO [dbo].[racing_map]
           ([id_ippo]
           ,[date_ride]
           ,[num_ride]
           ,[id_horse]
           ,[id_jokey]
           ,[id_coach]
           ,[weight]
           ,[last_ride]
           ,[distance]
           ,[rating]
           ,[prize_place])
     VALUES
           (<id_ippo, int,>
           ,<date_ride, date,>
           ,<num_ride, int,>
           ,<id_horse, int,>
           ,<id_jokey, int,>
           ,<id_coach, int,>
           ,<weight, int,>
           ,<last_ride, date,>
           ,<distance, int,>
           ,<rating, numeric(5,2),>
           ,<prize_place, int,>)
GO

