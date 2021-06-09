package com.liangchengj.amuse.lang.io;

import com.liangchengj.amuse.lang.util.Constants;
import com.liangchengj.amuse.lang.util.Strings;
import java.io.File;
import java.net.URI;
import java.util.Objects;

/**
 * Created at 2020/05/23 11:39.
 *
 * @author Liangcheng Juves
 */
public class NewFile extends File {

  public NewFile(String pathname) {
    super(pathname);
  }

  public NewFile(String parent, String child) {
    super(parent, child);
  }

  public NewFile(File parent, String child) {
    super(parent, child);
  }

  public NewFile(URI uri) {
    super(uri);
  }

  NewFile(File file) {
    super(file.getAbsolutePath());
  }

  public String getExtension() {
    if (isDirectory()) {
      return Constants.EMPTY_STRING;
    } else {
      return Strings.subWithLastIndex(getName(), ".");
    }
  }

  /**
   * @param dir
   * @param traverseDirListener
   */
  public static final void traverseDir(File dir, TraverseDirListener traverseDirListener) {
    Objects.requireNonNull(dir);
    Objects.requireNonNull(traverseDirListener);

    if (!dir.isDirectory()) {
      throw new UnsupportedOperationException("Only can traverse directory!");
    }

    for (File child : dir.listFiles()) {
      if (child.isDirectory()) {
        traverseDir(child, traverseDirListener);
      } else {
        traverseDirListener.onTraversing(child);
      }
    }
  }

  /** @param traverseDirListener */
  public final void traverseIfIsDir(TraverseDirListener traverseDirListener) {
    if (!this.isDirectory()) return;
    traverseDir(this, traverseDirListener);
  }

  public final File unwrap() {
    return this;
  }
}
