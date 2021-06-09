package com.liangchengj.amuse.codec;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.MessageDigest;

/**
 * Created at 2020/8/13 15:41.
 *
 * @author Liangcheng Juves
 */
public abstract class HashFn {
  private HashFn() {}

  public static MessageDigest msgDigestOf(HashFnType type)
      throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    Class msgDigestClass = MessageDigest.class;
    Method getInstance = msgDigestClass.getDeclaredMethod("getInstance", String.class);
    return (MessageDigest) getInstance.invoke(msgDigestClass, type.getType());
  }

  public static String invokeByType(HashFnType type, byte[] src) {
    try {
      MessageDigest msgDigest = msgDigestOf(type);
      msgDigest.update(src);
      return HexString.from(msgDigest.digest());
    } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
      throw new AssertionError(e);
    }
  }

  public static String MD5(byte[] src) {
    return invokeByType(HashFnType.MD5, src);
  }

  public static String SHA1(byte[] src) {
    return invokeByType(HashFnType.SHA1, src);
  }

  public static String SHA256(byte[] src) {
    return invokeByType(HashFnType.SHA256, src);
  }

  public static String SHA512(byte[] src) {
    return invokeByType(HashFnType.SHA512, src);
  }
}
