package nc.sumy.edu.webcontainer.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Defines an interface that provide method for running program on server side.
 * The server create an instance of Actions and invoke run method.
 */

public interface Actions {
    /**
     * This method generate response body.
     */
    void process(HttpServletRequest request, HttpServletResponse response);

    default String run(Map<String, String> args){
        return null;
    };
}
