/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Student;
import model.StudentDAO;

/**
 *
 * @author PC
 */
public class StudentController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet StudentController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet StudentController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       try{
           String theCommand = request.getParameter("command");
           if(theCommand == null){
               theCommand ="LIST";
           } switch(theCommand){
               case"LIST": listStudents(request, response);
               break;
               case"ADD": addStudent(request, response);
               break;
               case"DELETE": deleteStudent(request, response);
               break;
               case"UPDATE": updateStudent(request, response);
               break;
               case"LOAD": loadStudent(request, response);
               break;
               case "SEARCH":
                    searchStudents(request, response);
                    break;
               default:listStudents(request, response);
           }
       } catch (Exception ex) {
           Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
    }
}
    protected void listStudents (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        StudentDAO studentDAO = new StudentDAO();
        List<Student> list = studentDAO.getStudents();
        // add these students to the object request
        request.setAttribute("studentList", list);

        // send to the JSP page (view)
        RequestDispatcher dispatcher = request.getRequestDispatcher("tableStudent.jsp");
        dispatcher.forward(request, response);

    }
    protected void addStudent(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        //create a new student object
        Student student = new Student(firstName, lastName, email);
        StudentDAO studentDAO = new StudentDAO();
        studentDAO.addStudent(student);
        listStudents(request, response);
    }
    protected void deleteStudent(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        String theStudentId= request.getParameter("studentID");
        new StudentDAO().deleteStudent(theStudentId);
        listStudents(request, response);
    }
    private void loadStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //read student id from the form data
        String theStudentId = request.getParameter("studentID");

        //get student from the database
        Student student = new StudentDAO().getStudent(theStudentId);
        //place student in the request attribute
        Student s = (Student) student;
        request.setAttribute("THE_STUDENT", student);
        //send to jsp page: update-student-form.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("updateStudent-form.jsp");
        dispatcher.forward(request, response);
    }
    private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
    // read student info from the form data
        int id = Integer.parseInt(request.getParameter("studentID"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        //create a new student object
        Student student = new Student(id, firstName, lastName, email);

        //perform update on database
        new StudentDAO().updateStudent(student);
        //send them back to the "list student" page
        listStudents(request, response);
    }
     private void searchStudents(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String searchKeyword = request.getParameter("searchKeyword");

        StudentDAO studentDAO = new StudentDAO();
        List<Student> searchResults = studentDAO.searchStudents(searchKeyword);

        request.setAttribute("searchResults", searchResults);

        // Chuyển hướng đến trang search-results.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("search.jsp");
        dispatcher.forward(request, response);
    }
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>


}
