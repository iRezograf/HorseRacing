package entity;

public class TypeBet {
    private int id;
    private String typeBet;
    private double rate;


    public TypeBet(int id, String typeBet, double rate) {
        this.id = id;
        this.typeBet = typeBet;
        this.rate = rate;
    }

    public TypeBet(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeBet() {
        return typeBet;
    }

    public void setTypeBet(String typeBet) {
        this.typeBet = typeBet;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }


    @Override
    public String toString() {
        return "TypeBet: \n{" +
                "id=" + id +
                ", typeBet='" + typeBet + '\'' +
                ", rate=" + rate +
                "\n}";
    }
}
