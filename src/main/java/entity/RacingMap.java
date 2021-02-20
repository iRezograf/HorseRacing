package entity;

import java.sql.Date;

public class RacingMap {
    int id_ippodrom = 1;
    String ippodromName;
    Date date_ride;
    int num_ride = 1;
    int id_horse;
    String horseName;
    Date birth;
    String sex;
    int id_jokey;
    String jokeyName;
    int id_coach;
    String coachName;
    int weight = 0;
    Date last_ride;
    int distance =1600;
    double rating = 1.0 ;
    int prize_place = 1;
    /** amount = 17 */

    public void setIppodromName(String ippodromName) {
        this.ippodromName = ippodromName;
    }

    public void setHorseName(String horseName) {
        this.horseName = horseName;
    }

    public void setJokeyName(String jokeyName) {
        this.jokeyName = jokeyName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }
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

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public void setSex(String sex) {
        this.sex = sex;
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

    public int getId_ippodrom() {
        return id_ippodrom;
    }
    public String getIppodromName() {
        return ippodromName;
    }

    public Date getDate_ride() {
        return date_ride;
    }

    public int getNum_ride() {
        return num_ride;
    }

    public int getId_horse() {
        return id_horse;
    }

    public String getHorseName() {
        return horseName;
    }

    public Date getBirth() {
        return birth;
    }

    public String getSex() {
        return sex;
    }
    public int getId_jokey() {
        return id_jokey;
    }

    public String getJokeyName() {
        return jokeyName;
    }

    public int getId_coach() {
        return id_coach;
    }

    public String getCoachName() {
        return coachName;
    }

    public int getWeight() {
        return weight;
    }

    public Date getLast_ride() {
        return last_ride;
    }

    public int getDistance() {
        return distance;
    }

    public double getRating() {
        return rating;
    }

    public int getPrize_place() {
        return prize_place;
    }

    @Override
    public String toString() {
        return  "\nRacingMap:\n{" +
                "\nid_ippodrom=" + id_ippodrom +
                ", ippodromName='" + ippodromName + '\'' +
                "\n, date_ride=" + date_ride +
                ", num_ride=" + num_ride +
                "\n, id_horse=" + id_horse +
                ", horseName='" + horseName + '\'' +
                "\n, birth=" + birth +
                ", sex='" + sex + '\'' +
                "\n, id_jokey=" + id_jokey +
                ", jokeyName='" + jokeyName + '\'' +
                "\n, id_coach=" + id_coach +
                ", coachName='" + coachName + '\'' +
                "\n, weight=" + weight +
                "\n, last_ride=" + last_ride +
                "\n, distance=" + distance +
                "\n, rating=" + rating +
                "\n, prize_place=" + prize_place +
                "\n}";
    }
}
