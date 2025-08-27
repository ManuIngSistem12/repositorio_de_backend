import javax.crypto.Cipher;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.util.Base64;

public class RSAUtil {
    private static final String KEYSTORE_FILE = "mykeystore.p12";
    private static final String KEYSTORE_PASSWORD = "123456"; // usa tu contraseña
    private static final String KEY_ALIAS = "mykey";

    // Obtener llave pública
    public static PublicKey getPublicKey() throws Exception {
        KeyStore ks = KeyStore.getInstance("PKCS12");

        ks.load(new java.io.FileInputStream(KEYSTORE_FILE),
                KEYSTORE_PASSWORD.toCharArray());
        Certificate cert = ks.getCertificate(KEY_ALIAS);
        return cert.getPublicKey();
    }

    // Obtener llave privada
    public static PrivateKey getPrivateKey() throws Exception {
        KeyStore ks = KeyStore.getInstance("PKCS12");
        ks.load(new java.io.FileInputStream(KEYSTORE_FILE),
                KEYSTORE_PASSWORD.toCharArray());
        return (PrivateKey) ks.getKey(KEY_ALIAS, KEYSTORE_PASSWORD.toCharArray());
    }

    // Cifrar con llave pública
    public static String encrypt(String data, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encrypted = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }

    // Descifrar con llave privada
    public static String decrypt(String encryptedData, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decoded = Base64.getDecoder().decode(encryptedData);
        byte[] decrypted = cipher.doFinal(decoded);
        return new String(decrypted);
    }
}
