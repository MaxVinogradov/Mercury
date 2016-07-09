package nc.sumy.edu.webapp.orm;


//import nc.sumy.edu.webapp.database.DataBaseConnection;
//import nc.sumy.edu.webapp.database.DataBaseConnectionH2;
import org.dbunit.Assertion;
import org.dbunit.DBTestCase;
import org.dbunit.DatabaseUnitException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Test;

import java.io.*;

import static org.dbunit.PropertiesBasedJdbcDatabaseTester.*;

public class LoadingServiceImplTest extends DBTestCase {
    public static final String DATASET_XML = "dataset.xml";

    //private final DataBaseConnection dbCon = new DataBaseConnectionH2();

    @Override
    protected IDataSet getDataSet() throws DatabaseUnitException, IOException {
        return new FlatXmlDataSetBuilder().build(new FileInputStream(DATASET_XML));
    }

    public LoadingServiceImplTest(String name) {
        super(name);
        System.setProperty(DBUNIT_DRIVER_CLASS, "org.h2.Driver");
        System.setProperty(DBUNIT_CONNECTION_URL, "jdbc:h2:~/test");
        System.setProperty(DBUNIT_USERNAME, "sa");
        System.setProperty(DBUNIT_PASSWORD, "");
        System.setProperty(DBUNIT_SCHEMA, "PUBLIC");
    }

    @Test
    public void testUsers() throws Exception {
        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("USERS");

        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File(DATASET_XML));
        ITable expectedTable = expectedDataSet.getTable("USERS");

        Assertion.assertEquals(expectedTable, actualTable);
    }

    @Test
    public void testAccounts() throws Exception {
        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("ACCOUNTS");

        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File(DATASET_XML));
        ITable expectedTable = expectedDataSet.getTable("ACCOUNTS");

        Assertion.assertEquals(expectedTable, actualTable);
    }

    @Test
    public void testPortals() throws Exception {
        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("PORTALS");

        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File(DATASET_XML));
        ITable expectedTable = expectedDataSet.getTable("PORTALS");

        Assertion.assertEquals(expectedTable, actualTable);
    }

    @Test
    public void testPosts() throws Exception {
        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("POSTS");

        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File(DATASET_XML));
        ITable expectedTable = expectedDataSet.getTable("POSTS");

        Assertion.assertEquals(expectedTable, actualTable);
    }
}