USE [HorseRacing]
GO

SELECT        TOP (10) PERCENT jokey.name AS jokey, horse.name AS horse, ippo.name AS ippodrom, racing_map.date_ride, racing_map.distance, racing_map.prize_place
FROM            jokey INNER JOIN
                racing_map ON jokey.id = racing_map.id_jokey INNER JOIN
                horse ON racing_map.id_horse = horse.id INNER JOIN
                ippo ON racing_map.id_ippo = ippo.id
WHERE        (horse.id = 1)
ORDER BY racing_map.prize_place
GO

SELECT        TOP (10) PERCENT jokey.name AS jokey, horse.name AS horse, ippo.name AS ippodrom, racing_map.date_ride, racing_map.distance, racing_map.prize_place
FROM            jokey INNER JOIN
                racing_map ON jokey.id = racing_map.id_jokey INNER JOIN
                horse ON racing_map.id_horse = horse.id INNER JOIN
                ippo ON racing_map.id_ippo = ippo.id
WHERE        (racing_map.prize_place = 1)
ORDER BY jokey.id
GO
