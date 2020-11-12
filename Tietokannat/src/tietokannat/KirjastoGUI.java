package tietokannat;

import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

public class KirjastoGUI {

	private JFrame frame;
	private JTable table;
	private JScrollPane scrollPane_1;
	private JScrollPane scrollPane_2;
	private JTextField Nimi;
	private JTextField Tekija;
	private JTextField Pvm;
	private JTextField Nro;
	private JButton btnNewButton;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JLabel lblNewLabel_4;
	private JButton btnMuokkaaNime;
	private JButton btnMuokkaaKirjailijaa;
	private JButton btnMuokkaaPivmr;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KirjastoGUI window = new KirjastoGUI();
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
	public KirjastoGUI() {
		initialize();
		
		Kirjasto Library = new Kirjasto();
		
		
		try {
			Library.findInfo();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String nimi = Library.getName();
		String tekija = Library.getAuthor();
		int pvm = Library.getDate();
		
		DefaultTableModel model1 = Library.model;
		
		
		table.setModel(model1);
		
		
		
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String newName = Nimi.getText();
				String newAuth = Tekija.getText();
				int Vuosi =Integer.parseInt(Pvm.getText());
				String Number = Nro.getText();
				
				try {
					Library.addNew(newName, newAuth, Vuosi, Number);
					JOptionPane.showMessageDialog(null, "Kirja lisätty! Päivitä tietokanta nähdäksesi muutokset");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		
		});
		
		
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Library.update();
				
			}
		});
		
		
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int column = 3;
				int row = table.getSelectedRow();
				String value = table.getModel().getValueAt(row, column).toString();
				
				try {
					Library.Delete(value);
					JOptionPane.showMessageDialog(null, "Kirja poistettu! Päivitä tietokanta nähdäksesi muutokset");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
			}
		});
		btnMuokkaaNime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				int column = 3;
				int row = table.getSelectedRow();
				String value = table.getModel().getValueAt(row, column).toString();
				String newName = Nimi.getText();
				
				try {
					Library.Update1(newName, value);
					JOptionPane.showMessageDialog(null, "Kirja päivitetty! Päivitä tietokanta nähdäksesi muutokset");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				} catch (ArrayIndexOutOfBoundsException e1) {
					// TODO Auto-generated catch block
					System.out.println("Anna rivi");
					JOptionPane.showMessageDialog(null, "Et valinnut riviä ");
				}
				
			}
			
			
		});
		
		btnMuokkaaKirjailijaa.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e) {
				
				try {
				
				int column = 3;
				int row = table.getSelectedRow();
				String value = table.getModel().getValueAt(row, column).toString();
				String newName = Tekija.getText();
				
				try {
					Library.Update2(newName, value);
					JOptionPane.showMessageDialog(null, "Kirja päivitetty! Päivitä tietokanta nähdäksesi muutokset");
				} catch (  SQLException e1) {
					e1.printStackTrace();
					
				} 
				
				} catch (ArrayIndexOutOfBoundsException e1) {
					// TODO Auto-generated catch block
					System.out.println("Anna rivi");
					JOptionPane.showMessageDialog(null, "Et valinnut riviä ");
				}
				
			}
				
		});
		
		btnMuokkaaPivmr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
				
				int column = 3;
				int row = table.getSelectedRow();
				String value = table.getModel().getValueAt(row, column).toString();
				int newName = Integer.parseInt(Pvm.getText());
				
				try {
					Library.Update3(newName, value);
					JOptionPane.showMessageDialog(null, "Kirja päivitetty! Päivitä tietokanta nähdäksesi muutokset");
				} catch (  SQLException e1) {
					e1.printStackTrace();
					
				} 
				
				} catch (ArrayIndexOutOfBoundsException e1) {
					// TODO Auto-generated catch block
					System.out.println("Anna rivi");
					JOptionPane.showMessageDialog(null, "Et valinnut riviä ");
				}
				
			}
				
		});
		
		
		} 
	
		
		
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 946, 503);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 10, 511, 308);
		frame.getContentPane().add(scrollPane_2);
		
		table = new JTable();
		scrollPane_2.setViewportView(table);
		table.setDefaultEditor(Object.class, null);
		JTableHeader header = table.getTableHeader();
		
		
		Nimi = new JTextField();
		Nimi.setBounds(589, 49, 148, 19);
		frame.getContentPane().add(Nimi);
		Nimi.setColumns(10);
		
		Tekija = new JTextField();
		Tekija.setColumns(10);
		Tekija.setBounds(589, 105, 148, 19);
		frame.getContentPane().add(Tekija);
		
		Pvm = new JTextField();
		Pvm.setColumns(10);
		Pvm.setBounds(589, 157, 148, 19);
		frame.getContentPane().add(Pvm);
		
		Nro = new JTextField();
		Nro.setColumns(10);
		Nro.setBounds(589, 209, 148, 19);
		frame.getContentPane().add(Nro);
		
		btnNewButton = new JButton("Lisää kirja");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 10));
		
		btnNewButton.setBounds(589, 253, 148, 21);
		frame.getContentPane().add(btnNewButton);
		
		lblNewLabel = new JLabel("Anna kirjan nimi");
		lblNewLabel.setBounds(589, 26, 148, 13);
		frame.getContentPane().add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Anna kirjailijan nimi");
		lblNewLabel_1.setBounds(589, 82, 148, 13);
		frame.getContentPane().add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Anna julkaisuvuosi");
		lblNewLabel_2.setBounds(589, 134, 148, 13);
		frame.getContentPane().add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Anna isbn");
		lblNewLabel_3.setBounds(589, 186, 85, 13);
		frame.getContentPane().add(lblNewLabel_3);
		
		btnNewButton_1 = new JButton("Päivitä tietokanta");
		
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 9));
		btnNewButton_1.setBounds(589, 297, 148, 21);
		frame.getContentPane().add(btnNewButton_1);
		
		btnNewButton_2 = new JButton("Poista valittu rivi");
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 9));
		
		btnNewButton_2.setBounds(763, 297, 148, 21);
		frame.getContentPane().add(btnNewButton_2);
		
		lblNewLabel_4 = new JLabel("Muokkaa valittua riviä");
		lblNewLabel_4.setBounds(763, 26, 123, 13);
		frame.getContentPane().add(lblNewLabel_4);
		
		btnMuokkaaNime = new JButton("Muokkaa nimeä");
		
		btnMuokkaaNime.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnMuokkaaNime.setBounds(763, 48, 148, 21);
		frame.getContentPane().add(btnMuokkaaNime);
		
		btnMuokkaaKirjailijaa = new JButton("Muokkaa kirjailijaa");
		
		btnMuokkaaKirjailijaa.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnMuokkaaKirjailijaa.setBounds(763, 104, 148, 21);
		frame.getContentPane().add(btnMuokkaaKirjailijaa);
		
		btnMuokkaaPivmr = new JButton("Muokkaa aikaa");
		
		btnMuokkaaPivmr.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnMuokkaaPivmr.setBounds(763, 156, 148, 21);
		frame.getContentPane().add(btnMuokkaaPivmr);
		
		
	}
}
