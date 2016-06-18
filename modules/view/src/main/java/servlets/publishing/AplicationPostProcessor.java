package servlets.publishing;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AplicationPostProcessor extends Processor{

    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    public String getPostWall(HttpServletRequest request, HttpServletResponse response)  {
        StringBuilder builder = new StringBuilder();
        HtmlProcessor processor = new HtmlProcessor();
        return null;
    }

}
