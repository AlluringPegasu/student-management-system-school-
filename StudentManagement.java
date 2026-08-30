import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class StudentManagement {
    
    static Connection conn;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            conn = DBconnection.connect();
            System.out.println("Database Connected!");

            while (true) {
                System.out.println("\n--- Menu ---");
                System.out.println("1. Add Student");
                System.out.println("2. View Students");
                System.out.println("3. Update Email");
                System.out.println("4. Delete Student");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                
                int choice = scanner.nextInt();

                if (choice == 1) {
                    addStudent();
                } else if (choice == 2) {
                    viewStudents();
                } else if (choice == 3) {
                    updateStudent();
                } else if (choice == 4) {
                    deleteStudent();
                } else if (choice == 5) {
                    System.out.println("Closing program...");
                    break; 
                } else {
                    System.out.println("Wrong choice, try again.");
                }
            }
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void addStudent() {
        try {
            System.out.print("Enter new Student ID (number): ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter Student Name: ");
            String name = scanner.nextLine(); 
            
            System.out.print("Enter Email: ");
            String email = scanner.nextLine();
            
            System.out.print("Enter Department ID\n1-Computer Science,\n2-Mathematics,\n3-Business Administration\n4-Mechanical Engineering): ");
            int deptId = scanner.nextInt();
            scanner.nextLine();
            String sql = "INSERT INTO students VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setString(3, email);
            pstmt.setInt(4, deptId);
            
            pstmt.executeUpdate();
            System.out.println("Student added!");
            
        } catch (Exception e) {
            System.out.println("Error adding: " + e.getMessage());
            scanner.nextLine();
        }
    }

    public static void viewStudents() {
        try {
            String sql = "SELECT s.student_id, s.name, s.email, d.dept_name " +
                    "FROM students s " +
                    "INNER JOIN departments d ON s.dept_id = d.dept_id";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            System.out.println("\n--- All Students ---");
            while (rs.next()) {
                System.out.print("ID: " + rs.getInt("student_id"));
                System.out.print(" | Name: " + rs.getString("name"));
                System.out.print(" | Email: " + rs.getString("email"));
                System.out.println(" | Department: " + rs.getString("dept_name"));
            }
            
        } catch (Exception e) {
            System.out.println("Error viewing: " + e.getMessage());
        }
    }

    public static void updateStudent() {
        try {
            System.out.print("Enter the ID of the student to edit: ");
            int id = scanner.nextInt();
            
            System.out.print("Enter the NEW email: ");
            String newEmail = scanner.next();

            String sql = "UPDATE students SET email = ? WHERE student_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newEmail);
            pstmt.setInt(2, id);
            
            pstmt.executeUpdate();
            System.out.println("Email updated!");
            
        } catch (Exception e) {
            System.out.println("Error updating: " + e.getMessage());
        }
    }

    public static void deleteStudent() {
        try {
            System.out.print("Enter the ID of the student to delete: ");
            int id = scanner.nextInt();

            String sql = "DELETE FROM students WHERE student_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            
            pstmt.executeUpdate();
            System.out.println("Student deleted!");
            
        } catch (Exception e) {
            System.out.println("Error deleting: " + e.getMessage());
        }
    }
}