package Search;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import Search.Manager;
import Search.Smartphone;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Color;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.JComboBox;
import javax.swing.JToggleButton;
import java.awt.Button;
import javax.swing.JPanel;

public class Home {

	public static JFrame frame;
	public static JPanel Home;
	public static JPanel Phone;
	public static Manager a;
	private static JTextField textField;
	static String sea;
	static Image img[] = null;
	static JLabel ContainImg[] = new JLabel[6];
	static JLabel name[] = new JLabel[6]; // product array
	static JLabel message;
	private JLabel label;
	static JButton detail[] = new JButton[6];
	static JButton compare[] = new JButton[6];
	static int ID;
	static Smartphone window;
	// static String text;

	/**
	 * ; Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home window = new Home();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * Create the application.
	 */
	public Home() {
		initialize(); 
		Message("Loading");
		a = new Manager();
		Display();
		
	}
	
	public static void Message(String a) {
		message.setText(a);
		message.setVisible(true);
	}
	
	public static void Display() {
		
		String status = Manager.Status;
		//tao unsearched  status
//		JLabel lblContainStatus=new JLabel(status,SwingConstants.CENTER);
//		lblContainStatus.setFont(new Font("Footlight MT Light",Font.BOLD,15));
//		lblContainStatus.setBounds(500,100,50,50);
//		frame.getContentPane().add(lblContainStatus);
		for (int i = 0; i < 6; i++) {
			name[i].setText("");
			ContainImg[i].setIcon(null);
			detail[i].setVisible(false);
			compare[i].setVisible(false);

		}
		int size;
		if (Manager.product_num>6)
			size = 6;
		else 
			size = Manager.product_num;
		
		for (int i = 0; i < size; i++) {
			ID = i+1;
			name[i].setText(Manager.printProduct(i+1).Name);
			detail[i].setVisible(true);
			compare[i].setVisible(true);
			detail[i].addActionListener(new ActionListener() {
				int id = ID;
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						window = new Smartphone(id);

						Home.setVisible(false);
						int i = window.frame.getContentPane().getComponentCount();
						i--;
						for (; i>-1; i--) {
							Phone.add(window.frame.getContentPane().getComponent(i));
						}
						JButton btnExit = new JButton("Exit");			
						btnExit.addActionListener(new ActionListener() {
						    @Override
						    public void actionPerformed(ActionEvent e) {
								Phone.removeAll();
								Phone.setVisible(false);
								Home.setVisible(true);				
								window = null;
						    }
						});
						
						btnExit.setBounds(290, 625, 89, 23);
						Phone.add(btnExit);
						Phone.setVisible(true);
						// SmartPhoneFrame.setVisible(true);

					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			});

			if (i < 3) {
				ContainImg[i].setBounds(90 + 460 * i, 120, 200, 200);
				name[i].setBounds(100+ 460 * i, 330, 200, 50);
				detail[i].setBounds(100 + 460 * i, 365, 70, 23);
				compare[i].setBounds(180 + 460 * i, 365, 80, 23);

			} else {
				ContainImg[i].setBounds(90 + 460 *(i%3), 400, 200, 200);
				name[i].setBounds(100 + 460 *(i%3), 610, 200, 50);
				detail[i].setBounds(150 + 450 *(i%3), 490, 70, 23);
				compare[i].setBounds(180 + 460 *(i%3), 510, 80, 23);
			}
			Image img= null;

				
				try {
					
					URL url=new URL(Manager.printProduct(i+1).Image);
					img=ImageIO.read(url);
				}
				catch(IOException e) {
					e.printStackTrace();
				}
				ContainImg[i].setIcon(new ImageIcon(img));
			Home.add(ContainImg[i]);
			Home.add(name[i]);
			Home.add(detail[i]);
			Home.add(compare[i]);
		}	
		if (size!=0)
			Message(status);
		else
			Message("Can't find any product");
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1366, 768);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setBackground(Color.WHITE);
		
		Home = new JPanel();
		Home.setBounds(0, 0, 1366, 768);
		frame.getContentPane().add(Home);
		Home.setBackground(Color.WHITE);
		Home.setLayout(null);

		Phone = new JPanel();
		Phone.setBounds(0, 0, 1366, 768);
		frame.getContentPane().add(Phone);
		Phone.setBackground(Color.WHITE);
		Phone.setLayout(null);
		Phone.setVisible(false);

		textField = new JTextField();
		textField.setBounds(781, 74, 168, 24);
		Home.add(textField);
		textField.setColumns(10);
		
		
		// Make Search Button
		JButton btnNewButton = new JButton("SEARCH");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Manager.Search(textField.getText(), 1, 6);

				Display();

			}

		});
		btnNewButton.setBounds(954, 73, 126, 25);
		Home.add(btnNewButton);

		// Make slogan and banner
		JLabel lblCompareSearchEngine = new JLabel("COMPARE SEARCH ENGINE");
		lblCompareSearchEngine.setForeground(Color.RED);
		lblCompareSearchEngine.setFont(new Font("Footlight MT Light", Font.BOLD, 38));
		lblCompareSearchEngine.setBounds(353, 5, 537, 45);
		Home.add(lblCompareSearchEngine);

		label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Users\\student\\eclipse-workspace\\img\\1.png"));
		label.setBounds(895, 27, 0, 0);
		Home.add(label);
		// JComboBox<Object> cb=new JComboBox<Object>(ProductName);
		// cb.setBounds(0, 0, 90, 20);
		// frame.getContentPane().add(cb);

		// Make images of smartphones
		for (int i = 0; i < 6; i++) {
			name[i] = new JLabel("");
			ContainImg[i] = new JLabel("");
			detail[i] = new JButton("Detail");
			compare[i] = new JButton("Update");
		}
		
		///Message
		message = new JLabel("CO", SwingConstants.LEFT);
		message.setForeground(Color.DARK_GRAY);
		message.setFont(new Font("Footlight MT Light", Font.BOLD,12 ));
		message.setBounds(781, 90, 537, 45);
		Home.add(message);
		message.setVisible(false);
	}

	
}
