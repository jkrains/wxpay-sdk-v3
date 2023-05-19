package com.jk.wxpay.v3.commons.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.cert.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class PemUtils {

  /**
   * 从一个输入流中加载密钥。
   * @param inputStream
   * @return
   * @throws IOException
   * @throws NoSuchAlgorithmException
   * @throws InvalidKeySpecException
   */
  public static PrivateKey loadPrivateKey(InputStream inputStream) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, IllegalArgumentException {

      if (inputStream != null) {
          ByteArrayOutputStream array = new ByteArrayOutputStream();
          byte[] buffer = new byte[1024];
          int length;
          while ((length = inputStream.read(buffer)) != -1) {
              array.write(buffer, 0, length);
          }

          String privateKey = array.toString("utf-8")
                  .replace("-----BEGIN PRIVATE KEY-----", "")
                  .replace("-----END PRIVATE KEY-----", "")
                  .replaceAll("\\s+", "");

          KeyFactory kf = KeyFactory.getInstance("RSA");
          return kf.generatePrivate( new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey)));
      } else {
          throw new IllegalArgumentException("inputStream is null");
      }
  }

  public static PrivateKey loadPrivateKey(String privateK) throws NoSuchAlgorithmException, InvalidKeySpecException {
      if (!StringUtils.isNullOrEmpty(privateK)) {
          String privateKey = privateK.replace("-----BEGIN PRIVATE KEY-----", "")
                  .replace("-----END PRIVATE KEY-----", "")
                  .replaceAll("\\s+", "");
          KeyFactory kf = KeyFactory.getInstance("RSA");
          return kf.generatePrivate( new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey)));
      } else {
          throw new IllegalArgumentException("cert is null");
      }
  }

  /**
   * 从输入流来加载生成X509证书。
   * @param inputStream
   * @return
   * @throws CertificateException
   */
  public static X509Certificate loadCertificate(InputStream inputStream) throws CertificateException, IllegalArgumentException {
      if (inputStream != null) {
          CertificateFactory cf = CertificateFactory.getInstance("X509");
          X509Certificate cert = (X509Certificate) cf.generateCertificate(inputStream);
          cert.checkValidity();
          return cert;
      } else {
          throw new IllegalArgumentException("inputStream is null");
      }
  }

    /**
     * 从 string对象中加载。
     * @param cert
     * @return
     * @throws CertificateException
     * @throws IllegalArgumentException
     */
  public static X509Certificate loadCertificate(String cert) throws CertificateException, IllegalArgumentException  {
      try {
          InputStream inputStream = new ByteArrayInputStream(cert.getBytes());
          return loadCertificate(inputStream);
      } catch (Exception e) {
          throw e;
      }
  }
}
