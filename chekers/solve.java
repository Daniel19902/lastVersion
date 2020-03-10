import java.util.HashMap;
/**
 * Write a description of class Solve here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Solve
{
    private char[][] input;
    private char[][] output;
    private HashMap<String, Integer[]> negras;
    
    public Solve(){
        input = new char[12][12];
        output = new char[12][12];
        negras = new HashMap<String, Integer[]>();
        llenarMatriz();
    }
    
    private void llenarMatriz(){
        int contador = 1;
        for (int i = 2; i < 10; i++){
            for (int j = 2; j < 10; j++){
                if (i%2 == 0 && j%2!= 0){
                    input[i][j] = '.';
                    output[i][j] = '.';
                    String pasar = String.valueOf(contador);
                    negras.put(pasar, new Integer[]{i,j});
                    contador+=1;
                }else if (i%2 != 0 && j%2 == 0){
                    input[i][j] = '.';
                    output[i][j] = '.';
                    String pasar = String.valueOf(contador);
                    negras.put(pasar, new Integer[]{i,j});
                    contador+=1;
                }else{
                    input[i][j] = '-';
                    output[i][j] = '-';
                }
            }
        }
    }
    
    public void putTokens(char player, String moves, boolean entra, String[] jumps, String[] shift){
        char ficha = 'b'; 
        if (player == 'b'){
            ficha = 'w';
        }
        
        if (entra){
            Integer[] posicion = negras.get(shift[0]);
            if (input[posicion[0]][posicion[1]] =='.'){
                input[posicion[0]][posicion[1]] = player;
                posicion = negras.get(shift[1]);
                output[posicion[0]][posicion[1]] = player;  
            }
        }else{
            Integer[] posicion = negras.get(jumps[0]);
            output[posicion[0]][posicion[1]] = '.';
            if(input[posicion[0]][posicion[1]]=='.'){
                input[posicion[0]][posicion[1]] = player;
            }
            for(int i = 1; i< jumps.length; i++){
                
                int k1 = 1, k2 =1;
                Integer[] posicion2 = negras.get(jumps[i]);
                if (posicion[0] > posicion2[0]){
                    k1 = -1;
                }if (posicion[1] > posicion2[1]){
                    k2 = -1;
                }if (output[posicion[0]+k1][posicion[1]+k2] == '.'){
                    input[posicion[0]+k1][posicion[1]+k2] = ficha;
                }else{
                    output[posicion[0]+k1][posicion[1]+k2] = '.';
                }
                posicion = negras.get(jumps[i]);
            }
            output[posicion[0]][posicion[1]] = player;
        }
    }
    
    public String[] returnString(){
        String[] solve=new String[8];
        for(int i = 0 ; i<8; i++){
            solve[i] ="";
            for (int j = 0; j<8; j++){
                solve[i] += input[i+2][j+2]+"  ";
            }
            solve[i]+="          ";
            for (int j = 0; j<8; j++){
                solve[i] += output[i+2][j+2]+"  ";
            }
        }
        return solve;
    }
}
