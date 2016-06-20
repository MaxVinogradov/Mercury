package nc.sumy.edu.webapp.orm;


import nc.sumy.edu.webapp.database.DataBaseConnection;
import nc.sumy.edu.webapp.database.DataBaseConnectionH2;
import org.dbunit.Assertion;
import org.dbunit.DBTestCase;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Test;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;

import static org.dbunit.PropertiesBasedJdbcDatabaseTester.*;

public class LoadingServiceImplTest1 extends DBTestCase {

//    private final LoadingService loadingService = new LoadingServiceImpl();
    private final DataBaseConnection dbCon = new DataBaseConnectionH2();
    private static final String FILE_DBUNIT_TEST_DATA = "dbunit_test_data.xml";

    @Override
    protected IDataSet getDataSet() throws DatabaseUnitException, IOException {
        try (Connection jdbcConnection = dbCon.getConnection()) {
            IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);
            QueryDataSet partialDataSet = new QueryDataSet(connection);
            partialDataSet.addTable("ACCOUNTS", "SELECT * FROM PUBLIC.ACCOUNTS " +
                    "WHERE account_id in (1,2,3,4)");
            partialDataSet.addTable("PORTALS", "SELECT * FROM PUBLIC.PORTALS " +
                    "WHERE user_id = 1");
            partialDataSet.addTable("POSTS", "SELECT * FROM PUBLIC.POSTS " +
                    "WHERE post_id = 1");
            partialDataSet.addTable("SERVICES", "SELECT * FROM PUBLIC.SERVICES");
            partialDataSet.addTable("USERS", "SELECT * FROM PUBLIC.USERS " +
                    "WHERE user_id = 1");
//        partialDataSet.addTable("BAR");
            String fileDBUnitTestData = "dbunit_test_data.xml";
            FlatXmlDataSet.write(partialDataSet, new FileOutputStream(fileDBUnitTestData));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new FlatXmlDataSetBuilder().build(new FileInputStream(FILE_DBUNIT_TEST_DATA));
    }

    public LoadingServiceImplTest1(String name) {
        super(name);
//        System.out.println("LoadingServiceImplTest");
        System.setProperty(DBUNIT_DRIVER_CLASS, "org.h2.Driver");
        System.setProperty(DBUNIT_CONNECTION_URL, "jdbc:h2:~/test");
        System.setProperty(DBUNIT_USERNAME, "sa");
        System.setProperty(DBUNIT_PASSWORD, "");
        System.setProperty(DBUNIT_SCHEMA, "PUBLIC");
        //IDataSet dataSet = null;

    }

    protected DatabaseOperation getSetUpOperation() {
        try {
            //dataSet =
            getDataSet();
        } catch (DatabaseUnitException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println("getSetUpOperation");
        //return DatabaseOperation.REFRESH;
        return DatabaseOperation.NONE;
    }

    protected DatabaseOperation getTearDownOperation() {
//        System.out.println("getTearDownOperation");
        return DatabaseOperation.NONE;
    }

    @Test
    public void testLoadUser() throws Exception {
//        System.out.println("testLoadUser");
//        User user = loadingService.loadUser("John_Smith");

//         IDataSet expectedData = dataSet;
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File(FILE_DBUNIT_TEST_DATA));
        ITable expectedTable = expectedDataSet.getTable("USERS");

        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("USERS");

        Assertion.assertEquals(expectedTable, actualTable);
//        Assert.assertEquals(expectedData.getTable("USERS").getRowCount(),persons.size());
    }
}