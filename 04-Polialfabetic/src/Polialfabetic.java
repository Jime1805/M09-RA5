import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class Polialfabetic {

    private static char [] alfabet = "AÄÁÀBCÇDEËÉÈFGHIÏÌÍJKLMNÑOÖÒÓPQRSTUÙÚÜVWXYZ".toCharArray();
    private static char [] permutat;
    private static long clauSecreta = 1234L;
    private static Random random;

    public static void initRandom(long clau){
        random = new java.util.Random(clau);
    }

    public static void permutaAlfabet(char[] alfabet){

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

    public static void main(String[] args) {
        String msgs[] = {"Test 01 àrbitre, coixí, Perímetre",
                        "Test 02 Taüll, DÍA, año",
                        "Test 03 Peça, Òrrius, Bòvila"};
        String msgsXifrats[] = new String[msgs.length];

        System.out.println("Xifratge:\n-------");
        for (int i = 0; i < msgs.length; i++) {
            initRandom(clauSecreta);
            msgsXifrats[i] = xifraPoliAlfa(msgs[i]);
            System.out.printf("%-34s -> %s%n", msgs[i], msgsXifrats[i]);
        }

        System.out.println("Desxifratge:\n-----------");
        for (int i = 0; i < msgs.length; i++) {
            initRandom(clauSecreta);
            String msg = desxifraPoliAlfa(msgsXifrats[i]);
            System.out.printf("%-34s -> %s%n", msgsXifrats[i], msg);
        }
    }

    public static String xifraPoliAlfa(String msg){
        String result = "";
        for(int i = 0; i < msg.length(); i++){
            char caracter = msg.charAt(i);
            permutaAlfabet(alfabet);
            for (int j = 0; j < alfabet.length; j++){
                if (Character.isLetter(caracter))
                if (Character.toUpperCase(caracter) == alfabet[j]){
                    result += permutat[j];
                }
            }
        }

        return result;
    }

    public static String desxifraPoliAlfa(String msg){
        String result = "";
        for(int i = 0; i < msg.length(); i++){
            char caracter = msg.charAt(i);
            permutaAlfabet(alfabet);
            for (int j = 0; j < alfabet.length; j++){
                if (Character.toUpperCase(caracter) == permutat[j]){
                    result += alfabet[j];
                }
            }
        }

        return result;
    }
}
