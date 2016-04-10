import static org.junit.Assert.assertEquals;

public class MainTest {

    @org.junit.Test
    public void firstTest() {
        Test test = new Test();
        assertEquals(test.function(1), 25);
    }

}
