package entity;

public class Stud {
    private int id;
    private String name;

    public Stud(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Stud() {
    }


    @Override
    public String toString() {
        return "Stud: {" +
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
