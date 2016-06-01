package nc.sumy.edu.webcontainer.action;

import static java.lang.String.format;

public class ActionInvalidClassException extends ActionException {
    public ActionInvalidClassException(String className) {
        super(format(INVALID_CLASS, className));
    }
}
