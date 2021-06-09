package com.liangchengj.amuse.lang.io;

import java.io.IOException;

/**
 * Created at 2020/05/20 10:18.
 *
 * @author Liangcheng Juves
 */
public interface ReadInputListener {
  void onReading(byte[] buf, boolean finished) throws IOException;
}
