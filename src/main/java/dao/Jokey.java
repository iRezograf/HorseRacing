package dao;

public class Jokey {
    Long id;
    String name;

    public Jokey(Long id, String name) {
        this.id = id;
        this.name = name;
    }


    @Override
    public String toString() {
        return "Jokey: {" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}