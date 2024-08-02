package pack;

import java.sql.*;

public class Reservation {

    private int idReservation;
    private int idPassenger;
    private int idFlight;
    private Date reservationDate;
    private String seat;

    public int getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(int idReservation) {
        this.idReservation = idReservation;
    }

    public int getIdPassenger() {
        return idPassenger;
    }

    public void setIdPassenger(int idPassenger) {
        this.idPassenger = idPassenger;
    }

    public int getIdFlight() {
        return idFlight;
    }

    public void setIdFlight(int idFlight) {
        this.idFlight = idFlight;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public Reservation(int idPassenger, int idFlight, Date reservationDate, String seat) {
        this.idPassenger = idPassenger;
        this.idFlight = idFlight;
        this.reservationDate = reservationDate;
        this.seat = seat;
    }

    public Reservation() {
    }

    public static void deleteReservation(int id, Connection c){
        if (c != null){
            String sql = "DELETE from Ariline.reservation WHERE idreservation = ?";
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

    public static void addReservation(Reservation r, Connection c){
        if (c != null){
            String sql = "INSERT into Airline.reservation (idpassenger, idflight, reservationDate, seat) VALUES (?, ?, ?, ?)";
            try(PreparedStatement pstmt = c.prepareStatement(sql)){
                pstmt.setInt(1, r.getIdPassenger());
                pstmt.setInt(2, r.getIdFlight());
                pstmt.setDate(3, r.getReservationDate());
                pstmt.setString(4, r.getSeat());
                pstmt.executeUpdate();
                System.out.println("added");
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public static void searchReservationsByFlight(int id, Connection c){
        if(c != null){
            String sql = "SELECT * from Airline.reservation WHERE idflight = ?";
            try(PreparedStatement pstmt = c.prepareStatement(sql)){
                pstmt.setInt(1, id);
                ResultSet r = pstmt.executeQuery();

                System.out.printf("%-15s %-20s %-20s %-12s %-10s%n", "Reservation ID", "Passenger ID", "Flight ID", "Reservation Date", "Seat");
                System.out.println("-------------------------------------------------------------------------------");

                while (r.next()) {
                    int idreservation = r.getInt("idreservation");
                    String passengerName = r.getString("idpassenger");
                    String passengerLastName = r.getString("idflight");
                    Date reservationDate = r.getDate("reservationDate");
                    String seat = r.getString("seat");

                    System.out.printf("%-15d %-20s %-20s %-16s %-10s%n",
                            idreservation, passengerName, passengerLastName, reservationDate.toString(), seat);
                }

            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public static void updateReservation(Connection c, int id, int idPassenger, int idFlight, Date reservationDate, String seat){
        if (c != null){
            String sql = "UPDATE Airline.reservation SET idpassenger = ?, idflight = ?, reservationDate = =, seat = ? WHERE idreservation = ?";
            try(PreparedStatement pstmt = c.prepareStatement(sql)){
                pstmt.setInt(1, idPassenger);
                pstmt.setInt(2, idFlight);
                pstmt.setDate(3, reservationDate);
                pstmt.setString(4, seat);
                pstmt.setInt(5, id);
                pstmt.executeUpdate();
                System.out.println("Updated");
            } catch (SQLException e){
                e.printStackTrace();
            }
        } else {
            System.out.println("Failed connection");
        }
    }

}
