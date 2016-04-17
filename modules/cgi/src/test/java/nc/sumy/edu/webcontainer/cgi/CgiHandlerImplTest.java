package nc.sumy.edu.webcontainer.cgi;

import nc.sumy.edu.webcontainer.common.InstanceNotCreatedException;
import org.junit.Test;
import org.junit.Before;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class CgiHandlerImplTest {
    private CgiHandlerImpl cgiHandlerImpl = null;
    private final static String CLASS_ID_TEST = "Test";

    @Before
    public void setUp() {
        cgiHandlerImpl = new CgiHandlerImpl();
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
        assertEquals(processResult, cgiHandlerImpl.process(CLASS_ID_TEST, parameters));
    }

    @Test
    public void find() {
        CgiAction testClass = new nc.sumy.edu.webcontainer.cgi.stub.Test();
        assertEquals(testClass.getClass(), cgiHandlerImpl.find("Test"));
    }

    @Test(expected = CgiClassNotFoundException.class)
    public void findException() {
        cgiHandlerImpl.find("Absent");
    }

    @Test(expected = CgiInvalidClassException.class)
    public void runException1() {
        cgiHandlerImpl.process("TestWithoutRun", new HashMap<>());
    }

    @Test(expected = InstanceNotCreatedException.class)
    public void runException2() {
        cgiHandlerImpl.process("TestWithPrivateConstructor", new HashMap<>());
    }

    @Test(expected = InstanceNotCreatedException.class)
    public void runException3() {
        cgiHandlerImpl.process("AbstractTest", new HashMap<>());
    }
}