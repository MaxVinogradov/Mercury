package nc.sumy.edu.webapp.integration;

import nc.sumy.edu.webapp.integration.common.ResultOfPostSubmit;
import nc.sumy.edu.webapp.integration.common.SocialNetworkInfo;
import nc.sumy.edu.webapp.integration.common.SocialNetworks;
import org.junit.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.Assert.assertEquals;


public class IntegrationImplTest {
    private static final IntegrationImpl integration = new IntegrationImpl();
    @Test
    public void testSubmitPostInvalidSetParameter(){
        Set resultEmpty = integration.submitPost(new HashSet<SocialNetworkInfo>(), "post message");
        Set resultNull = integration.submitPost(null, "post message");
        assertEquals("Publishing to empty set of integrations must return empty set of results",
                Collections.emptySet(), resultEmpty);
        assertEquals("Publishing to null set returns must return empty set of results",
                Collections.emptySet(), resultNull);
    }

    @Test
    public void testSubmitPostInvalidMessageParameter(){
        SocialNetworkInfo info = new SocialNetworkInfo("id", SocialNetworks.VK, "token", "token additional field");
        Set<SocialNetworkInfo> infos = new HashSet<>();
        infos.add(info);
        Set<ResultOfPostSubmit> result = new HashSet<>();
        result.add(new ResultOfPostSubmit(info, false));
        Set<ResultOfPostSubmit> actualEmptyString = integration.submitPost(infos, "");
        Set<ResultOfPostSubmit> actualNull = integration.submitPost(infos, null);
        assertEquals("Publishing empty string must return empty set of results all false",
                result.equals(actualEmptyString), true);
        assertEquals("Publishing null string must return set of results all false",
                result, actualNull);
    }
}
