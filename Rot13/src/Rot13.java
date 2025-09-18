package Rot13.src;
public class Rot13 {

    public static void main(String[] args){

        System.out.println(xifraRot13("lmnzZ"));
        System.out.println(desxifraRot13("vwxíÍ"));
    }

    public static String xifraRot13(String cadena){

        String mayuscules = "AÀBCDEÉÈFGHIÍÏJKLMNOÓÒPQRSTUÚÜVWXYZ";
        String minuscules = "aàbcdeéèfghiíïjklmnòóopqrstúüvwxxyz"; // 35
        
        String result = "";

        char[] min = minuscules.toCharArray();
        char[] may = mayuscules.toCharArray();

        for (int i = 0; i < cadena.length(); i++){
            char caracter = cadena.charAt(i);

            for (int j = 0; j < may.length; j++){

                char pos_may = may[j];

                if (pos_may == caracter){
                    if (Math.abs(j + 13)>35){
                        result = result + may[Math.abs(35 - j - 13)];
                    }
                    else{
                        result = result + may[Math.abs(j + 13)];
                    }
                }
            }

            for (int j = 0; j < min.length; j++){
                char pos_min = min[j];
                if (pos_min == caracter){
                    if (Math.abs(j + 13)>35){
                        result = result + min[Math.abs(35 - j - 13)];
                    }
                    else{
                        result = result + min[Math.abs(j + 13)];
                    }
                }
            }

        }
        return result;
    }




    public static String desxifraRot13(String cadena){

        String mayuscules = "AÀBCDEÉÈFGHIÍÏJKLMNOÓÒPQRSTUÚÜVWXYZ";
        String minuscules = "aàbcdeéèfghiíïjklmnòóopqrstúüvwxxyz"; // 35
        
        String result = "";

        char[] min = minuscules.toCharArray();
        char[] may = mayuscules.toCharArray();

        for (int i = 0; i < cadena.length(); i++){
            char caracter = cadena.charAt(i);

            for (int j = 0; j < may.length; j++){

                char pos_may = may[j];

                if (pos_may == caracter){
                    if ((j - 13) < 0){
                        result = result + may[Math.abs(35 + j - 13)];
                        System.out.println("Entra 3 --> " + i + " " + result);
                    }
                    else{
                        result = result + may[Math.abs(j - 13)];
                        System.out.println("Entra 4 --> " + i + " " + result);
                    }
                }
            }

            for (int j = 0; j < min.length; j++){
                char pos_min = min[j];
                if (pos_min == caracter){
                    if ((j - 13) < 0){
                        result = result + min[Math.abs(35 + j - 13)];
                        System.out.println("Entra 1 --> " + i + " " + result);
                    }
                    else{
                        result = result + min[Math.abs(j - 13)];
                        System.out.println("Entra 2 --> " + i + " " + result);
                    }
                }
            }
        }
        return result;
    }
}