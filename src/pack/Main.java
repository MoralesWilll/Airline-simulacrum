package pack;

import java.sql.*;

import static pack.Flight.*;
import static pack.Plane.*;
import static pack.Reservation.*;
import static pack.Passenger.*;

public class Main {

    public static Connection openConnection() {

        String url = "jdbc:mysql://localhost:3306/Airline";
        String user = "root";
        String password = "password";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args){

    }

}
