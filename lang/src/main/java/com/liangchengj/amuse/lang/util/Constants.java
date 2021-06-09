package com.liangchengj.amuse.lang.util;

import java.io.File;

/**
 * Created at 2021/06/09 22:58.
 *
 * @author Liangcheng Juves
 */
public final class Constants {
  private Constants() {}

  public static final String EMPTY_STRING;
  public static final String EXEC_PATH;

  static {
    EMPTY_STRING = "";
    EXEC_PATH = new File(EMPTY_STRING).getAbsolutePath();
  }
}
