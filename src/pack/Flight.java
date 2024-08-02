package pack;

import java.sql.*;

public class Flight {

    private int idflight;
    private String destiny;
    private Date departureDate;
    private Time departureTime;
    private int idplane;

    public Flight(String destiny, Date departureDate, Time departureTime, int idplane) {
        this.destiny = destiny;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.idplane = idplane;
    }

    public int getIdflight() { return idflight; }
    public void setIdflight(int idflight) { this.idflight = idflight; }
    public String getDestiny() { return destiny; }
    public void setDestiny(String destiny) { this.destiny = destiny; }

    public Date getDepartureDate() { return departureDate; }
    public void setDepartureDate(Date departureDate) { this.departureDate = departureDate; }

    public Time getDepartureTime() { return departureTime; }
    public void setDepartureTime(Time departureTime) { this.departureTime = departureTime; }

    public int getIdplane() { return idplane; }
    public void setIdplane(int idplane) { this.idplane = idplane; }

    public static void deleteFlight(int id, Connection c){
        if(c != null){
            String sql = "DELETE from Airline.flight WHERE idflight = ?";
            try(PreparedStatement pstmt = c.prepareStatement(sql)){
                pstmt.setInt(1, id);
                pstmt.executeUpdate();
                System.out.println("Erased");
            } catch (SQLException e){
                e.printStackTrace();
            }
        } else {
            System.out.println("Failed connection");
        }
    }

    public static void addFlight(Flight f, Connection c) {
        if (c != null) {
            String sql = "INSERT INTO Airline.flight (destiny, departureDate, departureTime, idplane) VALUES (?, ?, ?, ?)";

            try (PreparedStatement pstmt = c.prepareStatement(sql)) {

                pstmt.setString(1, f.getDestiny());
                pstmt.setDate(2, f.getDepartureDate());
                pstmt.setTime(3, f.getDepartureTime());
                pstmt.setInt(4, f.getIdplane());
                pstmt.executeUpdate();
                System.out.println("added");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Failed connection");
        }
    }

    public static void searchFlightsByDestiny(String destiny, Connection c) {
        if (c != null) {

            String sql = "SELECT * FROM Airline.flight WHERE destiny = ?";
            try (PreparedStatement pstmt = c.prepareStatement(sql)) {

                pstmt.setString(1, destiny);
                ResultSet rs = pstmt.executeQuery();

                System.out.printf("%-10s %-20s %-12s %-8s %-10s%n", "Flight ID", "Destiny", "Departure Date", "Departure Time", "Plane ID");
                System.out.println("--------------------------------------------------------------");

                while (rs.next()) {
                    int idflight = rs.getInt("idflight");
                    String flightDestiny = rs.getString("destiny");
                    Date departureDate = rs.getDate("departureDate");
                    Time departureTime = rs.getTime("departureTime");
                    int idplane = rs.getInt("idplane");

                    System.out.printf("%-10d %-20s %-14s %-14s %-10d%n",
                            idflight, flightDestiny, departureDate.toString(), departureTime.toString(), idplane);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Failed connection");
        }
    }

    public static void updateFlight(int id, Connection c, String destiny, Date departureDate, Time departureTime, int idplane){
        if (c != null){
            String sql = "UPDATE Airline.flight SET destiny = ?, date = ?, time = ?, idplane = ? WHERE idflight = ?";
            try(PreparedStatement pstmt = c.prepareStatement(sql)){
                pstmt.setString(1, destiny);
                pstmt.setDate(2, departureDate);
                pstmt.setTime(3, departureTime);
                pstmt.setInt(3, idplane);
                pstmt.setInt(5, id);
                pstmt.executeUpdate();
                System.out.println("Actualizado");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("Failed connection");
        }
    }

}