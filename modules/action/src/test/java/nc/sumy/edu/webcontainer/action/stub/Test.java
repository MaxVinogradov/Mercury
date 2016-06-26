package nc.sumy.edu.webcontainer.action.stub;

import nc.sumy.edu.webcontainer.action.Act;
import nc.sumy.edu.webcontainer.action.Actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Act(id = "Test")
public class Test implements Actions {
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) {
        //why is it empty?
    }

    public String run(Map<String, String> parameters) {
        String result;

        result = ("Content-type: text/html\n\n");

        String name = parameters.get("login");

        String top = "<html>\n";
        top += "<head>\n";
        top += "<title>\n";
        top += "Hello There " + name + "!";
        top += "\n";
        top += "</title>\n";
        top += "</head>\n";
        top += "<body>\n";

        result += top;
        result += "<h1 align=center>Hello There " + name +
                "!</h1>";

        result += "</body>\n</html>\n";

        return result;
    }
}