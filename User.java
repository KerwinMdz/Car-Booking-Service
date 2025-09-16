import java.util.UUID;

public class User {
    private String firstName;
    private String lastName;
    public UUID userID;

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
}
