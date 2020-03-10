import java.util.HashMap;
/**
 * Write a description of class CheckersContest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CheckersContest{
    
    private String[] jumps;
    private String[] shift;
    
    public String[] solve(char player, String[] moves){
        Solve c = new Solve();
        for (int i = 0; i < moves.length; i++){
            toString(moves[i]);
            boolean entra = toString(moves[i]);
            c.putTokens(player, moves[i], entra, jumps, shift);
            if (player == 'w'){
                player = 'b';
            }else{
                player = 'w';
            }
        }
        return c.returnString();
    }
    
    private boolean toString(String moves){
        boolean entra = false;
        jumps = moves.split("x");
        shift = moves.split("-");
        char letra = moves.charAt(1);
        char letra2 = moves.charAt(2);
        if (letra == '-' || letra2 == '-'){
            entra = true;
        }
        return entra;
    }

    public void simulate(char player, String[] moves, boolean slow){
    }
}
