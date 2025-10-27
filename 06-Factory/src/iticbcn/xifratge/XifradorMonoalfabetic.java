package iticbcn.xifratge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class XifradorMonoalfabetic implements Xifrador{
    private static char [] alfabet = "AÄÁÀBCÇDEËÉÈFGHIÏÌÍJKLMNÑOÖÒÓPQRSTUÙÚÜVWXYZ".toCharArray();
    private static char [] permutat;

    public XifradorMonoalfabetic(){
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

    // revisar
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        try {
            byte[] xifrat = xifraMonoAlfa(msg).getBytes(clau);
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
            return desxifraMonoAlfa(xifrat.toString());
        } 
        catch (Exception e) {
            throw new ClauNoSuportada("Error desxifrant amb AES: " + e.getMessage());
        }
    }
}
