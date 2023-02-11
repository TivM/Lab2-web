import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ControllerServlet", value = "/ControllerServlet")
public class ControllerServlet extends HttpServlet {

    //Подумать насчет добавления ClearHistoryServlet котрый очищает всю исорию запросов

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String x = request.getParameter("X_value");
        String y = request.getParameter("Y_value");
        String r = request.getParameter("R_value");

        if(x == null || y == null || r == null){
            if(request.getParameter("clearHistory") != null){
                getServletContext().getRequestDispatcher("/ClearHistoryServlet").forward(request, response);
            }
            else{
                request.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
            }
        }
//        else if((x != null || y != null || r != null)&&(request.getParameter("clearHistory") != null)){
//                getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
//
//        }

        else {
            ServletContext servletContext = getServletContext();
            RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/AreaCheckServlet");
            requestDispatcher.forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("fromController", true);
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
