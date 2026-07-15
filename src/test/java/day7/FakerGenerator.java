package day7;


import com.github.javafaker.Faker;
import org.testng.annotations.Test;

public class FakerGenerator {

//    For more information on java faker library. It gives information on what all the data can be generated
//    - https://github.com/dius/java-faker

    @Test
public void fakeDataGenerator(){
    Faker faker = new Faker();
    String name = faker.name().fullName();
    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    String userName = faker.name().username();
    String paswd = faker.internet().password();
    String mailId = faker.internet().emailAddress();
    String phoneNumber = faker.phoneNumber().cellPhone();
    System.out.printf("""
        Full Name      : %s
        First Name     : %s
        Username       : %s
        Password       : %s
        Email Address  : %s
        Phone Number   : %s
        """,
            name,
            firstName,
            lastName,
            paswd,
            mailId,
            phoneNumber);
}



}
