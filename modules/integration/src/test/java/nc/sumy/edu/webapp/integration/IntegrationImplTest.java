package nc.sumy.edu.webapp.integration;

import nc.sumy.edu.webapp.integration.common.ResultOfPostSubmit;
import nc.sumy.edu.webapp.integration.common.SocialNetworkInfo;
import nc.sumy.edu.webapp.integration.common.SocialNetworks;
import org.junit.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;


public class IntegrationImplTest {
    private static final IntegrationImpl INTEGRATION = new IntegrationImpl();

    @Test
    public void testSubmitPostInvalidSetParameter(){
        Set resultEmpty = INTEGRATION.submitPost(new HashSet<SocialNetworkInfo>(), "post message");
        Set resultNull = INTEGRATION.submitPost(null, "post message");
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
        Set<ResultOfPostSubmit> actualEmptyString = INTEGRATION.submitPost(infos, "");
        Set<ResultOfPostSubmit> actualNull = INTEGRATION.submitPost(infos, null);
        assertEquals("Publishing empty string must return empty set of results all false",
                result.equals(actualEmptyString), true);
        assertEquals("Publishing null string must return set of results all false",
                result, actualNull);
    }

    @Test
    public void testSubmitPostValidParameters(){
        SocialNetworkInfo infoNullToken = new SocialNetworkInfo("id1", SocialNetworks.VK, null, "token additional field");
        SocialNetworkInfo infoNullAddFieldToken = new SocialNetworkInfo("id1", SocialNetworks.VK, "token",
                null);
        SocialNetworkInfo infoEmptyAddFieldToken = new SocialNetworkInfo("id2", SocialNetworks.VK, "token", "");
        SocialNetworkInfo infoEmptyToken = new SocialNetworkInfo("id2", SocialNetworks.VK, "", "token additional field");

        Set<SocialNetworkInfo> infoNull = new HashSet<>();
        infoNull.add(infoNullToken);
        infoNull.add(infoNullAddFieldToken);

        Set<SocialNetworkInfo> infoEmpty = new HashSet<>();
        infoEmpty.add(infoEmptyAddFieldToken);
        infoEmpty.add(infoEmptyToken);

        Set<ResultOfPostSubmit> resultNull = new HashSet<>();
        resultNull.add(new ResultOfPostSubmit(infoNullToken, false));
        resultNull.add(new ResultOfPostSubmit(infoNullAddFieldToken, false));

        Set<ResultOfPostSubmit> resultEmpty = new HashSet<>();
        resultEmpty.add(new ResultOfPostSubmit(infoEmptyAddFieldToken, false));
        resultEmpty.add(new ResultOfPostSubmit(infoEmptyToken, false));

        Set<ResultOfPostSubmit> actualNull = INTEGRATION.submitPost(infoNull, "message");
        Set<ResultOfPostSubmit> actualEmpty = INTEGRATION.submitPost(infoEmpty, "message");
        assertEquals("Publishing message to networks with null token/add.token field must return set of results all false",
                resultNull, actualNull);
        assertEquals("Publishing message to networks with empty token/add.token field must return set of results all false",
                resultEmpty, actualEmpty);
    }
}
