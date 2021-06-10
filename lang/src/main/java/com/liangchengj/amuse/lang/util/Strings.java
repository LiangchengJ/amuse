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

  /**
   * More secure string comparison, mainly to prevent timing attacks
   *
   * <p>The same effect can also be achieved through the Java API:
   *
   * <pre>{@code java.security.MessageDigest.isEqual(a.getBytes(), b.getBytes()); }</pre>
   *
   * @see java.security.MessageDigest#isEqual(byte[], byte[])
   * @param a {@link String} to compare
   * @param b {@link String} to compare
   * @return {@code true} means that the two are exactly the same
   */
  public static boolean isEqual(String a, String b) {
    if (null == a || null == b) return false;
    if (null == a && null == b) return true;
    if (a.length() != b.length()) return false;

    int ret = 0;
    for (int i = 0; i < a.length(); i++) {
      ret |= a.charAt(i) ^ b.charAt(i);
    }
    return ret == 0;
  }

  /**
   * Truncates the given character sequence to the given maximum length. If the length of the
   * sequence is greater than {@code maxLength}, the returned string will be exactly {@code
   * maxLength} chars in length and will end with the given {@code truncationIndicator}. Otherwise,
   * the sequence will be returned as a string with no changes to the content.
   *
   * <p>Examples:
   *
   * <pre>{@code
   * ASCII.truncate("foobar", 7, "..."); // returns "foobar"
   * ASCII.truncate("foobar", 5, "..."); // returns "fo..."
   * }</pre>
   *
   * <p><b>Note:</b> This method <i>may</i> work with certain non-ASCII text but is not safe for use
   * with arbitrary Unicode text. It is mostly intended for use with text that is known to be safe
   * for use with it (such as all-ASCII text) and for simple debugging text. When using this method,
   * consider the following:
   *
   * <ul>
   *   <li>it may split surrogate pairs
   *   <li>it may split characters and combining characters
   *   <li>it does not consider word boundaries
   *   <li>if truncating for display to users, there are other considerations that must be taken
   *       into account
   *   <li>the appropriate truncation indicator may be locale-dependent
   *   <li>it is safe to use non-ASCII characters in the truncation indicator
   * </ul>
   *
   * @throws IllegalArgumentException if {@code maxLength} is less than the length of {@code
   *     truncationIndicator}
   * @since 16.0
   */
  public static String truncate(CharSequence seq, int maxLength, String truncationIndicator) {
    Objects.requireNonNull(seq);

    // length to truncate the sequence to, not including the truncation indicator
    int truncationLength = maxLength - truncationIndicator.length();

    // in this worst case, this allows a maxLength equal to the length of the truncationIndicator,
    // meaning that a string will be truncated to just the truncation indicator itself
    Params.requireNonIllegalArgument(
        truncationLength >= 0,
        "maxLength (%s) must be >= length of the truncation indicator (%s)",
        maxLength,
        truncationIndicator.length());

    if (seq.length() <= maxLength) {
      String string = seq.toString();
      if (string.length() <= maxLength) {
        return string;
      }
      // if the length of the toString() result was > maxLength for some reason, truncate that
      seq = string;
    }

    return new StringBuilder(maxLength)
        .append(seq, 0, truncationLength)
        .append(truncationIndicator)
        .toString();
  }

  public static boolean empty(String string) {
    return string.isEmpty();
  }

  public static boolean emptyAfterTrim(String string) {
    return empty(string) || empty(string.trim());
  }
}
