package basics.Testing;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
   static List<Integer> myNumber;

    @BeforeAll
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }
}
