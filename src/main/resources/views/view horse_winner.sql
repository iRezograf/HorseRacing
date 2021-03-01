SELECT  horse.id,
        horse.name,
        horse.birth,
        horse.sex,
        racing_map.id_ippo,
        ippo.name AS Ippodrom,
        stud.id AS id_stu,
        stud.name AS Stud,
        racing_map.date_ride,
        racing_map.num_ride,
        racing_map.prize_place
FROM    horse INNER JOIN
        racing_map ON horse.id = racing_map.id_horse INNER JOIN
        stud ON horse.id_stud = stud.id INNER JOIN
        ippo ON racing_map.id_ippo = ippo.id
WHERE   (racing_map.prize_place = 1)