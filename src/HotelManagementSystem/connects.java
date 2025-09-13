package HotelManagementSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class connects {

    Connection connection;
    Statement statement;

    public connects(){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/HotelManagementSystem","root","12345");
            statement = connection.createStatement();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new connects();
    }
}
