package com.srishti.RestAPI;

//http://localhost:8080/RestJDBCBasic/webapi/student/students

import java.util.List;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("student")
public class StudentResource {
	
	StudentDAO studentJDBCDAO = new StudentDAO();
	
	@GET
	@Path("student/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public Student getStudent(@PathParam("id") int id) {
		System.out.println("Inside getStudent");
		return studentJDBCDAO.getStudent(id);
	}
	
	@GET
	@Path("students")
	@Produces(MediaType.APPLICATION_XML)
	public List<Student> getStudents() { 
		System.out.println("Inside getStudents");
		return studentJDBCDAO.getStudents();
	}
	
	@PUT
	@Path("insert")
	@Consumes(MediaType.APPLICATION_XML)
	public void insertStudent(Student s) {
		System.out.println("Inside insertStudent");
		 studentJDBCDAO.insertStudent(s);
	}
	
	@PUT
	@Path("update")
	@Consumes(MediaType.APPLICATION_XML)
	public void updateStudent(Student s) {
		System.out.println("Inside updateStudent");
		studentJDBCDAO.updateStudent(s);
	}
	
	@DELETE
	@Path("delete/{id}")
	public Student deleteStudent(@PathParam("id") int id) {
		System.out.println("Inside deleteStudent");
		
		Student s = studentJDBCDAO.getStudent(id);
		if(s != null) {
			studentJDBCDAO.deleteStudent(id);
		}
	 return s;
	}
}
