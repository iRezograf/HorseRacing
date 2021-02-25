package entity;

public class Ippo {
    private int id;
    private String name;

    public Ippo(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Ippo() {
    }


    @Override
    public String toString() {
        return "Ippodrom: {" +
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

