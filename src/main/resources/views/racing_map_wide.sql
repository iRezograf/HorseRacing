SELECT  dbo.racing_map.id_ippo, dbo.ippo.name AS ippodrom,
        dbo.racing_map.date_ride, dbo.racing_map.num_ride,
        dbo.horse.id AS id_horse, dbo.horse.name AS Horse,
        dbo.horse.birth, dbo.horse.sex,
        dbo.racing_map.id_jokey AS Jokey, dbo.jokey.name,
        dbo.racing_map.id_coach AS Coach, dbo.racing_map.weight,
        dbo.racing_map.last_ride, dbo.racing_map.distance,
        dbo.racing_map.rating, dbo.racing_map.prize_place
FROM    dbo.racing_map INNER JOIN
        dbo.ippo ON dbo.racing_map.id_ippo = dbo.ippo.id INNER JOIN
        dbo.horse ON dbo.racing_map.id_horse = dbo.horse.id INNER JOIN
        dbo.jokey ON dbo.racing_map.id_jokey = dbo.jokey.id INNER JOIN
        dbo.coach ON dbo.racing_map.id_coach = dbo.coach.id
WHERE   (dbo.racing_map.id_ippo = 1)