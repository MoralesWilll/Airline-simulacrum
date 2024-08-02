package pack;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Passenger {

    private int idpassenger;
    private String name;
    private String lastName;
    private String dni;

    public int getIdpassenger() {
        return idpassenger;
    }

    public void setIdpassenger(int idpassenger) {
        this.idpassenger = idpassenger;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Passenger(String name, String lastName, String dni) {
        this.name = name;
        this.lastName = lastName;
        this.dni = dni;
    }

    public Passenger() {
    }

    public static void deletePassenger(int id, Connection c){
        if (c != null){
            String sql = "DELETE from Airline.passenger WHERE idpassenger = ?";
            try(PreparedStatement pstmt = c.prepareStatement(sql)){
                pstmt.setInt(1, id);
                pstmt.executeUpdate();
                System.out.println("Deleted");
            } catch (SQLException e){
                e.printStackTrace();
            }
        } else {
            System.out.println("Failed connection");
        }
    }

    public static void addPassenger(Passenger p, Connection c){
        if (c != null){
            String sql = "INSERT into Airline.passenger (name, lastName, dni) VALUES (?, ?, ?)";
            try(PreparedStatement pstmt = c.prepareStatement(sql)){
                pstmt.setString(1, p.getName());
                pstmt.setString(2, p.getLastName());
                pstmt.setString(3, p.getDni());
                pstmt.executeUpdate();
                System.out.println("Added");
            } catch (SQLException e){
                e.printStackTrace();
            }
        } else {
            System.out.println("Failed connection");
        }
    }

    public static void editPassenger(Connection c, int id, String name, String lastName, String dni){
        if (c != null){
            String sql = "UPDATE Airline.passenger SET name = ?, lastName = ?, dni = ? WHERE idpassenger = ?";
            try(PreparedStatement pstmt = c.prepareStatement(sql)){
                pstmt.setString(1, name);
                pstmt.setString(2, lastName);
                pstmt.setString(3, dni);
                pstmt.setInt(4, id);
                pstmt.executeUpdate();
                System.out.println("Updated");
            } catch (SQLException e){
                e.printStackTrace();
            }
        } else {
            System.out.println("Failed connection");
        }
    }

    public static void searchPassengerByName(String name, Connection c){
        if (c != null){
            String sql = "SELECT * from Airline.passenger WHERE name LIKE ?";
            try (PreparedStatement pstmt = c.prepareStatement(sql)){
                pstmt.setString(1, name);
                ResultSet r = pstmt.executeQuery();

                System.out.printf("%-10s %-30s %-30s %-15s%n", "ID", "Name", "Last Name", "DNI");
                System.out.println("----------------------------------------------------------------------------------");

                while (r.next()) {
                    int id = r.getInt("idpassenger");
                    String firstName = r.getString("name");
                    String lastName = r.getString("lastName");
                    String dni = r.getString("dni");

                    System.out.printf("%-10d %-30s %-30s %-15s%n", id, firstName, lastName, dni);
                }

            } catch (SQLException e){
                e.printStackTrace();
            }
        } else {
            System.out.println("Failed conection");
        }
    }

}
