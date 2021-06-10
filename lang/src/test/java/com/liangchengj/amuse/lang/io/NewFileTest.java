package com.liangchengj.amuse.lang.io;

import com.liangchengj.amuse.lang.util.Constants;
import org.junit.jupiter.api.Test;

/**
 * Created at 2021/06/10 23:50.
 *
 * @author Liangcheng Juves
 */
class NewFileTest {

  private static final NewFile file = new NewFile(Constants.EXEC_PATH, "build.gradle");

  @Test
  void bytes() {
    System.out.println(new String(file.bytes()));
  }

  @Test
  void text() {
    System.out.println(file.text());
  }
}
