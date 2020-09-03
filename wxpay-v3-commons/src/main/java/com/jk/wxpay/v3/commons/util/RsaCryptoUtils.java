package com.jk.wxpay.v3.commons.util;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.Base64;

public class RsaCryptoUtils {

  /**
   * 通过证书来加密消息。
   * @param message
   * @param certificate
   * @return
   * @throws IllegalBlockSizeException
   * @throws BadPaddingException
   * @throws NoSuchPaddingException
   * @throws NoSuchAlgorithmException
   * @throws InvalidKeyException
   */
  public static String encryptOAEP(String message, X509Certificate certificate)
          throws IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
    Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-1AndMGF1Padding");
    cipher.init(Cipher.ENCRYPT_MODE, certificate.getPublicKey());

    byte[] data = message.getBytes(StandardCharsets.UTF_8);
    byte[] ciphereText = cipher.doFinal(data);
    return Base64.getEncoder().encodeToString(ciphereText);
  }

  /**
   * 通过密钥来解密消息。
   * @param ciphertext
   * @param privateKey
   * @return
   * @throws BadPaddingException
   * @throws IllegalBlockSizeException
   * @throws InvalidKeyException
   * @throws NoSuchPaddingException
   * @throws NoSuchAlgorithmException
   */
  public static String decryptOAEP(String ciphertext, PrivateKey privateKey)
          throws BadPaddingException, IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException {
    Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-1AndMGF1Padding");
    cipher.init(Cipher.DECRYPT_MODE, privateKey);

    byte[] data = Base64.getDecoder().decode(ciphertext);
    return new String(cipher.doFinal(data), StandardCharsets.UTF_8);
  }
}
