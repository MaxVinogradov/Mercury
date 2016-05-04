package nc.sumy.edu.webcontainer.action.stub;

import nc.sumy.edu.webcontainer.action.Actions;
import nc.sumy.edu.webcontainer.action.Act;

import java.util.Map;

@Act(id = "AbstractTest")
public abstract class AbstractTest implements Actions {
    public String run(Map<String, String> parameters) {
        return "This class calling InstantiationException";
    }

    public abstract void someAbstractMethod();
}