package iticbcn.xifratge;

import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

public class ClauPublica {
    public KeyPair generaParellClausRSA() throws Exception {
        KeyPairGenerator generador = KeyPairGenerator.getInstance("RSA");
        generador.initialize(2048); // Es la recomanada però es pot canviar.
        return generador.generateKeyPair();
    }

    public byte[] xifraRSA(String msg, PublicKey clauPublica) throws Exception{
        Cipher xifrador = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        xifrador.init(Cipher.ENCRYPT_MODE, clauPublica);
        return xifrador.doFinal(msg.getBytes(StandardCharsets.UTF_8));
    }

    public String desxifraRSA(byte[] msgXifrat, PrivateKey clauPrivada) throws Exception{
        Cipher desxifrador = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        desxifrador.init(Cipher.DECRYPT_MODE, clauPrivada);
        byte[] bytesDesxifrats = desxifrador.doFinal(msgXifrat);
        return new String(bytesDesxifrats, StandardCharsets.UTF_8);
    }
}
