package entity;

import java.sql.Date;

public class Horse {
    private int id;
    private String name;
    private Date birth = Date.valueOf("2021-01-08"); /** not null,*/
    private String sex = "Жеребец"; /** not null,*/
    private int idStud = 1;/** not null*/

    public Horse(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Horse() {
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setIdStud(int idStud) { this.idStud = idStud;
    }
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public Date getBirth() {
        return birth;
    }

    public String getSex() {
        return sex;
    }

    public int getIdStud() {
        return idStud;
    }

    @Override
    public String toString() {
        return "Horse: \n{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birth=" + birth +
                ", sex='" + sex + '\'' +
                ", idStud=" + idStud +
                "\n}";
    }
}
