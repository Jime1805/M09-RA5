package iticbcn.xifratge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class XifradorPolialfabetic implements Xifrador{

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

    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        try {
            byte[] xifrat = xifraPoliAlfa(msg).getBytes(clau);
            return new TextXifrat(xifrat);
        } 
        catch (Exception e) {
            throw new ClauNoSuportada("Error xifrant amb AES: " + e.getMessage());
        }
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        try {
            if (clau == null || clau.isEmpty()) throw new ClauNoSuportada("La clave no existe");
            return desxifraPoliAlfa(xifrat.toString());
        } 
        catch (Exception e) {
            throw new ClauNoSuportada("Error desxifrant amb AES: " + e.getMessage());
        }
    }
}
