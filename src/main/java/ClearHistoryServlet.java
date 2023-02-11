import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//Добавил если вдруг ничего нет и вызвать ClearHistory, но не работает
@WebServlet(name = "ClearHistoryServlet", value = "/ClearHistoryServlet")
public class ClearHistoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        for(int i = 0; i < Integer.parseInt(getServletContext().getAttribute("requestsCount").toString()); ++i){
            String reqName = "request" + i;
            getServletContext().removeAttribute(reqName + "x");
            getServletContext().removeAttribute(reqName + "y");
            getServletContext().removeAttribute(reqName + "r");
            getServletContext().removeAttribute(reqName + "res");
            getServletContext().removeAttribute(reqName + "date");
        }
        getServletContext().removeAttribute("requestsCount");
        response.sendRedirect("/ControllerServlet");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
