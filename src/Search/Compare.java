package Search;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import Search.Home;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Compare {

	static JFrame frame;
	public static Product a;
	public static Product b;
	static int count = 0;
	public static JLabel ContainImg[] = new JLabel[2];
	private static JTextField textField;
	public static int ID;
	static String Url[] = new String[3];
	static String Urlb[] = new String[3];
	public static JLabel hardware[] = new JLabel[9];
	public static JLabel hardware2[] = new JLabel[9];
	static JLabel label;
	static JLabel label2;
	static JLabel lblImg1;
	static JLabel lblImg2;

	public static void CompareAdd(Product SpCanAdd) {
		if (count == 0) {
			a = SpCanAdd;
			count++;
			System.out.println(a.Name);
		} else if (count == 1) {
			b = SpCanAdd;
			count++;
			System.out.println(b.Name);
		} else
			System.out.println("There are 2 products already");

	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Compare window = new Compare();
//					Manager asdf = new Manager();
//					a = Manager.printProduct(1);
//					b = Manager.printProduct(2);
					Design();

					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void Design() {

		// create images label

		Image img = null;
		try {

			URL url = new URL(Manager.printProduct(a.ID).Image);
			img = ImageIO.read(url);
			System.out.println(Manager.printProduct(a.ID).Name);
		} catch (IOException e) {
			e.printStackTrace();
		}
		JLabel label = new JLabel(new ImageIcon(img));
		label.setSize(400, 400);

		Image imgb = null;
		try {

			URL urlb = new URL(Manager.printProduct(b.ID).Image);
			imgb = ImageIO.read(urlb);
			System.out.println(Manager.printProduct(b.ID).Name);
		} catch (IOException e) {
			e.printStackTrace();
		}
		JLabel label2 = new JLabel(new ImageIcon(imgb));
		label2.setSize(1200, 400);
		frame.getContentPane().setLayout(null);
		frame.add(label);
		frame.add(label2);
		
		
		// create the hardware of the first product label
		String[] hard = new String[9];
		for (int j = 0; j < 9; j++) {
			hard[j] = Manager.printProduct(a.ID).Spec[j];
		}

		hardware[0] = new JLabel("<html> Màn Hình:    " + " <font color='red'>" + hard[0] + "</font></html>");
		hardware[1] = new JLabel("<html> Màu:    " + " <font color='red'>" + hard[1] + "</font></html>");
		hardware[2] = new JLabel("<html> OS:    " + " <font color='red'>" + hard[2] + "</font></html>");
		hardware[3] = new JLabel("<html> CPU:    " + " <font color='red'>" + hard[3] + "</font></html>");
		hardware[4] = new JLabel("<html> RAM:    " + " <font color='red'>" + hard[4] + "</font></html>");
		hardware[5] = new JLabel("<html> ROM:    " + " <font color='red'>" + hard[5] + "</font></html>");
		hardware[6] = new JLabel("<html> CAMERA:    " + " <font color='red'>" + hard[6] + "</font></html>");
		hardware[7] = new JLabel("<html> PIN:    " + " <font color='red'>" + hard[7] + "</font></html>");
		hardware[8] = new JLabel("<html> SIM:    " + " <font color='red'>" + hard[8] + "</font></html>");
		
		
		for(int j=0;j<9;j++) {
			hardware[j].setBounds(100,220+30*j,250,200);
			frame.getContentPane().add(hardware[j]);
		}

		// create the hardware of the second product label
		String[] hard2 = new String[9];
		for (int j = 0; j < 9; j++) {
			hard2[j] = Manager.printProduct(b.ID).Spec[j];
		}

		hardware2[0] = new JLabel("<html> Màn Hình:    " + " <font color='red'>" + hard2[0] + "</font></html>");
		hardware2[1] = new JLabel("<html> Màu:    " + " <font color='red'>" + hard2[1] + "</font></html>");
		hardware2[2] = new JLabel("<html> OS:    " + " <font color='red'>" + hard2[2] + "</font></html>");
		hardware2[3] = new JLabel("<html> CPU:    " + " <font color='red'>" + hard2[3] + "</font></html>");
		hardware2[4] = new JLabel("<html> RAM:    " + " <font color='red'>" + hard2[4] + "</font></html>");
		hardware2[5] = new JLabel("<html> ROM:    " + " <font color='red'>" + hard2[5] + "</font></html>");
		hardware2[6] = new JLabel("<html> CAMERA:    " + " <font color='red'>" + hard2[6] + "</font></html>");
		hardware2[7] = new JLabel("<html> PIN:    " + " <font color='red'>" + hard2[7] + "</font></html>");
		hardware2[8] = new JLabel("<html> SIM:    " + " <font color='red'>" + hard2[8] + "</font></html>");
		
		
		for(int j=0;j<9;j++) {
			hardware2[j].setBounds(500,220+30*j,250,200);
			frame.getContentPane().add(hardware2[j]);
		}

	}

	/**
	 * Create the application.
	 */
	public Compare() {
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 1366, 738);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBackground(Color.WHITE);

		textField = new JTextField();
		textField.setBounds(781, 74, 168, 24);
		frame.add(textField);
		textField.setColumns(10);

		// Make Search Button
		JButton btnNewButton = new JButton("SEARCH");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Manager.Search(textField.getText(), 1, 6);
				Home.Display();
			}

		});

		btnNewButton.setBounds(954, 73, 126, 25);
		frame.add(btnNewButton);

		// Make slogan and banner
		JLabel lblCompareSearchEngine = new JLabel("COMPARE SEARCH ENGINE", SwingConstants.LEFT);
		lblCompareSearchEngine.setForeground(Color.RED);
		lblCompareSearchEngine.setFont(new Font("Footlight MT Light", Font.BOLD, 38));
		lblCompareSearchEngine.setBounds(10, 11, 540, 57);
		frame.getContentPane().add(lblCompareSearchEngine);

		// Add images label
		lblImg1 = new JLabel("");
		lblImg1.setBounds(100, 300, 200, 200);

		lblImg2 = new JLabel("");
		lblImg2.setBounds(100, 500, 200, 200);

		frame.getContentPane().add(lblImg1);
		frame.getContentPane().add(lblImg2);

		// Label for hardware
		

	}

}
