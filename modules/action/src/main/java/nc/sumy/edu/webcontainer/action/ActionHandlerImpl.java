package nc.sumy.edu.webcontainer.action;

import nc.sumy.edu.webcontainer.common.ClassUtil;
import org.atteo.classindex.ClassIndex;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class ActionHandlerImpl implements ActionHandler {
    /**
     * Find class marked with Act annotation and implemented Actions interface,
     * create instance and invoke run method to process response body.
     */
    public String process(String className, Map<String, String> parameters) {
        return run(find(className), parameters);
    }

    /**
     * Find class by name marked with Act annotation and implemented Actions interface
     */
    public Class<Actions> find(String id) {
        for (Class<?> klass : ClassIndex.getAnnotated(Act.class))
            if (klass.getAnnotation(Act.class).id().equals(id))
                return (Class<Actions>) klass;
        throw new ActionClassNotFoundException(id);
    }

    protected String run(Class<Actions> klass, Map<String, String> args) {
        if (!Actions.class.isAssignableFrom(klass))
            throw new ActionInvalidClassException(klass.getSimpleName());
        Actions instance = ClassUtil.newInstance(klass);
        return instance.run(args);
    }

    @Override
    public void process(String className, HttpServletRequest request, HttpServletResponse response) {
        run(find(className), request, response);
        //return run(find(className), parameters);
    }

    protected void run(Class<Actions> klass, HttpServletRequest request, HttpServletResponse response) {
        if (!Actions.class.isAssignableFrom(klass))
            throw new ActionInvalidClassException(klass.getSimpleName());
        Actions instance = ClassUtil.newInstance(klass);
        instance.process(request, response);
    }
}