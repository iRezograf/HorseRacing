package entity;

import java.math.BigDecimal;
import java.sql.Date;

public class RacingMap {
    int id_ippodrom = 1;
    Date date_ride;
    int num_ride = 1;
    int id_horse;
    int id_jokey;
    int id_coach;
    int weight = 0;
    Date last_ride;
    int distance =1600;
    double rating = 1.0 ;
    int prize_place = 1;

    public void setId_ippodrom(int id_ippodrom) {
        this.id_ippodrom = id_ippodrom;
    }

    public void setDate_ride(Date date_ride) {
        this.date_ride = date_ride;
    }

    public void setNum_ride(int num_ride) {
        this.num_ride = num_ride;
    }

    public void setId_horse(int id_horse) {
        this.id_horse = id_horse;
    }

    public void setId_jokey(int id_jokey) {
        this.id_jokey = id_jokey;
    }

    public void setId_coach(int id_coach) {
        this.id_coach = id_coach;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setLast_ride(Date last_ride) {
        this.last_ride = last_ride;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setPrize_place(int prize_place) {
        this.prize_place = prize_place;
    }

    @Override
    public String toString() {
        return "RacingMap: \n{"+
                "id_ippodrom=" + id_ippodrom +
                ", date_ride=" + date_ride +
                ", num_ride="  + num_ride +
                ", id_horse="  + id_horse +
                ", id_jokey="  + id_jokey +
                ", id_coach="  + id_coach +
                ", weight="    + weight +
                ", last_ride=" + last_ride +
                ", distance="  + distance +
                ", rating="    + rating +
                ", prize_place=" + prize_place +
                '}';
    }
}
