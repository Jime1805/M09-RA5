public class RotX {

    public static void main(String[] args){
        forcaBruta("Hola");
    }

    public static String xifraRotX(String cadena, int desplacament){
        return codificador(cadena, desplacament, true);
    }

    public static String desxifraRotX(String cadena, int desplacament){
        return codificador(cadena, desplacament, false);
    }

    public static void forcaBruta(String cadena){
        String result = "";
        char[] min = "aäáàbcdeëéèfghiïìíjklmnoöòópqrstuùúüvwxyz".toCharArray(); // 41
        char[] may = "AÄÁÀBCDEËÉÈFGHIÏÌÍJKLMNOÖÒÓPQRSTUÙÚÜVWXYZ".toCharArray(); // 41

        for (int k = 0; k < 41; k++){
            for (int i = 0; i < cadena.length(); i++){
                char caracter = cadena.charAt(i);

                for (int j = 0; j < may.length; j++){

                    char pos_may = may[j];

                    if (pos_may == caracter){
                        if ((j - k) < 0){
                            result = result + may[Math.abs(41 + j - k)];
                        }
                        else{
                            result = result + may[Math.abs(j - k)];
                        }
                    }
                }

                for (int j = 0; j < min.length; j++){
                    char pos_min = min[j];
                    if (pos_min == caracter){
                        if ((j - k) < 0){
                            result = result + min[Math.abs(41 + j - k)];
                        }
                        else{
                            result = result + min[Math.abs(j - k)];
                        }
                    }
                }
            }
            System.out.println("Còdig desxifrat");
            System.out.println(result);
        }
    }

    public static String codificador(String cadena, int desplacament, boolean xifratge){

        String result = "";
        char[] min = "aäáàbcdeëéèfghiïìíjklmnoöòópqrstuùúüvwxyz".toCharArray(); // 41
        char[] may = "AÄÁÀBCDEËÉÈFGHIÏÌÍJKLMNOÖÒÓPQRSTUÙÚÜVWXYZ".toCharArray(); // 41

        if (xifratge == true){
            for (int i = 0; i < cadena.length(); i++){
                char caracter = cadena.charAt(i);

                for (int j = 0; j < may.length; j++){

                    char pos_may = may[j];

                    if (pos_may == caracter){
                        if (Math.abs(j + desplacament)>=41){
                            result = result + may[Math.abs(41 - j - desplacament)];
                        }
                        else{
                            result = result + may[Math.abs(j + desplacament)];
                        }
                    }
                }

                for (int j = 0; j < min.length; j++){
                    char pos_min = min[j];
                    if (pos_min == caracter){
                        if (Math.abs(j + desplacament)>=41){
                            result = result + min[Math.abs(41 - j - desplacament)];
                        }
                        else{
                            result = result + min[Math.abs(j + desplacament)];
                        }
                    }
                }

            }
            System.out.println("Còdig xifrat");
            return result;
        }
        for (int i = 0; i < cadena.length(); i++){
            char caracter = cadena.charAt(i);

            for (int j = 0; j < may.length; j++){

                char pos_may = may[j];

                if (pos_may == caracter){
                    if ((j - desplacament) < 0){
                        result = result + may[Math.abs(41 + j - desplacament)];
                    }
                    else{
                        result = result + may[Math.abs(j - desplacament)];
                    }
                }
            }

            for (int j = 0; j < min.length; j++){
                char pos_min = min[j];
                if (pos_min == caracter){
                    if ((j - desplacament) < 0){
                        result = result + min[Math.abs(41 + j - desplacament)];
                    }
                    else{
                        result = result + min[Math.abs(j - desplacament)];
                    }
                }
            }
        }
        System.out.println("Còdig desxifrat");
        return result;
    }

    public static void codificadorforca(String cadena){
        
    }
}
