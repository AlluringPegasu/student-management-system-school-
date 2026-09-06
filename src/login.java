
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import java.sql.*;
import java.util.Scanner;
import java.awt.Font;
import net.miginfocom.swing.MigLayout;
import javax.swing.SwingConstants;

public class login extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	static Connection conn;
    static Scanner scanner = new Scanner(System.in);

	/**
	 * Create the panel.
	 */
	public login(window parentWindow) {
		setLayout(new MigLayout("", "[66px][22px][167px][][][][][][]", "[65px][18px][18px][20px]"));
		
		JLabel lblNewLabel_2 = new JLabel("SQL LOGIN");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Thunder SemBd", Font.BOLD, 40));
		add(lblNewLabel_2, "cell 0 0 9 1,grow");
		
		JLabel lblNewLabel = new JLabel("username");
		add(lblNewLabel, "cell 0 1,growx,aligny center");
		
		JLabel lblNewLabel_1 = new JLabel("password");
		add(lblNewLabel_1, "cell 0 2,growx,aligny center");
		
		textField = new JTextField();
		add(textField, "cell 2 1,alignx left,aligny center");
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		add(textField_1, "cell 2 2,alignx left,aligny center");
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("continue");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					conn = DBconnection.getConnection(textField.getText(),textField_1.getText());

					parentWindow.switchToMainPage(conn);
				}
				catch(Exception exp) {
					JOptionPane.showMessageDialog(login.this,"Login Failed: " + exp.getMessage(), "Database Error",JOptionPane.ERROR_MESSAGE);
					System.out.println(exp.getMessage());
				}
				
			}
		});
		add(btnNewButton, "cell 2 3,alignx left,aligny center");

	}
}
