package za.co.bmw.app;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "GetPrimes", urlPatterns = {"/GetPrimes"})
public class GetPrimes extends HttpServlet {
    
    private String calculatePrimesUsingSieveOfEratosthenes(int a, int b) {
        StringBuilder sb = new StringBuilder();
        
        boolean[] isComposite = new boolean[b + 1];
        for (int i = 2; i * i <= b; i++) {
            if (!isComposite [i]) {
                for (int j = i; i * j <= b; j++) {
                    isComposite [i*j] = true;
                }
            }
        }

        sb.append("<ul>");
        for (int i = 2; i <= b; i++) {
            if (!isComposite [i]) sb.append("<li>" + i + "</li>");
        }
        sb.append("</ul>");
        return sb.toString();
        
    }

  private String calculatePrimes(int a, int b) {
    StringBuilder sb = new StringBuilder();

    // Calculate prime numbers here and add to StringBuilder output:
    sb.append("<ul>");
    for(int n = a; n <= b; n++){
        if(n > 1 && n % 1 == 0 && n % 2 != 0 || n == 2){
            sb.append("<li>" + n + "</li>");
        }
    }
    sb.append("</ul>");

    return sb.toString();
  }

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

    int lowerNumber = Integer.parseInt(request.getParameter("lowerNumber"));
    int upperNumber = Integer.parseInt(request.getParameter("upperNumber"));
    boolean sieve = Boolean.parseBoolean(request.getParameter("sieve"));
    
    final String output;
    long start = Calendar.getInstance().getTimeInMillis();
    
    if(sieve){
        output = calculatePrimesUsingSieveOfEratosthenes(lowerNumber, upperNumber);
        
    } else {
        output = calculatePrimes(lowerNumber, upperNumber);
    }
    
    long end = Calendar.getInstance().getTimeInMillis();

    try (PrintWriter out = response.getWriter()) {
      out.println("Calculated in " + (end - start) + "ms. </br>");  
      out.println(output);
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
    processRequest(request, response);
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
