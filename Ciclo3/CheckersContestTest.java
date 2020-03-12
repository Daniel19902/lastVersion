

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class CheckersContestTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class CheckersContestTest
{
    @ Test
    public void solveInput(){
        CheckersContest test = new CheckersContest();
        String [] tes1 = test.solve('b',new String[]{"2-7","9x2","32-17","2x11x18","5-9"});
        assertEquals(tes1[0],"-.-b-.-.b-b-.-.--w-.-.-.w-w-b-.--.-.-.-..-.-.-.--.-.-.-..-.-.-b-");
    }
    @ Test
    public void solveOutput(){
        CheckersContest test = new CheckersContest();
        String [] tes1 = test.solve('b',new String[]{"2-7","9x2","32-17","2x11x18","5-9"});
        assertEquals(tes1[1],"-.-.-.-..-.-.-.--b-.-.-.w-w-.-.--b-w-.-..-.-.-.--.-.-.-..-.-.-.-");
    }
    @ Test
    public void solveInput2(){
        CheckersContest test = new CheckersContest();
        String [] tes1 = test.solve('w',new String[]{"21-17","13x22x31x24","19x28"});
        assertEquals(tes1[0],"-.-.-.-..-.-.-.--.-.-.-.b-.-w-.--.-.-w-.w-.-.-.--.-w-w-..-.-.-.-");
    }
    @ Test
    public void solveOutput2(){
        CheckersContest test = new CheckersContest();
        String [] tes1 = test.solve('w',new String[]{"21-17","13x22x31x24","19x28"});
        assertEquals(tes1[1],"-.-.-.-..-.-.-.--.-.-.-..-.-w-.--.-.-.-..-.-.-.--.-.-.-w.-.-.-.-");
    }
}
