package iticbcn.xifratge;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class XifratgeAES implements Xifrador{

    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";

    private static final int MIDA_IV = 16;
    private byte[] iv = new byte[MIDA_IV];
    private static final String CLAU = "contrasenña";

    public byte[] xifraAES(String msg, String clau) throws Exception {
        // Bytes de msg
        byte[] msgBytes = msg.getBytes(StandardCharsets.UTF_8);

        // iv
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        // hash
        MessageDigest digest = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] clauHash = digest.digest(clau.getBytes(StandardCharsets.UTF_8)); // si no funciona poner UTF-8
        SecretKeySpec secretKeySpec = new SecretKeySpec(clauHash, ALGORISME_XIFRAT);

        //cifrar
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivSpec);
        byte[] msgXifrat = cipher.doFinal(msgBytes);

        //iv + msgXifrat
        byte[] bIvMsgXifrat = new byte[MIDA_IV + msgXifrat.length];
        System.arraycopy(iv, 0, bIvMsgXifrat, 0, MIDA_IV);
        System.arraycopy(msgXifrat, 0, bIvMsgXifrat, MIDA_IV, msgXifrat.length);

        return bIvMsgXifrat;
    }

    public String desxifraAES(byte[] bIvIMsgXifrat, String clau) throws Exception{
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        byte[] msgXifrat = Arrays.copyOfRange(bIvIMsgXifrat, MIDA_IV, bIvIMsgXifrat.length);
        MessageDigest digest = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] hschCode = digest.digest(clau.getBytes("UTF-8"));
        SecretKeySpec secretKeySpec = new SecretKeySpec(hschCode, ALGORISME_XIFRAT);
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivSpec);
        byte[] msgDesXifrat = cipher.doFinal(msgXifrat);

        return new String(msgDesXifrat, "UTF-8");
    }
}
