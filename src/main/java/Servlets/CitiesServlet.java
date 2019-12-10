package Servlets;

import Models.BikeStation;
import Models.City;
import RDFExtractor.RDFExtractor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class CitiesServlet extends HttpServlet {

    private List<City> cities;

    @Override
    public void init() throws ServletException {
        super.init();
        RDFExtractor rdfExtractor = new RDFExtractor();
        cities = rdfExtractor.getTriples();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*request.setAttribute("stations",cities.get(0).getBikeStations());
        this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response); */

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h3>Hello World!</h3>");
    }
}
