package entity;

public class BetsOfPlayer {
    int id;
    String last_n;
    String first_n;
    String date_ride;
    String num_ride;
    String horse;
    String type_bet;
    String bet;
    String rate;
    String payout;

    public void setId(int id) {
        this.id = id;
    }

    public void setLast_n(String last_n) {
        this.last_n = last_n;
    }

    public void setFirst_n(String first_n) {
        this.first_n = first_n;
    }

    public void setDate_ride(String date_ride) {
        this.date_ride = date_ride;
    }

    public void setNum_ride(String num_ride) {
        this.num_ride = num_ride;
    }

    public void setHorse(String horse) {
        this.horse = horse;
    }

    public void setType_bet(String type_bet) {
        this.type_bet = type_bet;
    }

    public void setBet(String bet) {
        this.bet = bet;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public void setPayout(String payout) {
        this.payout = payout;
    }

    @Override
    public String toString() {
        return "BetsOfPlayer:\n{" +
                "  id=" + id +
                ", last_n='" + last_n + '\'' +
                ", first_n='" + first_n + '\'' +
                ", date_ride='" + date_ride + '\'' +
                ", num_ride='" + num_ride + '\'' +
                ", horse='" + horse + '\'' +
                ", type_bet='" + type_bet + '\'' +
                ", bet='" + bet + '\'' +
                ", rate='" + rate + '\'' +
                ", payout='" + payout + '\'' +
                '}';
    }
}
