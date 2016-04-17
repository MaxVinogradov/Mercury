package nc.sumy.edu.webcontainer.cgi.stub;

import nc.sumy.edu.webcontainer.cgi.Cgi;
import nc.sumy.edu.webcontainer.cgi.CgiAction;

import java.util.Map;

@Cgi(id = "AbstractTest")
public abstract class AbstractTest implements CgiAction {
    public String run(Map<String, String> parameters) {
        return "This class calling InstantiationException";
    }

    public abstract void someAbstractMethod();
}