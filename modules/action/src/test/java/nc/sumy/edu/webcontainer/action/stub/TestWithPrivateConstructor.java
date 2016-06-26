package nc.sumy.edu.webcontainer.action.stub;

import nc.sumy.edu.webcontainer.action.Act;
import nc.sumy.edu.webcontainer.action.Actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Act(id = "TestWithPrivateConstructor")
public class TestWithPrivateConstructor implements Actions {

    private TestWithPrivateConstructor() {
    }

    public TestWithPrivateConstructor(String message) {
        System.out.println(message);
    }

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) {
        //why is it empty?
    }

    @Override
    public String run(Map<String, String> parameters) {
        return "Class for test";
    }
}