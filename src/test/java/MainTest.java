import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    public MainTest(){

    }
    @Test
    public void getString(){
        String msg = "Hello world";
        assertEquals("Hello world",msg);
    }


}