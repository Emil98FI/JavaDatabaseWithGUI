package tietokannat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;

public class Kirjasto {

	private String name;
	private String author;
	private int date;
	private String isbn;
	DefaultTableModel model = new DefaultTableModel(new String[]{"Nimi", "Tekijä", "Päivämäärä","isbn"}, 0);
	
	
	

	   
	
	
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public DefaultTableModel getModel() {
		return model;
	}
	public void setModel(DefaultTableModel model) {
		this.model = model;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getDate() {
		return date;
	}
	public void setDate(int date) {
		this.date = date;
	}
	
	public void findInfo() throws SQLException {
		
		String URL = "jdbc:mysql://localhost:3306/mydatabase";
		
		String USERID = "Dbuser";
		String PASSWORD = "user";
		
		Connection con = DriverManager.getConnection(URL, USERID, PASSWORD);
		
		System.out.println("Yhteys tietokantaan on luotu.");
		
		Statement stmt = con.createStatement();
		
		ResultSet rs = stmt.executeQuery("SELECT * FROM kirjat");
		
		
		while (rs.next()){
			
			setName(rs.getString(1));
			setAuthor(rs.getString(2));
			setDate(rs.getInt(3));
			setIsbn(rs.getString(4));
			model.addRow(new Object[]{this.name, this.author, this.date,this.isbn});
			
			}
		
		
	}
	
	public void addNew (String nimi, String tekijä, int vuosi, String isbn) throws SQLException {
		String URL = "jdbc:mysql://localhost:3306/mydatabase";
		
		String USERID = "Dbuser";
		String PASSWORD = "user";
		
		Connection con = DriverManager.getConnection(URL, USERID, PASSWORD);
		
		String sql = "INSERT INTO kirjat values (?,?,?,?)";
		
		PreparedStatement preparedStmt = con.prepareStatement(sql);
		
		preparedStmt.setString(1, nimi);
		preparedStmt.setString(2, tekijä);
		preparedStmt.setInt(3, vuosi);
		preparedStmt.setString(4, isbn);
		
		preparedStmt.execute();
		
		System.out.println("Tiedot Lisätty");
		
		
	}
	
	public void update() {
		model.setRowCount(0);
		
		try {
			findInfo();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void Delete(String id) throws SQLException {
		
		String URL = "jdbc:mysql://localhost:3306/mydatabase";
		
		String USERID = "Dbuser";
		String PASSWORD = "user";
		
		Connection con = DriverManager.getConnection(URL, USERID, PASSWORD);
		
		String sql = "DELETE FROM KIRJAT WHERE isbn=(?)";
		
		PreparedStatement preparedStmt = con.prepareStatement(sql);
		
		preparedStmt.setString(1, id);
		
		preparedStmt.execute();
		
		System.out.println("Poistettu");
	}
	
	
	public void Update1(String Auth, String id) throws SQLException {
		
		String URL = "jdbc:mysql://localhost:3306/mydatabase";
		
		String USERID = "Dbuser";
		String PASSWORD = "user";
		
		Connection con = DriverManager.getConnection(URL, USERID, PASSWORD);
		
		String sql = "UPDATE KIRJAT SET teoksenNimi=(?) WHERE isbn=(?)";
		
		PreparedStatement preparedStmt = con.prepareStatement(sql);
		
		preparedStmt.setString(1, Auth);
		preparedStmt.setString(2, id);
		
		preparedStmt.execute();
		
		System.out.println("Muokattu");
		
		
		
	}
	
public void Update2(String Auth, String id) throws SQLException {
		
		String URL = "jdbc:mysql://localhost:3306/mydatabase";
		
		String USERID = "Dbuser";
		String PASSWORD = "user";
		
		Connection con = DriverManager.getConnection(URL, USERID, PASSWORD);
		
		String sql = "UPDATE KIRJAT SET tekijanNimi=(?) WHERE isbn=(?)";
		
		PreparedStatement preparedStmt = con.prepareStatement(sql);
		
		preparedStmt.setString(1, Auth);
		preparedStmt.setString(2, id);
		
		preparedStmt.execute();
		
		System.out.println("Muokattu");
		
		
		
	}

public void Update3(int date, String id) throws SQLException {
	
	String URL = "jdbc:mysql://localhost:3306/mydatabase";
	
	String USERID = "Dbuser";
	String PASSWORD = "user";
	
	Connection con = DriverManager.getConnection(URL, USERID, PASSWORD);
	
	String sql = "UPDATE KIRJAT SET julkaisuvuosi=(?) WHERE isbn=(?)";
	
	PreparedStatement preparedStmt = con.prepareStatement(sql);
	
	preparedStmt.setInt(1, date);
	preparedStmt.setString(2, id);
	
	preparedStmt.execute();
	
	System.out.println("Muokattu");
	
	
	
}
	
}
