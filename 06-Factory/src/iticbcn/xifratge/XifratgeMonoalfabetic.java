package iticbcn.xifratge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class XifratgeMonoalfabetic implements Xifrador{
    private static char [] alfabet = "AÄÁÀBCÇDEËÉÈFGHIÏÌÍJKLMNÑOÖÒÓPQRSTUÙÚÜVWXYZ".toCharArray();
    private static char [] permutat;

    public XifratgeMonoalfabetic(){
        permutat = permutaAlfabet(alfabet);
    }

    public char [] permutaAlfabet(char[] alfabet){

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

    public String xifraMonoAlfa(String cadena){
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

    public String desxifraMonoAlfa(String cadena){
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
