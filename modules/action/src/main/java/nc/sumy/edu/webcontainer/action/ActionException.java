package nc.sumy.edu.webcontainer.action;

/**
* General exception for action module.
*/

public class ActionException extends RuntimeException {

    public static final String CLASS_NOT_FOUND = "Class with id \"%s\" not found";
    public static final String INVALID_CLASS = "Invalid class: class \"%s\" should implement Actions";

    public ActionException(String message) {
        super(message);
    }

    public ActionException(String message, Throwable cause) {
        super(message, cause);
    }

}
