package first;

import com.sun.org.slf4j.internal.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="MyFirstServlet",value="/first/api/*")
public class MyFirstServlet extends HttpServlet {
    Logger logger;
    @Override
    public void destroy() {
        logger.info("Destroy executed");
    }

    @Override
    public void init() throws ServletException {
        logger.info("Init executed");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        //logger
        String path=req.getPathInfo();
        if(path==null||path.equals("/"));
        logger.info("Received"+req.getParameter("lumpSum"));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }
}
