package mvc;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CalculoIMCControle", urlPatterns = {"/CalculoIMCControle"})
public class CalculoIMCControle extends HttpServlet {

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        try {
            
            String paramPeso = req.getParameter("peso");
            Double peso = paramPeso == null ? 0.0 : Double.parseDouble(paramPeso);

            String paramAltura = req.getParameter("altura");
            Double altura = paramAltura == null ? 0.0 : Double.parseDouble(paramAltura);

            CalculoIMCModel cImc = new CalculoIMCModel();
            cImc.setPeso(peso);
            cImc.setAltura(altura);
            cImc.calcularImc();
            cImc.calcularResultado();

            req.setAttribute("imc", cImc); //Passando um objeto para o JSP.

            //Chamar o JSP apenas para mostrar o resultado.
            req.getRequestDispatcher("mvc/imc.jsp").forward(req, resp);
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CalculoIMCControle</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CalculoIMCControle at " + req.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {            
            out.close();
        }
    }   // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
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
