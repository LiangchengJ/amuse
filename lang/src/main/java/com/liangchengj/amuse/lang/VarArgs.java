package com.liangchengj.amuse.lang;

/**
 * Created at 2021/6/9 17:35.
 *
 * @author Liangcheng Juves
 */
public final class VarArgs {
  private VarArgs() {}

  public static String stringOf(byte... varArgs) {
    return new String(varArgs);
  }

  public static String stringOf(char... varArgs) {
    return new String(varArgs);
  }

  public static <E> E[] arrayOf(E... varArgs) {
    return varArgs;
  }

  public static byte[] arrayOf(byte... varArgs) {
    return varArgs;
  }

  public static short[] arrayOf(short... varArgs) {
    return varArgs;
  }

  public static int[] arrayOf(int... varArgs) {
    return varArgs;
  }

  public static float[] arrayOf(float... varArgs) {
    return varArgs;
  }

  public static double[] arrayOf(double... varArgs) {
    return varArgs;
  }

  public static long[] arrayOf(long... varArgs) {
    return varArgs;
  }

  public static boolean[] arrayOf(boolean... varArgs) {
    return varArgs;
  }

  public static char[] arrayOf(char... varArgs) {
    return varArgs;
  }
}
