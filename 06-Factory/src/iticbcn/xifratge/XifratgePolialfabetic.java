package iticbcn.xifratge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class XifratgePolialfabetic implements Xifrador{

    private static char [] alfabet = "AÄÁÀBCÇDEËÉÈFGHIÏÌÍJKLMNÑOÖÒÓPQRSTUÙÚÜVWXYZ".toCharArray();
    private static char [] permutat;
    private static long clauSecreta = 1234L;
    private static Random random;

    public void initRandom(long clau){
        random = new java.util.Random(clau);
    }

    public void permutaAlfabet(char[] alfabet){

        List<String> list_alfabet = new ArrayList<String>();
        
        for(int i = 0; i < alfabet.length; i++){
            list_alfabet.add("" + alfabet[i]);
        }
        
        Collections.shuffle(list_alfabet, random);
        char [] result = new char[list_alfabet.size()];
        for (int i = 0; i < list_alfabet.size(); i++){
            result[i] = list_alfabet.get(i).charAt(0);
        }
        permutat =  result;
    }

    public String xifraPoliAlfa(String msg){
        String result = "";
        for(int i = 0; i < msg.length(); i++){
            char caracter = msg.charAt(i);
            permutaAlfabet(alfabet);
            if (Character.isLetter(caracter)){
                for (int j = 0; j < alfabet.length; j++){
                    if (Character.toUpperCase(caracter) == alfabet[j]){
                        if (Character.isUpperCase(caracter)){
                            result += permutat[j];
                        }
                        else{
                            result += Character.toLowerCase(permutat[j]);
                        }
                        
                    }
                }
            }
            else{
                result += caracter;
            }
            
        }

        return result;
    }

    public String desxifraPoliAlfa(String msg){
        String result = "";
        for (int i = 0; i < msg.length(); i ++){
            char caracter = msg.charAt(i);
            permutaAlfabet(alfabet);
            if (Character.isLetter(caracter)){
                for (int j = 0; j < alfabet.length; j++){
                    if (Character.toUpperCase(caracter) == permutat[j]){
                        if (Character.isUpperCase(caracter)){
                            result += alfabet[j];
                        }
                        else{
                            result += Character.toLowerCase(alfabet[j]);
                        }
                        
                    }
                }  
            }
            else{
                result += caracter;
            }
            
        }
        return result;
    }
}
