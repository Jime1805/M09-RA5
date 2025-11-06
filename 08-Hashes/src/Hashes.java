import java.security.MessageDigest;
import java.security.spec.KeySpec;
import java.util.HexFormat;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Hashes{

    private int npass = 0;
    private static final String CHARSET = "abcdefABCDEF1234567890!";
    private static final int MAX_LEN = 6;

    public String getSHA512AmbSalt(String pw, String salt){
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
        messageDigest.update((pw + salt).getBytes());
        byte[] digest = messageDigest.digest();
        HexFormat hex = HexFormat.of();
        return hex.formatHex(digest);
    }

    public String getPBKDF2AmbSalt(String pw, String salt){
        int iterations = 65536;
        int Keylength = 128;

        char[] passwordChars = pw.toCharArray();
        byte [] saltBytes = salt.getBytes();

        KeySpec spec = new PBEKeySpec(passwordChars, saltBytes, iterations, Keylength);
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] key = secretKeyFactory.generateSecret(spec).getEncoded();
        return HexFormat.of().formatHex(key);
    }
    public String forcaBruta(String alg, String hash, String salt){
        
    }
    public String getInterval(Long t1, Long t2)

    public static void main(String[] args) throws Exception {

        String salt = "qpoweiruañslkdfjz";
        String pw = "aaabF!";
        Hashes h = new Hashes();
        String[] aHashes = { h.getSHA512AmbSalt(pw, salt), h.getPBKDF2AmbSalt(pw, salt) };
        String pwTrobat = null;
        String[] algoritmes = { "SHA-512", "PBKDF2" };

        for (int i = 0; i < aHashes.length; i++) {
            System.out.print("===============================\n");
            System.out.printf("Algorisme: %s\n", algoritmes[i]);
            System.out.printf("Hash: %s\n", aHashes[i]);
            System.out.print("-------------------------------\n");
            System.out.print("-- Inici de força bruta ---\n");

            long t1 = System.currentTimeMillis();
            pwTrobat = h.forcaBruta(algoritmes[i], aHashes[i], salt);
            long t2 = System.currentTimeMillis();

            System.out.printf("Pass  : %s\n", pwTrobat);
            System.out.printf("Provats: %d\n", h.npass);
            System.out.printf("Temps : %s\n", h.getInterval(t1, t2));
            System.out.print("-------------------------------\n\n");
        }
    }
}