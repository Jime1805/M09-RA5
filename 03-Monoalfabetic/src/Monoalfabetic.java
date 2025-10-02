import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Monoalfabetic {
    private static char [] alfabet = "AÄÁÀBCÇDEËÉÈFGHIÏÌÍJKLMNÑOÖÒÓPQRSTUÙÚÜVWXYZ".toCharArray();;
    private static char [] permutat = permutaAlfabet(alfabet);

    public static void main(String[] args){
        
        System.out.println(alfabet);
        System.out.println(permutat);

        String xifrat = xifraMonoAlfa("Hola");
        //System.out.println(xifrat);
        String desxifrat = desxifraMonoAlfa(xifrat);
        //System.out.println(desxifrat);
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
            System.out.print(caracter + " -> ");
            for (int j = 0; j < alfabet.length; j++){
                if (caracter == alfabet[j]){
                    System.out.println(permutat[i]);
                    result += permutat[i];
                }
            }
            //System.out.println(result);
        }
        System.out.println(permutat);
        return result;
    }























    public static String desxifraMonoAlfa(String cadena){
        char[] alf = "AÄÁÀBCÇDEËÉÈFGHIÏÌÍJKLMNÑOÖÒÓPQRSTUÙÚÜVWXYZ".toCharArray(); // 43
        char[] alfPerm = permutaAlfabet(alf);

        String result = "";

        for (int i = 0; i < cadena.length(); i++){
            char caracter = cadena.charAt(i);
            for (int j = 0; j < alf.length; j++){
                if (caracter == alfPerm[j]){
                    result += alf[i];
                }
            }
        }
        return result;
    }


}
