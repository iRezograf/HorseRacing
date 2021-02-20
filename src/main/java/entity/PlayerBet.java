package entity;

import java.sql.Date;

public class PlayerBet {
    int    id;
    String lastName;
    String firstName;
    int    idIppodrom = 1;
    String ippodromName;
    Date   dateRide;
    int    numRide;
    int    idHorse;
    String horse;
    int    idTypeBet;
    String typeBet;
    int    bet;
    double rate;
    int    payout;
    /** amount = 12*/

    public void setId(int id) {
        this.id = id;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setIdHorse(int idHorse) {
        this.idHorse = idHorse;
    }

    public void setIdTypeBet(int idTypeBet) {
        this.idTypeBet = idTypeBet;
    }

    public void setHorse(String horse) {
        this.horse = horse;
    }

    public void setTypeBet(String typeBet) {
        this.typeBet = typeBet;
    }

    public void setBet(int bet) {
        this.bet = bet;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public void setPayout(int payout) {
        this.payout = payout;
    }

    public void setIdIppodrom(int idIppodrom) {
        this.idIppodrom = idIppodrom;
    }

    public void setIppodromName(String ippodromName) {
        this.ippodromName = ippodromName;
    }
    public int getIdIppodrom() {
        return idIppodrom;
    }

    public int getNumRide() {
        return numRide;
    }

    public void setNumRide(int numRide) {
        this.numRide = numRide;
    }

    public void setDateRide(Date dateRide) {
        this.dateRide = dateRide;
    }

    public Date getDateRide() {
        return dateRide;
    }

    public String getIppodromName() {
        return ippodromName;
    }

    public int getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public int getIdHorse() {
        return idHorse;
    }

    public String getHorse() {
        return horse;
    }

    public int getIdTypeBet() {
        return idTypeBet;
    }

    public String getTypeBet() {
        return typeBet;
    }

    public int getBet() {
        return bet;
    }

    public double getRate() {
        return rate;
    }

    public int getPayout() {
        return payout;
    }

    @Override
    public String toString() {
        return "PlayerBet\n" +
                "{" +
                "\nid=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ",\n id_ippodrom=" + idIppodrom +
                ", ippodromName='" + ippodromName + '\'' +
                ",\n dateRide='" + dateRide + '\'' +
                ", num_ride='" + numRide + '\'' +
                ",\n idHorse=" + idHorse +
                ", horse='" + horse + '\'' +
                ",\n idTypeBet=" + idTypeBet +
                ", typeBet='" + typeBet + '\'' +
                ", bet='" + bet + '\'' +
                ",\n rate='" + rate + '\'' +
                ",\n payout='" + payout + '\'' +
                "\n}";
    }
}
