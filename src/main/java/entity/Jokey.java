package entity;

public class Jokey {
    private int id;
    private String name;

    public Jokey(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Jokey() {
    }


    @Override
    public String toString() {
        return "Jokey: {" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}