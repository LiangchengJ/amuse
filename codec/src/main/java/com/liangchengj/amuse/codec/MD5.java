package com.liangchengj.amuse.codec;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.util.Arrays;
import sun.security.jca.JCAUtil;

/**
 * Created at 2020/8/12 15:31
 *
 * @author Liangcheng Juves
 */
public abstract class MD5 {

  private MD5() {}

  private static final int SALT_LENGTH = 12;

  public static <T> T instanceFrom(Class<T> classOfT)
      throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    Method getInstance = classOfT.getDeclaredMethod("getInstance", String.class);
    return (T) getInstance.invoke(classOfT, "MD5");
  }

  public static String encrypt(byte[] src) {
    try {
      byte[] salt = new byte[SALT_LENGTH];
      JCAUtil.getSecureRandom().nextBytes(salt);
      MessageDigest md = instanceFrom(MessageDigest.class);
      md.update(salt);
      md.update(src);

      byte[] digest = md.digest();
      byte[] ret = new byte[digest.length + SALT_LENGTH];
      System.arraycopy(salt, 0, ret, 0, SALT_LENGTH);
      System.arraycopy(digest, 0, ret, SALT_LENGTH, digest.length);

      return HexString.from(ret);
    } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
      throw new AssertionError(e);
    }
  }

  public static boolean validEncrypted(byte[] src, String encrypted) {
    try {
      byte[] encryptedBytes = HexString.parse(encrypted);
      byte[] salt = new byte[SALT_LENGTH];

      System.arraycopy(encryptedBytes, 0, salt, 0, SALT_LENGTH);
      MessageDigest md = instanceFrom(MessageDigest.class);
      md.update(salt);
      md.update(src);

      byte[] digest = md.digest();
      byte[] newDigest = new byte[encryptedBytes.length - SALT_LENGTH];
      System.arraycopy(encryptedBytes, SALT_LENGTH, newDigest, 0, newDigest.length);

      return Arrays.equals(digest, newDigest);
    } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
      throw new AssertionError(e);
    }
  }
}
