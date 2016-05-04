package nc.sumy.edu.webcontainer.action;

import java.util.Map;

/**
* Interface that provides method for working with action programm.
*/

public interface ActionHandler {

/**
* Process string from class using parameters.
*/
    String process(String className, Map<String, String> parameters);
}
