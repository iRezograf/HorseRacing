package entity;

//import org.testng.annotations.Test;

public class Player {
    private int id;
    private String firstName = "";
    private String lastName = "" ;
    private String login = null;
    private String password = null;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Player: " +
                "\n{\n id=" + id +
                ",\n firstName='" + firstName + '\'' +
                ",\n surname='" + lastName + '\'' +
                ",\n login='" + login + '\'' +
                ",\n password='" + password + '\'' +
                '\n'+'}';
    }



}
