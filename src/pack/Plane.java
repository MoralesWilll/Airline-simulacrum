package pack;

import java.sql.*;

public class Plane {

        private int idPlane;
        private String model;
        private int capacity;

        public Plane(String model, int capacity) {
            this.model = model;
            this.capacity = capacity;
        }

        public int getIdPlane() {
            return idPlane;
        }

        public void setIdPlane(int idPlane) {
            this.idPlane = idPlane;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public int getCapacity() {
            return capacity;
        }

        public void setCapacity(int capacity) {
            this.capacity = capacity;
        }

        public Plane() {
        }

    public static void addPlane(Plane p, Connection c){
        if(c != null) {
            String sql = "INSERT INTO Airline.plane (model, capacity) VALUES (?, ?)";
            try (PreparedStatement pstmt = c.prepareStatement(sql)) {
                pstmt.setString(1, p.getModel());
                pstmt.setInt(2, p.getCapacity());
                pstmt.executeUpdate();
                System.out.println("Added");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Failed connection");
        }
    }

    public static void searchPlane(int i, Connection c) {
        if(c != null) {
            String sql = "SELECT * FROM Airline.plane WHERE idplane = ?";
            try (PreparedStatement pstmt = c.prepareStatement(sql)) {
                pstmt.setInt(1, i); // idplane
                try (ResultSet r = pstmt.executeQuery()) {
                    while (r.next()) {
                        int id = r.getInt("idplane");
                        String model = r.getString("model");
                        String capacity = r.getString("capacity");

                        System.out.printf("%-10d %-30s %-10s%n", id, model, capacity);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("Failed connection");
        }
    }

    public static void deletePlane(int id, Connection c){
        if(c != null) {
            String sql = "DELETE from Airline.plane WHERE idplane = ? ";
            try (PreparedStatement pstmt = c.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Erased");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Failed connection");
        }
    }

    public static void updatePlane(int id, Connection c, String model, int capacity){
        if(c != null){
            String sql = "UPDATE Airline.plane SET model = ?, capacity = ? WHERE idplane = ?";
            try(PreparedStatement pstmt = c.prepareStatement(sql)){
                pstmt.setString(1, model);
                pstmt.setInt(2, capacity);
                pstmt.setInt(3, id);
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
