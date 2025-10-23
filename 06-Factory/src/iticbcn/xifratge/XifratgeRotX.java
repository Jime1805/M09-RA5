package iticbcn.xifratge;

public class XifratgeRotX implements Xifrador{

    public String xifraRotX(String cadena, int desplacament){
        return codificador(cadena, desplacament, true, false);
    }

    public String desxifraRotX(String cadena, int desplacament){
        return codificador(cadena, desplacament, false, false);
    }

    public void forcaBrutaRotX(String cadena){
        codificador(cadena, 0, false, true);
    }
    
    public String codificador(String cadena, int desplacament, boolean xifratge, boolean forca){

        String result = "";
        char[] min = "aäáàbcçdeëéèfghiïìíjklmnñoöòópqrstuùúüvwxyz".toCharArray(); // 43
        char[] may = "AÄÁÀBCÇDEËÉÈFGHIÏÌÍJKLMNÑOÖÒÓPQRSTUÙÚÜVWXYZ".toCharArray(); // 43

        if (forca == true){
            System.out.println();
            System.out.println("Sistema per força bruta:");
            System.out.println("------------------------------------");

            for (int k = 0; k < 43; k++){
                result = "";
                for (int i = 0; i < cadena.length(); i++){
                    char caracter = cadena.charAt(i);

                    for (int j = 0; j < may.length; j++){

                        char pos_may = may[j];

                        if (pos_may == caracter){
                            if ((j - k) < 0){
                                result = result + may[Math.abs(43 + j - k)];
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
                                result = result + min[Math.abs(43 + j - k)];
                            }
                            else{
                                result = result + min[Math.abs(j - k)];
                            }
                        }
                    }
                }
                
                System.out.println("Número fet ús: " + k + " " + result);
            }
            return "";
        }
        else{
            if (xifratge == true){
                System.out.println();
                System.out.println("Xifratge per desplaçament:");
                System.out.println("------------------------------------");
                for (int i = 0; i < cadena.length(); i++){
                    char caracter = cadena.charAt(i);

                    for (int j = 0; j < may.length; j++){

                        char pos_may = may[j];

                        if (pos_may == caracter){
                            if (Math.abs(j + desplacament)>=43){
                                result = result + may[Math.abs(43 - j - desplacament)];
                            }
                            else{
                                result = result + may[Math.abs(j + desplacament)];
                            }
                        }
                    }

                    for (int j = 0; j < min.length; j++){
                        char pos_min = min[j];
                        if (pos_min == caracter){
                            if (Math.abs(j + desplacament)>=43){
                                result = result + min[Math.abs(43 - j - desplacament)];
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
            System.out.println();
            System.out.println("Xifratge per desplaçament:");
            System.out.println("------------------------------------");

            for (int i = 0; i < cadena.length(); i++){
                char caracter = cadena.charAt(i);

                for (int j = 0; j < may.length; j++){

                    char pos_may = may[j];

                    if (pos_may == caracter){
                        if ((j - desplacament) < 0){
                            result = result + may[Math.abs(43 + j - desplacament)];
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
                            result = result + min[Math.abs(43 + j - desplacament)];
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
    }
}
