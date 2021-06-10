package com.liangchengj.amuse.lang.io;

import com.liangchengj.amuse.lang.util.Constants;
import com.liangchengj.amuse.lang.util.Params;
import com.liangchengj.amuse.lang.util.Strings;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
  public static final void traverseDir(File dir, ITraverseDirListener traverseDirListener) {
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
  public final void traverseIfIsDir(ITraverseDirListener traverseDirListener) {
    if (this.isFile()) return;
    traverseDir(this, traverseDirListener);
  }

  public final File unwrap() {
    return this;
  }

  /**
   * If it is not a directory, convert the current file to a file input stream
   *
   * @return a file input stream {@link FileInputStream}
   */
  public final FileInputStream transformToFileInputStream() {
    Params.requireNonUnsupportedOperation(
        this.isFile(), "Can not transform directory to a file output stream");
    try {
      return new FileInputStream(this);
    } catch (FileNotFoundException e) {
      throw new AssertionError(e);
    }
  }

  /**
   * If it is not a directory, convert the current file to a file output stream
   *
   * @return a file output stream {@link FileOutputStream}
   */
  public final FileOutputStream transformToFileOutputStream() {
    Params.requireNonUnsupportedOperation(
        this.isFile(), "Can not transform directory to a file output stream");
    try {
      return new FileOutputStream(this);
    } catch (FileNotFoundException e) {
      throw new AssertionError(e);
    }
  }

  /**
   * Get the contents of the file in the form of a byte array
   *
   * @return a byte array
   */
  public final byte[] bytes() {
    Params.requireNonUnsupportedOperation(this.isFile(), "Only provide to file");
    return Stream.readAsBytes(transformToFileInputStream());
  }

  /**
   * Get the contents of the file in the form of a string
   *
   * @return a string
   */
  public final String text() {
    Params.requireNonUnsupportedOperation(this.isFile(), "Only provide to file");
    return new String(bytes());
  }
}
