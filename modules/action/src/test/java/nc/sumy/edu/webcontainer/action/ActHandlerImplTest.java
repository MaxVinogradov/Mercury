package nc.sumy.edu.webcontainer.action;

import nc.sumy.edu.webcontainer.common.InstanceNotCreatedException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class ActHandlerImplTest {
    private ActionHandlerImpl actionHandlerImpl = null;
    private final static String CLASS_ID_TEST = "Test";

    @BeforeMethod
    public void setUp() {
        actionHandlerImpl = new ActionHandlerImpl();
    }

    @Test
    public void process() {
        String processResult = "Content-type: text/html\n\n" +
                "<html>\n" + "<head>\n" + "<title>\n" + "Hello There " + "Petya Vasechkin" + "!" +
                "\n" + "</title>\n" + "</head>\n" + "<body>\n" + "<h1 align=center>Hello There " + "Petya Vasechkin" +
                "!</h1>" + "</body>\n</html>\n";
        Map<String, String> parameters = new HashMap<>();
        parameters.put("login", "Petya Vasechkin");
        parameters.put("password", "qq");
        assertEquals(processResult, actionHandlerImpl.process(CLASS_ID_TEST, parameters));
    }

    @Test
    public void find() {
        Actions testClass = new nc.sumy.edu.webcontainer.action.stub.Test();
        assertEquals(testClass.getClass(), actionHandlerImpl.find("Test"));
    }

    @Test(expectedExceptions = ActionClassNotFoundException.class)
    public void findException() {
        actionHandlerImpl.find("Absent");
    }

    @Test(expectedExceptions = ActionInvalidClassException.class)
    public void runException1() {
        actionHandlerImpl.process("TestWithoutRun", new HashMap<>());
    }

    @Test(expectedExceptions = InstanceNotCreatedException.class)
    public void runException2() {
        actionHandlerImpl.process("TestWithPrivateConstructor", new HashMap<>());
    }

    @Test(expectedExceptions = InstanceNotCreatedException.class)
    public void runException3() {
        actionHandlerImpl.process("AbstractTest", new HashMap<>());
    }
}