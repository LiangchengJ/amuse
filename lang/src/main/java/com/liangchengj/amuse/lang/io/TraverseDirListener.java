package com.liangchengj.amuse.lang.io;

import java.io.File;

/** @author Liangcheng Juves Created at 2020/05/23 12:08 */
public interface TraverseDirListener {
  void onTraversing(File file);
}
