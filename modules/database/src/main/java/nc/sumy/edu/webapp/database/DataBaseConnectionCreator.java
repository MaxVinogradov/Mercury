package nc.sumy.edu.webapp.database;

import nc.sumy.edu.webapp.database.queryloader.QueryLoader;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import ru.qatools.properties.Property;
import ru.qatools.properties.Resource;

@Component
@Resource.Classpath("DB.SQL.properties")
public class DataBaseConnectionCreator {

    @Property("oracle.datasource")
    private String datasource;

    public DataBaseConnection getConection() {
        if (StringUtils.equals(new QueryLoader().getDbProfile(), "H2")) {
            return new DataBaseConnectionH2();
        } else {
            return new DataBaseConnectionOracle(datasource);
        }
    }

}
