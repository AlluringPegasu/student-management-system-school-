
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.sql.*;
import java.util.Scanner;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import net.miginfocom.swing.MigLayout;
import java.awt.Font;

public class mainpage extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private Connection conn;
    static Scanner scanner = new Scanner(System.in);
    public void setConnection(Connection conn) {
    	this.conn=conn;
    }

	/**
	 * Create the panel.
	 */
	public mainpage() {
		setLayout(new MigLayout("fill", "[80.00px][80.00px][80.00px][grow,fill]", "[20px][44.00px,grow][52.00px][19px][18px][24px][18px][26px][20px][grow][20px][10px][12px]"));
		
		JLabel lblNewLabel = new JLabel("Student ID");
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
		add(lblNewLabel, "cell 0 2,growx,aligny center");
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 12));
		add(lblNewLabel_1, "cell 0 4,growx,aligny center");
		
		JLabel lblNewLabel_2 = new JLabel("Email");
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.BOLD, 12));
		add(lblNewLabel_2, "cell 0 6,alignx left,aligny center");
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "cell 3 0 1 13,grow");
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Student ID", "Name", "Email", "Department"
			}
		));
		
		textField = new JTextField();
		add(textField, "cell 1 2 2 1,growx,aligny center");
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		add(textField_1, "cell 1 4 2 1,growx,aligny center");
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		add(textField_2, "cell 1 6 2 1,growx,aligny center");
		textField_2.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Segoe UI", Font.BOLD, 12));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Computer Science", "Mathematics,", "Business Administration", "Mechanical Engineering"}));
		add(comboBox, "cell 1 8 2 1,grow");
		
		
		
		JLabel lblNewLabel_3 = new JLabel("Department");
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.BOLD, 12));
		add(lblNewLabel_3, "cell 0 8,growx,aligny center");
		
		JLabel lblNewLabel_4 = new JLabel("");
		add(lblNewLabel_4, "cell 0 12 3 1,grow");
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
                    // Converting to proper data types
                    int studentId = Integer.parseInt(textField.getText());
                    String name = textField_1.getText();
                    String email = textField_2.getText();
                    int deptId = comboBox.getSelectedIndex()+1;
                    if(addStudent(studentId, deptId, name, email)==1) {
                    	lblNewLabel_4.setText("new entry added");
                    }
                    else {
                    lblNewLabel_4.setText("error adding new entry");}
                    
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid numeric Student ID.");
                    
                }
			}
		});
		add(btnNewButton, "cell 0 10,growx,aligny center");
		JButton btnNewButton_1 = new JButton("Update");
		btnNewButton_1.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
                    // Converting to proper data types
                    int studentId = Integer.parseInt(textField.getText());
                    String name = textField_1.getText();
                    String email = textField_2.getText();
                    int deptId = comboBox.getSelectedIndex()+1;
                    if(updateStudent(email,name,deptId,studentId)==1) {
                    	lblNewLabel_4.setText("entry updated");
                    }
                    else {
                    	lblNewLabel_4.setText("error updating entry");
                    }
                    
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid numeric Student ID.");

                }
			}
		});
		add(btnNewButton_1, "cell 1 10,growx,aligny center");
		
		
		JButton btnNewButton_3 = new JButton("Refresh Table");
		btnNewButton_3.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				String tblquery = "SELECT s.student_id, s.name, s.email, d.dept_name " +
	                    "FROM students s " +
	                    "INNER JOIN departments d ON s.dept_id = d.dept_id";
				PreparedStatement pst = conn.prepareStatement(tblquery);
		        ResultSet rs = pst.executeQuery();
		        table.setModel(DbUtils.resultSetToTableModel(rs));
				}catch(Exception e1) {
                    JOptionPane.showMessageDialog(null, e1.getMessage());
				}
				}
		});
		add(btnNewButton_3, "cell 0 0 3 1,growx,aligny center");
		
		JButton btnNewButton_2 = new JButton("Delete");
		btnNewButton_2.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int studentId = Integer.parseInt(textField.getText());
					if(deleteStudent(studentId)==1) {
                    	lblNewLabel_4.setText("entry deleted");
                    }
                    else {
                    	lblNewLabel_4.setText("error deleting entry");
                    }
				}
				catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Please enter a valid numeric Student ID.");
				}
			}
		});
		add(btnNewButton_2, "cell 2 10,growx,aligny center");
		

	}
    public int addStudent(int id,int deptId,String name,String email) {
        try {
            String sql = "INSERT INTO students VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setString(3, email);
            pstmt.setInt(4, deptId);
            
            pstmt.executeUpdate();
            System.out.println("Student added!");
            return 1;
            
            
        } catch (Exception e) {
            System.out.println("Error adding: " + e.getMessage());
            JOptionPane.showMessageDialog(mainpage.this,
                    "Error adding student: " + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
            return 0;
        }
    }
    public int updateStudent(String newEmail,String newname,int newdept,int id) {
        try {
            String sql = "UPDATE students SET email = ?,name=?,dept_id=? WHERE student_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newEmail);
            pstmt.setString(2, newname);
            pstmt.setInt(3, newdept);
            pstmt.setInt(4, id);
            
            pstmt.executeUpdate();
            System.out.println("Email updated!");
            return 1;
            
        } catch (Exception e) {
            System.out.println("Error updating: " + e.getMessage());
            JOptionPane.showMessageDialog(mainpage.this,
                    "Error updating student: " + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
            return 0;
        }
    }
    public int deleteStudent(int id) {
    	try {
    		String sql = "DELETE FROM students WHERE student_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            return 1;
    	}
    	catch(Exception e) {
    		System.out.println("Error deleting: " + e.getMessage());
            JOptionPane.showMessageDialog(mainpage.this,
                    "Error deleting student: " + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
            return 0;
    	}
    	
    }
}
