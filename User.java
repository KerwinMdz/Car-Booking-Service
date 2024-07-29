import java.util.UUID;

public class User {
    private String firstName;
    private String lastName;
    private final UUID userID;

    //Constructor
    public User(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
        this.userID = UUID.randomUUID();
    }

    //Getters
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public UUID getUserID(){
        return userID;
    }

    //Setters
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    @Override
    public String toString(){
        return "First Name: " + firstName + " \n" + "Last Name: " + lastName + " \n" +
                "UserID: " + userID;
    }
}
