import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "RequestFilter")
public class RequestFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        String servletPath = ((HttpServletRequest)request).getServletPath();
        if(request.getAttribute("fromController") == null &&
                !servletPath.equals("/ControllerServlet") && !servletPath.startsWith("/res")){
            ((HttpServletResponse) response).sendRedirect("/ControllerServlet");
        } else{
            chain.doFilter(request, response);
        }
    }
}
