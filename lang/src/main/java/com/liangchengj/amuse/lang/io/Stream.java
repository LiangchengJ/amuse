package com.liangchengj.amuse.lang.io;

import com.liangchengj.amuse.lang.VarArgs;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Objects;

/**
 * Created at 2020/04/30 15:08.
 *
 * @author Liangcheng Juves
 */
public final class Stream {
  private Stream() {}

  /**
   * @param readInputListener
   * @param buf
   * @param len
   * @param finished
   * @throws IOException
   */
  private static void onReading(
      ReadInputListener readInputListener, byte[] buf, int len, boolean finished)
      throws IOException {
    if (null != readInputListener) {
      readInputListener.onReading(Arrays.copyOf(buf, len), finished);
    }
  }

  /**
   * @param inputStream
   * @param readInputListener
   * @throws IOException
   */
  public static void readInputStream(InputStream inputStream, ReadInputListener readInputListener)
      throws IOException {
    int len, endLen = 0;
    final byte[] buf = newBuffer(1024);
    while ((len = inputStream.read(buf)) != -1) {
      onReading(readInputListener, buf, len, false);
      endLen = len;
    }
    onReading(readInputListener, buf, endLen, true);
  }

  /**
   * @param inputStream
   * @return
   */
  public static byte[] readAsBytes(InputStream inputStream) {
    Objects.requireNonNull(inputStream);
    final ByteArrayOutputStream baos = new ByteArrayOutputStream();

    try {
      readInputStream(
          inputStream,
          (buf, finished) -> {
            if (!finished) {
              baos.write(buf);
              baos.flush();
            } else {
              baos.close();
            }
          });
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      return baos.toByteArray();
    }
  }

  /**
   * @param inputStream
   * @return
   */
  public static String readAsString(InputStream inputStream) {
    return new String(readAsBytes(inputStream));
  }

  /**
   * @param inputStream
   * @param outputStreams
   * @throws IOException
   */
  public static void readAndWrite(InputStream inputStream, OutputStream... outputStreams)
      throws IOException {
    ReadInputListener readInputListener = null;
    if (null != outputStreams) {
      readInputListener =
          (buf, finished) -> {
            for (OutputStream outputStream : outputStreams) {
              if (null != outputStream) {
                if (!finished) {
                  outputStream.write(buf);
                  outputStream.flush();
                } else {
                  outputStream.close();
                }
              }
            }
          };
    }
    readInputStream(inputStream, readInputListener);
  }

  /**
   * @param bytes
   * @param outputStreams
   * @throws IOException
   */
  public static void readAndWrite(byte[] bytes, OutputStream... outputStreams) throws IOException {
    readAndWrite(transformBytesToInputStream(bytes), outputStreams);
  }

  /**
   * @param string
   * @param outputStreams
   * @throws IOException
   */
  public static void readAndWrite(String string, OutputStream... outputStreams) throws IOException {
    readAndWrite(string.getBytes(), outputStreams);
  }

  /**
   * @param bytes
   * @return
   */
  public static InputStream transformBytesToInputStream(byte... bytes) {
    Objects.requireNonNull(bytes);
    return new ByteArrayInputStream(VarArgs.arrayOf(bytes));
  }

  /**
   * @param string
   * @return
   */
  public static InputStream transformStringToInputStream(String string) {
    return transformBytesToInputStream(string.getBytes());
  }

  /**
   * @param size
   * @return
   */
  public static byte[] newBuffer(int size) {
    return new byte[size];
  }

  /**
   * @param outputStream
   * @throws IOException
   */
  public static void flushAndClose(OutputStream outputStream) throws IOException {
    Objects.requireNonNull(outputStream);
    outputStream.flush();
    outputStream.close();
  }
}
