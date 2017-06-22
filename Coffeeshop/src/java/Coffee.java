/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;

/**
 *
 * @author devya
 */
public class Coffee extends HttpServlet {

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
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet Coffee</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet Coffee at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//            
//            
            
//        }
       
       
       String newItem = request.getParameter("item");
       
        System.out.println(newItem);
        String url = "jdbc:mysql://localhost:3306/std_database";
   String uname = "root";
   String pass ="root";
   
//   String query = "insert into coffee (item)" +  "values('" +newItem + "')";
   String query = "insert into coffee (item) values(?)";
   
   Class.forName("com.mysql.jdbc.Driver");
        
        Connection con = DriverManager.getConnection(url,uname,pass);
        System.out.println("cccc");
        System.out.println(newItem);
        
        PreparedStatement std = con.prepareStatement(query);
        std.setString(1, newItem);
        std.executeUpdate();
        std.close();
//        Statement st = con.createStatement();
//        
//        st.executeQuery(query);
//        st.close();
        
       
        ServletContext sc = this.getServletContext();
        RequestDispatcher rd = sc.getRequestDispatcher("/index.html");
        rd.forward(request, response);
        
        
        con.close();
        
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
        
            try {
                processRequest(request, response);
            } catch (SQLException ex) {
            Logger.getLogger(Coffee.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Coffee.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Coffee.class.getName()).log(Level.SEVERE, null, ex);
        }
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
