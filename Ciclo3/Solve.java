import java.util.HashMap;
import java.util.ArrayList;
/**
 * Write a description of class Solve here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Solve{
    private char[][] input;
    private char[][] output;
    private HashMap<String, Integer[]> negras;
    private ArrayList<Integer[]> kill ;
    private ArrayList<Character> colorFicha ;
    /**
     * constructor
     */
    public Solve(){
        input = new char[12][12];
        output = new char[12][12];
        kill=new ArrayList<Integer[]>();
        negras = new HashMap<String, Integer[]>();
        colorFicha=new ArrayList<Character>();
        llenarMatriz();
    }
    /**
     * prepara el tablero de juego creando el input y el output
     */
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
    /**
     * en reparacion !!!!
     */
    private void convertirRey(char player,String lista[],int moveLista){
        Integer[] j=negras.get(lista[moveLista]);
        if(player=='w' && j[0]==2){
            output[j[0]][j[1]]='W';
            input[j[0]][j[1]]='W';
        }if(player=='b' && j[0]==9){
            output[j[0]][j[1]]='B';
            input[j[0]][j[1]]='B';

        }
    }
    
    /**
     * refactoriza codigo
     * @posicion listas de posiciones
     * @player el la ficha que esta jugando
     * @ficha es la ficha contrincante
     */
    public void refactorizar(Integer posicion[],char player,char ficha){
        if(output[posicion[0]][posicion[1]]=='.'){
                input[posicion[0]][posicion[1]] = player;
                output[posicion[0]][posicion[1]] = player;
      //          convertirRey(player,shift,0);
                
                resultadoKill(posicion,ficha,player);
            }
    }
    
    /**
     * realiza los movimientos para colocar fichas y matar fichas
     * @player el la ficha que esta jugando
     * @moves es el movimiento de la ficha
     * @entra es un boolean que dice que movimeinto es - 0 x
     * @ jumps son una lista cuando mata
     * @shift es una lista cuando mueve normal
     */
    public void putTokens(char player, String moves, boolean entra, String[] jumps, String[] shift){
        char ficha = 'b'; 
        if (player == 'b'){
            ficha = 'w';
        }
        if (entra){
            Integer[] posicion = negras.get(shift[0]);
            refactorizar(posicion,player,ficha);
            check(posicion,player,ficha);
            player=output[posicion[0]][posicion[1]];
            output[posicion[0]][posicion[1]] = '.';
            posicion = negras.get(shift[1]);
            output[posicion[0]][posicion[1]] = player;
        }else{
            Integer[] posicion = negras.get(jumps[0]);
            refactorizar(posicion,player,ficha);
            output[posicion[0]][posicion[1]] = '.';
            for(int i = 1; i< jumps.length; i++){
                int k1 = 1, k2 =1;
                Integer[] posicion2 = negras.get(jumps[i]);
                if (posicion[0] > posicion2[0]){
                    k1 = -1;
                }if (posicion[1] > posicion2[1]){
                    k2 = -1;
                }if (output[posicion[0]+k1][posicion[1]+k2] == '.'){
                    input[posicion[0]+k1][posicion[1]+k2] = ficha;
                    check(new Integer[]{posicion[0]+k1,posicion[1]+k2},ficha,player);
                }else{
                    output[posicion[0]+k1][posicion[1]+k2] = '.';
                }
                posicion = negras.get(jumps[i]);
            }
            output[posicion[0]][posicion[1]] = player;
            kill.add(negras.get(jumps[jumps.length-1]));
            colorFicha.add(player);
        }
    }
    
    /**
     * hace un revicion en la lista kill para ver si no hay una ficha alrededor
     * @ p son las posiciones que va a revisar
     * @ ficha e la ficha contrincante
     * @ player es la ficha que esta jugando
     */
    private void resultadoKill(Integer[] p,char ficha,char player){
        int[] lista={-1,1};
        for(int i=0;i<kill.size();i++){
            if(colorFicha.get(i).equals(ficha)){
                for(int j=0;j<2;j++){
                    for(int k=0;k<2;k++){
                        if(p[0]+j==kill.get(i)[0] && p[1]+k==kill.get(i)[1]){
                            input[p[0]+j*-1][p[1]+k*-1] = player;
                            output[p[0]+j*-1][p[1]+k*-1] = player;
                        }
                    }
                }
            }
        }
    }
    
    /**
     * hace una revicion para agregar fichas que estan protegiendo otras fichas
     * @ pos son las posiciones que va a revisar
     * @ ficha e la ficha contrincante
     * @ turno es la ficha que esta jugando
     */
    private void check(Integer[] pos, char turno,char ficha){
        int[] lista={-1,1};
        for(int i=0;i<2;i++){
            for(int j=0;j<2;j++){
                if(input[pos[0]+lista[i]][pos[1]+lista[j]]==ficha){
                    if(input[pos[0]+lista[i]*2][pos[1]+lista[j]*2] =='.'){
                        input[pos[0]+lista[i]*2][pos[1]+lista[j]*2] = ficha;
                    }
                    output[pos[0]+lista[i]*2][pos[1]+lista[j]*2] = ficha;
                }
            }
        }
    }
    
    /**
     * realiza la lista de los dos tableros
     */
    public String[] returnString(){
        String solve="";
        String solve2 = "";
        String[] solve3 = new String[2]; 
        for(int i = 0 ; i<8; i++){
            for (int j = 0; j<8; j++){
                System.out.println(input[i+2][j+2]);
                solve += input[i+2][j+2];
                solve2 += output[i+2][j+2];
            }
        }
        solve3[0] = solve;
        solve3[1] = solve2;
        return solve3;
        }

}
