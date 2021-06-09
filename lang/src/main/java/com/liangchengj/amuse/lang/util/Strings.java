package com.liangchengj.amuse.lang.util;

import java.util.Objects;

/**
 * Created at 2021/06/09 23:14.
 *
 * @author Liangcheng Juves
 */
public final class Strings {
  private Strings() {}

  public static String subEndBack(String string, int backStep) {
    Objects.requireNonNull(string);
    return string.isEmpty() ? string : string.substring(0, string.length() - backStep);
  }

  public static String subEndBackOne(String string) {
    return subEndBack(string, 1);
  }

  public static String subWithLastIndex(String string, String ch, int gotoStep) {
    Objects.requireNonNull(string);
    return string.isEmpty() ? string : string.substring(0, string.lastIndexOf(ch) + gotoStep);
  }

  public static String subWithLastIndex(String string, String ch) {
    return subWithLastIndex(string, ch, 1);
  }

  public static boolean safeCompare(String a, String b) {
    if (a.length() != b.length()) {
      return false;
    }

    int ret = 0;
    for (int i = 0; i < a.length(); i++) {
      ret |= a.charAt(i) ^ b.charAt(i);
    }
    return ret == 0;
    // return MessageDigest.isEqual(a.getBytes(), b.getBytes());
  }
}
