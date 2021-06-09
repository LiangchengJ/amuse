package com.liangchengj.amuse.codec;

import java.util.Objects;

/**
 * Created at 2021/06/09 21:56.
 *
 * @author Liangcheng Juves
 */
public final class HexString {

  private HexString() {}

  // Convert from a byte array to a hexadecimal representation as a string
  public static String from(byte[] bytes, boolean upperCase) {
    Objects.requireNonNull(bytes);
    StringBuilder sb = new StringBuilder();
    for (byte b : bytes) {
      sb.append(Character.forDigit(b >> 4 & 0xF, 16));
      sb.append(Character.forDigit(b & 0xF, 16));
    }

    String result = sb.toString();

    return upperCase ? result.toUpperCase() : result;
  }

  public static String from(byte[] bytes) {
    return from(bytes, false);
  }

  public static String from(String string, boolean upperCase) {
    return from(string.getBytes(), upperCase);
  }

  public static String from(String string) {
    return from(string, false);
  }

  public static String from(Object obj, boolean upperCase) {
    return from(obj.toString(), upperCase);
  }

  public static String from(Object obj) {
    return from(obj, false);
  }

  public static byte[] parse(String hex) {
    Objects.requireNonNull(hex);
    int hexLen = hex.length();
    final byte[] bytes = new byte[hexLen / 2];
    for (int i = 0; i < hexLen; i += 2) {
      bytes[i / 2] = (byte) Integer.parseInt(hex.substring(i, i + 2), 16);
    }
    return bytes;
  }

  public static String parseToString(String hex) {
    return new String(parse(hex));
  }
}
