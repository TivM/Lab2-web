import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "AreaCheckServlet", value = "/AreaCheckServlet")
public class AreaCheckServlet extends HttpServlet {
//    //Не уверен нужен ли отдельный класс для точки на графике
//    public static class Point{
//        public double x;
//        public double y;
//        public double  r;
//        public String check;
//
//        public Point(double x, double y, double r, String check) {
//            this.x = x;
//            this.y = y;
//            this.r = r;
//            this.check = check;
//        }
//    }
//
//    private List<Point> list = null;



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext context = getServletContext();
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        if(context.getAttribute("requestsCount") == null)
            context.setAttribute("requestsCount", 0);


;
        //Таблица результатов
        out.println("<html>\n" +
                "<head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "    <title>2 лабораторная работа</title>\n" +
                "    <link rel=\"stylesheet\" href=\"/css/answer.css\">\n" +
                "</head>\n" +
                "<body>\n" +
                "<table class=\"results\">\n" +
                "    <tr class=\"header\">\n" +
                "        <th>Координата X</th>\n" +
                "        <th>Координата Y</th>\n" +
                "        <th>Радиус</th>\n" +
                "        <th>Результат</th>\n" +
                "        <th>Текущее время</th>\n" +
                "    </tr>");

        for(int i = 0; i < Integer.parseInt(context.getAttribute("requestsCount").toString()); ++i){
            printTD(i, out);
        }



        double xCoord = 0, yCoord = 0, rCoord = 0;
        boolean valid = true;

        try {
            xCoord = Double.parseDouble(request.getParameter("X_value"));
            yCoord = Double.parseDouble(request.getParameter("Y_value"));
            rCoord = Double.parseDouble(request.getParameter("R_value"));
        } catch (Exception e){
            valid = false;
        }

        String reqName = "request" + context.getAttribute("requestsCount");

        valid = valid && rCoord >= 2 && rCoord <= 5;

        context.setAttribute(reqName + "x", valid ? xCoord : "Error");
        context.setAttribute(reqName + "y", valid ? yCoord : "Error");
        context.setAttribute(reqName + "r", valid ? rCoord : "Error");
        context.setAttribute(reqName + "res", valid ? isHit(xCoord, yCoord, rCoord) : "Error");
        context.setAttribute(reqName + "date", valid ? new SimpleDateFormat("M/d/y hh:mm:ss a").format(new Date()) : "Errors");


        printTD(Integer.parseInt(context.getAttribute("requestsCount").toString()), out);
        context.setAttribute("requestsCount", Integer.parseInt(context.getAttribute("requestsCount").toString()) + 1);

        out.println("</table>\n" +
                "<table>\n" +
                "    <tr class=\"manageButtons\">\n" +
                "        <td>\n" +
                "            <form method=\"get\" action = \"/ControllerServlet\">\n" +
                "                <button name='back' value='back'>Назад</button>\n" +
                "            </form>\n" +
                "        </td>\n" +
                "        <td>\n" +
                "            <form method=\"get\" action = \"/ControllerServlet\">\n" +
                "                <button name='clearHistory' value='clear''>Очистить сессионную историю</button>\n" +
                "            </form>\n" +
                "        </td>\n" +
                "    </tr>\n" +
                "</table>\n" +
                "<script type = \"text/javascript\" >\n"+
                    "function preventBack(){window.history.forward();}\n" +
                    "setTimeout(\"preventBack()\", 0);\n" +
                    "window.onunload=function(){null};\n" +
                "</script>\n"+
                "</body>\n" +
                "</html>");



//        out.close();
    }

//СКРИПТ ДЛЯ ТОГО ЧТОБЫ НЕЛЬЗЯ БЫЛО ЮЗАТЬ СТРЕЛОЧКИ ДЛЯ ПЕРЕХОДА МЕЖДУ СТРАНИЦАМИ








    private void printTD(int num, PrintWriter out){
        ServletContext context = getServletContext();
        out.printf("<tr>\n<td>%s</td>\n<td>%s</td>\n<td>%s</td>\n<td>%s</td>\n<td>%s</td>\n</tr>\n",
                context.getAttribute("request" + num + "x"),
                context.getAttribute("request" + num + "y"),
                context.getAttribute("request" + num + "r"),
                context.getAttribute("request" + num + "res"),
                context.getAttribute("request" + num + "date"));
    }

    //Проверка на попадание в область на графике
    private String isHit(double x, double y, double r){
        return  x <= 0 && y >= 0 && y <= 2 * x + r ||
                x >= 0 && y >= 0 && x * x + y * y <= r * r ||
                x <= 0 && x >= -r/2 && y >= -r && y <= 0 ? "YES" : "NO";
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
