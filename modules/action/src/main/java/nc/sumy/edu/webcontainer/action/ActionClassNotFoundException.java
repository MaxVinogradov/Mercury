package nc.sumy.edu.webcontainer.action;

import static java.lang.String.format;

public class ActionClassNotFoundException extends ActionException {
    public ActionClassNotFoundException(String className) {
        super(format(CLASS_NOT_FOUND, className));
    }
}
