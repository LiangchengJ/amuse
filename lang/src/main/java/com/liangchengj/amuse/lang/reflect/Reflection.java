package com.liangchengj.amuse.lang.reflect;

/**
 * Created at 2021/06/11 00:30.
 *
 * @author Liangcheng Juves
 */
public final class Reflection {
  private Reflection() {}

  /**
   * Determine whether a class can be loaded normally
   *
   * @param name The fully qualified name of the desired class
   * @return {@code ture} means it can be loaded normally
   */
  public static boolean canFindClass(String name) {
    try {
      Class.forName(name);
      return true;
    } catch (ClassNotFoundException e) {
      return false;
    }
  }
}
