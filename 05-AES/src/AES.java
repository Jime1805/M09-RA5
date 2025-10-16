import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES {

    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";

    private static final int MIDA_IV = 16;
    private static byte[] iv = new byte[MIDA_IV];
    private static final String CLAU = "contrasenña";

    public static byte[] xifraAES(String msg, String clau) throws Exception {
        // Bytes de msg
        byte[] msgBytes = msg.getBytes(StandardCharsets.UTF_8);

        // iv
        new SecureRandom().nextBytes(iv);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        // hash
        MessageDigest digest = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] clauHash = digest.digest(clau.getBytes(StandardCharsets.UTF_8));
        SecretKeySpec secretKeySpec = new SecretKeySpec(clauHash, ALGORISME_XIFRAT);

        //cifrar
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivSpec);
        byte[] msgXifrat = cipher.doFinal(msgBytes);

        //iv + msgXifrat
        byte[] resultat = new byte[MIDA_IV + msgXifrat.length];
        
        
    }

    public static String desxifraAES(byte[] bIvIMsgXifrat, String clau) throws Exception{

    }
    
    public static void main(String[] args) {
    String msgs[] = {"Lorem ipsum dicet",
                     "Hola Andrés cómo está tu cuñado",
                     "Àgora illa Òtto"};

    for (int i = 0; i < msgs.length; i++) {
        String msg = msgs[i];

        byte[] bXifrats = null;
        String desxifrat = "";
        try {
            bXifrats = xifraAES(msg, CLAU);
            desxifrat = desxifraAES(bXifrats, CLAU);
        } catch (Exception e) {
            System.err.println("Error de xifrat: " + e.getLocalizedMessage());
        }

        System.out.println("---------------------");
        System.out.println("Msg: " + msg);
        System.out.println("Enc: " + new String(bXifrats));
        System.out.println("DEC: " + desxifrat);
    }
}

}
