package com.srishti.RestAPI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

	Connection con = null;
	
	public StudentDAO() {
		super();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");	
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","root123$");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Student getStudent(int id) {
		Student s = new Student();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from student where id = " + id);
			while(rs.next()) {
				s.setId(rs.getInt(1));
				s.setName(rs.getString(2));
				s.setAge(rs.getInt(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return s;
	}
	
	public List<Student> getStudents() {
		List<Student> l = new ArrayList<Student>();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from student");
			while(rs.next()) {
				Student s = new Student();
				s.setId(rs.getInt(1));
				s.setName(rs.getString(2));
				s.setAge(rs.getInt(3));
				l.add(s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return l;
	}
	
	public void insertStudent(Student s) {
		try {
		PreparedStatement ps = con.prepareStatement("insert into Student values (?,?,?)");
		ps.setInt(1, s.getId());
		ps.setString(2, s.getName());
		ps.setInt(3, s.getAge());
		ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateStudent(Student s) {
		try {
			PreparedStatement ps = con.prepareStatement("update Student set name = ? where id = ?");
			ps.setString(1, s.getName());
			ps.setInt(2, s.getId());
			ps.executeUpdate();
		} catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void deleteStudent(int id) {
		try {
			PreparedStatement ps = con.prepareStatement("delete from student where id = ?");
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}


}
