package com.jk.wxpay.v3.commons.util;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * 通过 api-v3 平台申请的key, 对数据进行解密验证。
 * 1. 微信支付回调数据解密时需要。
 */
public class AesUtils {

  static final int KEY_LENGTH_BYTE = 32;
  static final int TAG_LENGTH_BIT = 128;

  /**
   * 这里使用aes key 将内容解密成字符串。
   * @param aesKey
   * @param associatedData
   * @param nonce
   * @param cipherText
   * @return
   * @throws GeneralSecurityException
   * @throws IllegalStateException
   * @throws IllegalArgumentException
   */
  public static String decryptToString(byte[] aesKey, byte[] associatedData, byte[] nonce, String cipherText)
      throws GeneralSecurityException, IllegalStateException, IllegalArgumentException {

    if (aesKey.length != KEY_LENGTH_BYTE) {
      throw new IllegalArgumentException("Invalid AES ApiV3Key，length must equals to 32 bytes");
    }

    try {
      Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");

      SecretKeySpec key = new SecretKeySpec(aesKey, "AES");
      GCMParameterSpec spec = new GCMParameterSpec(TAG_LENGTH_BIT, nonce);

      cipher.init(Cipher.DECRYPT_MODE, key, spec);
      cipher.updateAAD(associatedData);

      return new String(cipher.doFinal(Base64.getDecoder().decode(cipherText)), Charset.defaultCharset());
    } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
      throw new IllegalStateException(e);
    } catch (InvalidKeyException | InvalidAlgorithmParameterException e) {
      throw new IllegalArgumentException(e);
    }
  }
}
