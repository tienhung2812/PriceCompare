package Search;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.event.AncestorListener;

import Search.Manager;

import javax.swing.event.AncestorEvent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Smartphone {

	JFrame frame;
	//static JLabel img = new JLabel("");
	static JLabel label1 = new JLabel("");
	static JLabel price[] = new JLabel[3];
	static JLabel display[] = new JLabel[3];
	static JLabel hardware[] = new JLabel[9];


	public static int ID;
	static String Url[]=new String[3];

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Smartphone window = new Smartphone(1);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		// Display();
	}

	@SuppressWarnings("null")
	
	/**
	 * Create the application.
	 * @param id 
	 * @param search2 
	 * @throws IOException 
	 * @throws MalformedURLException 
	 */
	public Smartphone(int id) throws MalformedURLException, IOException {
		ID=id;
		initialize();	
		
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 * @throws MalformedURLException 
	 */
	@SuppressWarnings("null")
	private void initialize() throws MalformedURLException, IOException {
		frame = new JFrame();
		frame.setBounds(100, 100, 1366, 768);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Image img= null;
		try {
			
			URL url=new URL(Manager.printProduct(ID).Image);
			img=ImageIO.read(url);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		JLabel label=new JLabel(new ImageIcon(img));
		label.setSize(600, 674);
		frame.getContentPane().setLayout(null);
		
//		int search[] = a.searchByName("");
//		for(int i=0;i<search.length;i++) {
//			System.out.println(a.product[search[i]].Price[0]);	
//		}
		
		// name for product
		JLabel lblName= new JLabel(Manager.printProduct(ID).Name,SwingConstants.CENTER);
		lblName.setForeground(Color.BLACK);
		lblName.setFont(new Font("Times New Roman",Font.BOLD,30));
		lblName.setBounds(70,50,500,300);
		frame.getContentPane().add(lblName);
		
		
		JLabel lblCompareSearchEngine = new JLabel("COMPARE SEARCH ENGINE",SwingConstants.CENTER);
		lblCompareSearchEngine.setHorizontalAlignment(SwingConstants.CENTER);
		lblCompareSearchEngine.setVerticalAlignment(SwingConstants.CENTER);
		lblCompareSearchEngine.setForeground(Color.RED);
		lblCompareSearchEngine.setFont(new Font("Footlight MT Light", Font.BOLD, 38));
		lblCompareSearchEngine.setBounds(280, 11, 540, 57);
		frame.getContentPane().add(lblCompareSearchEngine);
		
		label1 = new JLabel("");
		label1.setIcon(new ImageIcon("C:\\Users\\student\\eclipse-workspace\\img\\1.png"));
		label1.setBounds(175, 11, 50, 50);
		//tao label cua price

			price[0]=new JLabel("Lazada Price: ");
			price[1]=new JLabel("TGDD Price: ");
			price[2]=new JLabel("FPT Price: ");
		
		//Display the price
		for(int i=0;i<3;i++) {
			price[i].setBounds(500,150+150*i, 100, 50);
			frame.getContentPane().add(price[i]);
		}
		
		
		for(int i=0;i<3;i++) {
			
			display[i]=new JLabel(Integer.valueOf((Manager.printProduct(ID).Price[i])) +"   VND");
			display[i].setBounds(650,150+150*i,200,50);
			display[i].setFont(new Font("Footlight MT Light",Font.BOLD,20));
			display[i].setForeground(Color.RED);
			frame.getContentPane().add(display[i]);
	}
	
		//Display all the hardware of the product
		String[] hard = new String[9];

			for(int j=0;j<9;j++) {
				hard[j]=Manager.printProduct(ID).Spec[j];
				System.out.println(hard[j]);
			}
//		}

			hardware[0]=new JLabel("<html> Màn Hình:    "+" <font color='red'>"+hard[0]+"</font></html>");
			hardware[1]=new JLabel("<html> Màu:    "+" <font color='red'>"+hard[1]+"</font></html>");
			hardware[2]=new JLabel("<html> OS:    "+" <font color='red'>"+hard[2]+"</font></html>");
			hardware[3]=new JLabel("<html> CPU:    "+" <font color='red'>"+hard[3]+"</font></html>");
			hardware[4]=new JLabel("<html> RAM:    "+" <font color='red'>"+hard[4]+"</font></html>");
			hardware[5]=new JLabel("<html> ROM:    "+" <font color='red'>"+hard[5]+"</font></html>");
			hardware[6]=new JLabel("<html> CAMERA:    "+" <font color='red'>"+hard[6]+"</font></html>");
			hardware[7]=new JLabel("<html> PIN:    "+" <font color='red'>"+hard[7]+"</font></html>");
			hardware[8]=new JLabel("<html> SIM:    "+" <font color='red'>"+hard[8]+"</font></html>");
			
//		}
//		}
		for(int i=0;i<hardware.length;i++) {
			hardware[i].setBounds(900,70+45*i,200,200);
			frame.getContentPane().add(hardware[i]);
		}
		frame.getContentPane().add(label1);
		
		frame.add(label);
		frame.add(lblName);

}

	
}
