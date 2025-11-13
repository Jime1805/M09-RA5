import java.security.MessageDigest;
import java.security.spec.KeySpec;
import java.util.HexFormat;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Hashes {

    private int npass = 0;
    private static final String CHARSET = "abcdefABCDEF1234567890!";
    private static final int MAX_LEN = 6;

    public String getSHA512AmbSalt(String pw, String salt) throws Exception {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
        messageDigest.update((pw + salt).getBytes());
        byte[] digest = messageDigest.digest();
        return HexFormat.of().formatHex(digest);
    }

    public String getPBKDF2AmbSalt(String pw, String salt) throws Exception {
        int iterations = 65536;
        int Keylength = 128;

        char[] passwordChars = pw.toCharArray();
        byte[] saltBytes = salt.getBytes();

        KeySpec spec = new PBEKeySpec(passwordChars, saltBytes, iterations, Keylength);
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] key = secretKeyFactory.generateSecret(spec).getEncoded();
        return HexFormat.of().formatHex(key);
    }

    public String forcaBruta(String alg, String hash, String salt) throws Exception {
        npass = 0;
        char[] letras = CHARSET.toCharArray();
        int base = letras.length;

        for (int longitud = MAX_LEN; longitud >= 1; longitud--) {
            long total = 1L;
            for (int k = 0; k < longitud; k++) {
                total *= base;
            }

            for (long numero = 0; numero < total; numero++) {
                StringBuilder combinacion = new StringBuilder();
                long resto = numero;

                for (int j = 0; j < longitud; j++) {
                    int indiceLetra = (int) (resto % base);
                    combinacion.insert(0, letras[indiceLetra]);
                    resto /= base;
                }

                npass++;
                String combinacionStr = combinacion.toString();
                String hashGenerat;

                if ("SHA-512".equalsIgnoreCase(alg)) {
                    hashGenerat = getSHA512AmbSalt(combinacionStr, salt);
                } else if ("PBKDF2".equalsIgnoreCase(alg)) {
                    hashGenerat = getPBKDF2AmbSalt(combinacionStr, salt);
                } else {
                    throw new IllegalArgumentException("Algoritme desconegut: " + alg);
                }

                if (hashGenerat.equalsIgnoreCase(hash)) {
                    return combinacionStr;
                }
            }
        }
        return null;
    }


    public String getInterval(Long t1, Long t2) {
        long totalMillis = t2 - t1;

        long days = totalMillis / (24 * 3600 * 1000);
        totalMillis %= (24 * 3600 * 1000);

        long hours = totalMillis / 3600000;
        totalMillis %= 3600000;

        long minutes = totalMillis / 60000;
        totalMillis %= 60000;

        long seconds = totalMillis / 1000;
        long millis = totalMillis % 1000;

        return String.format("%d dies / %d hores / %d minuts / %d segons / %d millis", days, hours, minutes, seconds, millis);
    }

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
