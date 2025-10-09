import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Monoalfabetic {
    private static char [] alfabet = "AÄÁÀBCÇDEËÉÈFGHIÏÌÍJKLMNÑOÖÒÓPQRSTUÙÚÜVWXYZ".toCharArray();
    private static char [] permutat = permutaAlfabet(alfabet);

    public static void main(String[] args){
        System.out.print("Alfabet:  ");
        System.out.println(alfabet);
        System.out.print("Permutat: ");
        System.out.println(permutat);

        String xifrat = xifraMonoAlfa("Hola");
        System.out.println("Missatge xifrat:    " + xifrat);
        String desxifrat = desxifraMonoAlfa(xifrat);
        System.out.println("Missatge desxifrat: " + desxifrat);
    }

    public static char [] permutaAlfabet(char[] alfabet){

        List<String> list_alfabet = new ArrayList<String>();
        
        for(int i = 0; i < alfabet.length; i++){
            list_alfabet.add("" + alfabet[i]);
        }
        
        Collections.shuffle(list_alfabet);
        char [] result = new char[list_alfabet.size()];
        for (int i = 0; i < list_alfabet.size(); i++){
            result[i] = list_alfabet.get(i).charAt(0);
        }
        return result;
    }

    public static String xifraMonoAlfa(String cadena){
        String result = "";
        for (int i = 0; i < cadena.length(); i++){
            char caracter = Character.toUpperCase(cadena.charAt(i));
            for (int j = 0; j < alfabet.length; j++){
                if (caracter == alfabet[j]){
                    result += permutat[j];
                }
            }
        }
        return result;
    }

    public static String desxifraMonoAlfa(String cadena){
        String result = "";

        for (int i = 0; i < cadena.length(); i++){
            char caracter = cadena.charAt(i);
            for (int j = 0; j < alfabet.length; j++){
                if (caracter == permutat[j]){
                    result += alfabet[j];
                }
            }
        }
        return result;
    }


}
