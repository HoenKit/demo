/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC
 */
public class StudentDAO {
    public List<Student> getStudents(){
        List<Student> sl = new ArrayList<>();
        ConnectDB db = ConnectDB.getInstance();
        String sql = "Select * from studentTBL";
    try {
        Connection con = db.openConnection();
        PreparedStatement statement = con.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            int id = rs.getInt(1);
            String firstName = rs.getString(2);
            String lastName = rs.getString(3);
            String email = rs.getString(4);
            Student student = new Student(id, firstName, lastName, email);
            sl.add(student);
        }
    rs.close();
    statement.close();
    con.close();
    } catch (Exception ex) {
    Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, 
   null, ex);
  }
    return sl;

    }
    public void addStudent(Student student) {
        // create sql for insert
        String sql = "INSERT INTO studentTBL (firstname, lastname, email)\n"
        + "VALUES (?,?, ?);";
        ConnectDB db = ConnectDB.getInstance();

        Connection con;
        try {
        con = db.openConnection();
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, student.getFirstName());
        statement.setString(2, student.getLastName());
        statement.setString(3, student.getEmail());
        statement.execute();

        statement.close();
        con.close();

        } catch (Exception ex) {
        Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, 
       null, ex);
        }
    }
    public void updateStudent(Student student) {
        String sql = " UPDATE [VKUStudentDB].[dbo].[studentTBL]\n"
        + "SET firstName=?, lastName=?, email = ?\n"
        + "WHERE id = ?";
        ConnectDB db = ConnectDB.getInstance();
        Connection con;
        try {
        con = db.openConnection();
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, student.getFirstName());
        statement.setString(2, student.getLastName());
        statement.setString(3, student.getEmail());
        statement.setInt(4, student.getId());
        statement.execute();
        statement.close();
        con.close();
        } catch (Exception ex) {
        Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    public Student getStudent(String idd) {
        List<Student> sl = new ArrayList<>();
        int id = Integer.parseInt(idd);
        ConnectDB db = ConnectDB.getInstance();
        Student student = null;
        String sql = "Select * from studentTBL where id=?";
        try {
        Connection con = db.openConnection();
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {

        String firstName = rs.getString(2);
        String lastName = rs.getString(3);
        String email = rs.getString(4);
        student = new Student(id, firstName, lastName, email);

        }
        rs.close();
        statement.close();
        con.close();
        } catch (Exception ex) {
        Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return student;
        }
    public void deleteStudent(String idd) {
    // create the connection
        try {
        ConnectDB db = ConnectDB.getInstance();
        Connection con = db.openConnection();
        // prepare the statement in order to excute the sql comments
        String sql = "DELETE FROM studentTBL WHERE id=?";
        PreparedStatement statement = con.prepareStatement(sql);
        // convert String id to int id
        int id = Integer.parseInt(idd);
        // set parameter in the sql
        statement.setInt(1, id);
        // execute the sql
        statement.execute();
        con.close();
        statement.close();

        } catch (Exception ex) {
        Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
       }
     public List<Student> searchStudents(String keyword) {
        List<Student> students = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        ConnectDB db = ConnectDB.getInstance();

        try {
            connection = db.openConnection();
            String sql = "SELECT * FROM studentTBL WHERE firstname LIKE ? OR lastname LIKE ? OR email LIKE ?";
            String likeKeyword = "%" + keyword + "%";
            statement = connection.prepareStatement(sql);
            statement.setString(1, likeKeyword);
            statement.setString(2, likeKeyword);
            statement.setString(3, likeKeyword);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String firstName = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                String email = resultSet.getString(4);
                Student student = new Student(id, firstName, lastName, email);
                students.add(student);
            }
        } catch (SQLException ex) {
            // Log any database-related exceptions
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, "Database error: " + ex.getMessage(), ex);
        } catch (Exception ex) {
            // Log any other exceptions
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, "An unexpected error occurred: " + ex.getMessage(), ex);
        } finally {
            // Close resources in a finally block
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                // Log any closing-related exceptions
                Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, "Error closing resources: " + ex.getMessage(), ex);
            }
        }
        return students;
    }
}
