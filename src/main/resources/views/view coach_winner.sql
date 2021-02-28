USE [HorseRacing]
GO

SELECT        TOP (10) PERCENT dbo.coach.name AS coach, dbo.ippo.name AS ippodrom, dbo.racing_map.date_ride, dbo.jokey.name AS jokey, dbo.racing_map.prize_place
FROM            dbo.coach INNER JOIN
                dbo.racing_map ON dbo.coach.id = dbo.racing_map.id_coach INNER JOIN
                dbo.ippo ON dbo.racing_map.id_ippo = dbo.ippo.id INNER JOIN
                dbo.jokey ON dbo.racing_map.id_jokey = dbo.jokey.id
WHERE        (dbo.racing_map.prize_place = 1)
GO

SELECT        TOP (10) PERCENT dbo.coach.name AS coach, dbo.ippo.name AS ippodrom, dbo.racing_map.date_ride, dbo.jokey.name AS jokey, dbo.racing_map.prize_place
FROM            dbo.coach INNER JOIN
                dbo.racing_map ON dbo.coach.id = dbo.racing_map.id_coach INNER JOIN
                dbo.ippo ON dbo.racing_map.id_ippo = dbo.ippo.id INNER JOIN
                dbo.jokey ON dbo.racing_map.id_jokey = dbo.jokey.id
WHERE        (dbo.coach.id = 1)
ORDER BY dbo.racing_map.prize_place
GO