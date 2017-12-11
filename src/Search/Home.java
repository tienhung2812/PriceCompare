package Search;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

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
import java.awt.event.KeyEvent;
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
	public static JPanel ComparePage;
	public static Manager a;
	private static JTextField textField;
	static String sea;
	static Image img[] = null;
	static JLabel ContainImg[] = new JLabel[6];
	static JLabel Loading = null;
	static JLabel name[] = new JLabel[6]; // product array
	static JLabel message;
	private JLabel label;
	static JButton detail[] = new JButton[6];
	static JButton compare[] = new JButton[6];
	static int ID;
	static Smartphone window;
	static Compare window1;
	static JLabel ComparingStatus;
	static JButton Comparison;
	static JButton PageChange;
	static int count = 0;
	public static JLabel lblCompareSearchEngine;
	public static JButton btnSearch;
	public static int page = 1;
	public static JLabel PageNumber;
	public static JButton PageBack;

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

	static void backToHome() {
		page = 1;
		for (int i = 0; i < 6; i++) {
			name[i].setText("");
			ContainImg[i].setIcon(null);
			detail[i].setVisible(false);
			compare[i].setVisible(false);

		}
		Loading.setVisible(true);

		System.out.println("Set to Home");
	}

	public static void Display() {
		// Counting page
		int totalpage = Manager.product_num / 6;
		if (Manager.product_num % 6 != 0) {
			totalpage++;
		}
		if (page == (totalpage))
			PageChange.setEnabled(false);
		else
			PageChange.setEnabled(true);
		System.out.println(page + " of " + totalpage);
		// Display
		String status = Manager.Status;
		ComparingStatus.setText("There is no product chosen");
		for (int i = 0; i < 6; i++) {
			name[i].setText("");
			ContainImg[i].setIcon(null);
			detail[i].setVisible(false);
			compare[i].setVisible(false);

		}
		int size;
		if (page != totalpage)
			size = 6;
		else
			size = Manager.product_num - 6 * (totalpage - 1);

		for (int i = 0; i < size; i++) {
			ID = i + 6 * (page - 1) + 1;

			name[i].setText(Manager.printProduct(ID).Name);

			detail[i].setVisible(true);

			for (ActionListener al : detail[i].getActionListeners()) {
				detail[i].removeActionListener(al);
			}
			for (ActionListener al : compare[i].getActionListeners()) {
				compare[i].removeActionListener(al);
			}
			detail[i].addActionListener(new ActionListener() {
				int id = ID;
				Smartphone window;

				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						window = new Smartphone(id);
						Home.setVisible(false);
						int i = window.frame.getContentPane().getComponentCount();
						i--;
						for (; i > -1; i--) {
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
			detail[i].removeAll();

			compare[i].setVisible(true);
			compare[i].addActionListener(new ActionListener() {
				int id = ID;

				@Override
				public void actionPerformed(ActionEvent e) {

					Compare.CompareAdd(Manager.printProduct(id));
					count++;
					if (count == 1) {
						ComparingStatus.setText("There is one product");
						Comparison.setEnabled(false);
					}
					if (count == 2) {
						ComparingStatus.setText("There is two products");
						Comparison.setEnabled(true);
					}
					if (count > 3) {
						ComparingStatus.setText("There is two products already. Cannot add more");
						Comparison.setVisible(true);
					}
				}
			});

			Comparison.addActionListener(new ActionListener() {
				int id = ID;

				public void actionPerformed(ActionEvent e) {
					window1 = new Compare();
					Compare.Design();
					Home.setVisible(false);
					int i = window1.frame.getContentPane().getComponentCount();

					i--;
					for (; i > -1; i--) {
						ComparePage.add(window1.frame.getContentPane().getComponent(i));
					}
					JButton btnExit = new JButton("Exit");
					btnExit.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							ComparePage.removeAll();
							ComparePage.setVisible(false);
							Home.setVisible(true);
							window1 = null;
							count = 0;
							ComparingStatus.setText("There is no product chosen");
							Comparison.setEnabled(false);
							Comparison.setVisible(true);

						}
					});

					btnExit.setBounds(290, 625, 89, 23);
					ComparePage.add(btnExit);
					ComparePage.setVisible(true);
					// SmartPhoneFrame.setVisible(true);

				}
			});

			if (i < 3) {
				ContainImg[i].setBounds(90 + 460 * i, 120, 200, 200);
				name[i].setBounds(100 + 460 * i, 330, 200, 50);
				detail[i].setBounds(100 + 460 * i, 365, 70, 23);
				compare[i].setBounds(180 + 460 * i, 365, 125, 23);

			} else {
				ContainImg[i].setBounds(90 + 460 * (i % 3), 400, 200, 200);
				name[i].setBounds(100 + 460 * (i % 3), 610, 200, 50);
				detail[i].setBounds(80 + 480 * (i % 3), 650, 70, 23);
				compare[i].setBounds(160 + 480 * (i % 3), 650, 125, 23);
			}
			Image img = null;

			try {

				URL url = new URL(Manager.printProduct(ID).Image);
				img = ImageIO.read(url);
			} catch (IOException e) {
				e.printStackTrace();
			}
			ContainImg[i].setIcon(new ImageIcon(img));
			Home.add(ContainImg[i]);
			Home.add(name[i]);
			Home.add(detail[i]);
			Home.add(compare[i]);
		}
		if (size != 0)
			Message(status);
		else
			Message("Can't find any product");
		if (page == 1) {
			PageBack.setEnabled(false);
		} else
			PageBack.setEnabled(true);

		// Loading
		Loading.setVisible(false);
		//Page number
		PageNumber.setText("" + page);

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

		ComparePage = new JPanel();
		ComparePage.setBounds(0, 0, 1366, 768);
		frame.getContentPane().add(ComparePage);
		ComparePage.setBackground(Color.WHITE);
		ComparePage.setLayout(null);
		ComparePage.setVisible(false);

		textField = new JTextField();
		textField.setBounds(781, 74, 168, 24);
		Home.add(textField);
		textField.setColumns(10);

		// Make Search Button
		JButton btnSearch = new JButton("SEARCH");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField.getText().length() > 0) {
					backToHome();
					Manager.Search(textField.getText(), 1, 6);
					Display();
				}
			}
		});

		PageChange = new JButton(">");
		PageChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				page++;
				PageNumber.setText("" + page);
				Display();
			}

		});
		PageBack = new JButton("<");
		PageBack.setEnabled(true);
		PageBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				page--;

				PageNumber.setText("" + page);

				Display();

			}
		});

		// else if(page==1 || page <1) {
		// PageBack.setEnabled(false);
		// }
		PageNumber = new JLabel("" + page);
		PageNumber.setBounds(1300, 600, 45, 45);
		Home.add(PageNumber);
		//
		PageChange.setBounds(1300, 650, 45, 45);
		Home.add(PageChange);

		PageBack.setBounds(15, 650, 45, 45);
		Home.add(PageBack);

		btnSearch.setBounds(954, 73, 126, 25);
		Home.add(btnSearch);

		// Make slogan and banner
		lblCompareSearchEngine = new JLabel("COMPARE SEARCH ENGINE");
		lblCompareSearchEngine.setForeground(Color.RED);
		lblCompareSearchEngine.setFont(new Font("Footlight MT Light", Font.BOLD, 38));
		lblCompareSearchEngine.setBounds(353, 5, 537, 45);
		Home.add(lblCompareSearchEngine);

		// Make images of smartphones
		for (int i = 0; i < 6; i++) {
			name[i] = new JLabel("");
			ContainImg[i] = new JLabel("");
			detail[i] = new JButton("Detail");
			compare[i] = new JButton("Add to Compare");
		}

		// Create Loading image
		Loading = new JLabel("");
		ImageIcon icon = new ImageIcon(".\\Loading.gif");
		Loading.setIcon(icon);
		Loading.setBounds(Home.getWidth() / 2 - 40, Home.getHeight() / 2, 77, 77);
		Home.add(Loading);
		Loading.setVisible(false);

		// Comparing Status
		ComparingStatus = new JLabel("");
		ComparingStatus.setBounds(15, 50, 400, 23);
		Home.add(ComparingStatus);

		// Comparison Button
		Comparison = new JButton("Compare");
		Comparison.setBounds(15, 74, 90, 23);
		Home.add(Comparison);
		Comparison.setEnabled(false);

		/// Message
		message = new JLabel("CO", SwingConstants.LEFT);
		message.setForeground(Color.DARK_GRAY);
		message.setFont(new Font("Footlight MT Light", Font.BOLD, 12));
		message.setBounds(781, 90, 537, 45);
		Home.add(message);
		message.setVisible(false);

		SwingUtilities.getRootPane(btnSearch).setDefaultButton(btnSearch);
	}

}
