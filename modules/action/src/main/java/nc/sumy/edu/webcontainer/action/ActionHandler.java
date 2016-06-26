package nc.sumy.edu.webcontainer.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Interface that provides method for working with action programm.
 */

public interface ActionHandler {

    /**
     * Process string from class using parameters.
     */
    void process(String className, HttpServletRequest request, HttpServletResponse response);
//    String process(String className, Map<String, String> parameters);

}
