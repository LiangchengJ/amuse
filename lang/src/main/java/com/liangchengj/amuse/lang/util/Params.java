package com.liangchengj.amuse.lang.util;

import java.util.Objects;

/**
 * Created at 2021/06/10 22:59.
 *
 * @author Liangcheng Juves
 */
public final class Params {
  // The current class is not allowed to be instantiated
  private Params() {}

  /**
   * Ensure that the parameters passed in the calling method meet the conditions
   *
   * @param condition A boolean condition
   * @param format A <a href="../util/Formatter.html#syntax">format string</a>
   * @param args Arguments referenced by the format specifiers in the format * string. If there are
   *     more arguments than format specifiers, the * extra arguments are ignored. The number of
   *     arguments is * variable and may be zero. The maximum number of arguments is * limited by
   *     the maximum dimension of a Java array as defined by * <cite>The Java&trade; Virtual Machine
   *     Specification</cite>. * The behaviour on a * {@code null} argument depends on the <a *
   *     href="../util/Formatter.html#syntax">conversion</a>
   * @throws NullPointerException If a format string is empty, it will be triggered
   * @throws IllegalArgumentException If {@param condition} is false
   * @throws java.util.IllegalFormatException If a format string contains an illegal syntax, a
   *     format specifier that is incompatible with the given arguments, insufficient arguments
   *     given the format string, or other illegal conditions. For specification of all possible
   *     formatting errors, see the <a href="../util/Formatter.html#detail">Details</a> section of
   *     the formatter class specification
   */
  public static void requireNonIllegalArgument(boolean condition, String format, Object... args) {
    Objects.requireNonNull(format);
    if (!condition) {
      throw new IllegalArgumentException(String.format(format, args));
    }
  }

  /**
   * Ensure that the parameters passed in the calling method meet the conditions
   *
   * @param condition A boolean condition
   * @param format A <a href="../util/Formatter.html#syntax">format string</a>
   * @param args Arguments referenced by the format specifiers in the format * string. If there are
   *     more arguments than format specifiers, the * extra arguments are ignored. The number of
   *     arguments is * variable and may be zero. The maximum number of arguments is * limited by
   *     the maximum dimension of a Java array as defined by * <cite>The Java&trade; Virtual Machine
   *     Specification</cite>. * The behaviour on a * {@code null} argument depends on the <a *
   *     href="../util/Formatter.html#syntax">conversion</a>
   * @throws NullPointerException If a format string is empty, it will be triggered
   * @throws UnsupportedOperationException If {@param condition} is false
   * @throws java.util.IllegalFormatException If a format string contains an illegal syntax, a
   *     format specifier that is incompatible with the given arguments, insufficient arguments
   *     given the format string, or other illegal conditions. For specification of all possible
   *     formatting errors, see the <a href="../util/Formatter.html#detail">Details</a> section of
   *     the formatter class specification
   */
  public static void requireNonUnsupportedOperation(
      boolean condition, String format, Object... args) {
    Objects.requireNonNull(format);
    if (!condition) {
      throw new UnsupportedOperationException(String.format(format, args));
    }
  }

  /**
   * Ensure that the parameters passed in the calling method meet the conditions
   *
   * @param condition A boolean condition
   * @param format A <a href="../util/Formatter.html#syntax">format string</a>
   * @param args Arguments referenced by the format specifiers in the format * string. If there are
   *     more arguments than format specifiers, the * extra arguments are ignored. The number of
   *     arguments is * variable and may be zero. The maximum number of arguments is * limited by
   *     the maximum dimension of a Java array as defined by * <cite>The Java&trade; Virtual Machine
   *     Specification</cite>. * The behaviour on a * {@code null} argument depends on the <a *
   *     href="../util/Formatter.html#syntax">conversion</a>
   * @throws NullPointerException If a format string is empty or {@param condition} is false, it
   *     will be triggered
   * @throws java.util.IllegalFormatException If a format string contains an illegal syntax, a
   *     format specifier that is incompatible with the given arguments, insufficient arguments
   *     given the format string, or other illegal conditions. For specification of all possible
   *     formatting errors, see the <a href="../util/Formatter.html#detail">Details</a> section of
   *     the formatter class specification
   */
  public static void requireNonNullPointer(boolean condition, String format, Object... args) {
    Objects.requireNonNull(format);
    if (!condition) {
      throw new NullPointerException(String.format(format, args));
    }
  }
}
