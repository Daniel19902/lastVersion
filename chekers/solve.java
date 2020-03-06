import java.util.HashMap; 
import java.util.ArrayList;
import java.util.*; 
/**
 * Write a description of class solve here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class solve{
    private char[][] input;
    private char[][] output;
    private HashMap<String ,Integer[]> posiciones;
    
    public solve (){
        posiciones = new HashMap<String ,Integer[]>();
        input = new char[8][8];
        output = new char[8][8];
        
    }
  
    private void nose(){
        int contador = 1;
        for (int i = 0 ; i < 8 ; i++){
            for (int j = 1-i%2 ; j < 8 ; j+=2){
                input[i][j] = '.';
                output[i][j] = '.';
                String pasar = String.valueOf(contador);
                posiciones.put(pasar , new Integer[] {i,j});
                contador+=1;
            }for (int j =i%2 ; j < 8 ; j+=2){
                input[i][j] = '-';
                output[i][j] = '-';
            }
        }
    }
    
    private String[] move(char turno, String veces[]){
        nose();
        for(int ii=0;ii<veces.length;ii++){
           char ficha ='b';
           if (turno == 'b'){
               ficha = 'w';
           }
            ArrayList<Integer> move = new ArrayList<Integer>();
            String[] jumps = veces[ii].split("x");
            String[] shift = veces[ii].split("-");
            char letra = veces[ii].charAt(1);
            char letra2 = veces[ii].charAt(2);
            if (letra == '-' || letra2 == '-'){
                Integer[] posicion = posiciones.get(shift[0]);
                output[posicion[0]][posicion[1]] = '.';
                if(input[posicion[0]][posicion[1]]=='.'){
                    input[posicion[0]][posicion[1]] = turno;
                }
                posicion = posiciones.get(shift[1]);
                output[posicion[0]][posicion[1]] = turno;
            }else{
                Integer[] posicion = posiciones.get(jumps[0]);
                output[posicion[0]][posicion[1]] = '.';
                if(input[posicion[0]][posicion[1]]=='.'){
                    input[posicion[0]][posicion[1]] = turno;
                }
                for(int i = 1; i< jumps.length; i++){
                    int k1 = 1, k2 =1;
                    Integer[] posicion2 = posiciones.get(jumps[i]);
                    if (posicion[0] > posicion2[0]){
                        k1 = -1;
                    }if (posicion[1] > posicion2[1]){
                        k2 = -1;
                    } if (output[posicion[0]+k1][posicion[1]+k2] == '.'){
                        input[posicion[0]+k1][posicion[1]+k2] = ficha;
                    }else{
                        output[posicion[0]+k1][posicion[1]+k2] = '.';
                    }
                    posicion = posiciones.get(jumps[i]);
                }
                output[posicion[0]][posicion[1]] = turno;
            }
        }
        String[] solve=new String[8];
        for(int i = 0 ; i<8; i++){
            solve[i] ="";
            for (int j = 0; j<8; j++){
                solve[i] += input[i][j];
            }
            //solve[i]+="       ";
            //for (int j = 0; j<8; j++){
              //  solve[i] += output[i][j];
            //}
        }
        return solve;
    }
    public String[] solve(char player,String moves[]){
        return move(player,moves);
    }
    public void simulate(char s, String moves[],boolean slow){
        Checkers c=new Checkers(8);
        String[] r=move(s,moves);
        c.read(r[0],r[1],r[0],r[0],r[0],r[0],r[0],r[0]);
        if (!slow){
            
        }
    }
    
    private String toString (){
        
        return "";
    }
 }

