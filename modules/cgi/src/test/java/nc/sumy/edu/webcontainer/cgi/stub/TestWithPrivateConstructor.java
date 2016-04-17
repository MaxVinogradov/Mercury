package nc.sumy.edu.webcontainer.cgi.stub;

import nc.sumy.edu.webcontainer.cgi.Cgi;
import nc.sumy.edu.webcontainer.cgi.CgiAction;

import java.util.Map;

@Cgi(id = "TestWithPrivateConstructor")
public class TestWithPrivateConstructor implements CgiAction {

    private TestWithPrivateConstructor() {
    }

    public TestWithPrivateConstructor(String message) {
        System.out.println(message);
    }

    @Override
    public String run(Map<String, String> parameters) {
        return "Class for test";
    }
}