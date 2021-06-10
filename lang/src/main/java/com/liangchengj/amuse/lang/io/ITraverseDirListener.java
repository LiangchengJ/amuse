package com.liangchengj.amuse.lang.io;

import java.io.File;

/**
 * Created at 2020/05/23 12:08
 *
 * @author Liangcheng Juves
 */
public interface ITraverseDirListener {
  void onTraversing(File file);
}
