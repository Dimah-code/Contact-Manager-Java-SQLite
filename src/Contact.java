public class Contact {
    public int id = 0;
    public String firstName;
    public String lastName;
    public String phoneNumber;

    public Contact(String firstName, String lastName, String phoneNumber){
        id++;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }
    public String toString(){
        return (id + " | " + firstName + " " + lastName + " | " + phoneNumber);
    }
}
