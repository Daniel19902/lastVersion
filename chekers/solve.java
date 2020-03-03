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
    private String[][] input;
    private String[][] output;
    private HashMap<String ,Integer[]> posiciones;
    public solve (){
        posiciones = new HashMap<String ,Integer[]>();
        nose();
        input = new String[8][8];
        output = new String[8][8];
    }
  
    private void nose(){
        int contador = 1;
        for (int i = 0 ; i < 8 ; i++){
            for (int j = 1-i%2 ; j < 8 ; j+=2){
                String pasar = String.valueOf(contador);
                posiciones.put(pasar , new Integer[] {i,j});
                contador+=1;
            }
        }
    }
    
    public void move(String turno, int veces){
        if (veces > 0){
            String ficha = "";
            if (turno == "b"){
                ficha = "w";
            }else{
                ficha = "b";
            }
            Scanner scan = new Scanner(System.in);
            String turno2 = scan.nextLine();
            ArrayList<Integer> move;
            move = new ArrayList<Integer>();
            String[] jumps = turno2.split("x");
            String[] shift = turno2.split("-");
            //inicio de convertir String a int
            char letra = turno2.charAt(1);
            char letra2 = turno2.charAt(2);
            if (letra == '-' || letra2 == '-'){
                Integer[] posicion = posiciones.get(shift[0]);
                input[posicion[0]][posicion[1]] = turno;
                posicion = posiciones.get(shift[1]);
                output[posicion[0]][posicion[1]] = turno;
            }else{
                Integer[] posicion = posiciones.get(shift[0]);
                input[posicion[0]][posicion[1]] = turno;
                for(int i = 1; i< jumps.length; i++){
                    int k1 = 1, k2 =1;
                    Integer[] posicion2 = posiciones.get(shift[i]);
                    if (posicion[0] < posicion2[0]){
                        k1 = -1;
                    }if (posicion[1] < posicion2[1]){
                        k2 = -1;
                    } if (output[posicion[0]+k1][posicion[1]+k2] == "."){
                        input[posicion[0]+k1][posicion[1]+k2] = ficha;
                    }else{
                        input[posicion[0]+k1][posicion[1]+k2] = ".";
                    }
                    posicion = posiciones.get(shift[i]);
                }
                output[posicion[0]][posicion[1]] = turno;
            }
            move(ficha, veces-1);
        }
    }
  
    public void main(String[] args){
        solve s = new solve();
        Scanner scan = new Scanner(System.in);
        String turno = scan.nextLine();
        String[] veces = turno.split(" ");
        int entero = Integer.parseInt(veces[1]);    
        move(veces[0],entero);
    }
        
 }

