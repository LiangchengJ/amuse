package com.liangchengj.amuse.lang.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;

/**
 * Created at 2020/04/30 14:50.
 *
 * @author Liangcheng Juves
 */
public final class Resource {

  private Resource() {}

  private static ClassLoader classLoader = Resource.class.getClassLoader();

  /**
   * @param name
   * @return
   */
  public static InputStream getAsStream(String name) {
    return classLoader.getResourceAsStream(name);
  }

  /**
   * @param clazz
   * @param name
   * @return
   */
  public static InputStream getAsStream(Class<?> clazz, String name) {
    return clazz.getResourceAsStream(name);
  }

  /**
   * @param name
   * @return
   */
  public static URL getAsURL(String name) {
    return classLoader.getResource(name);
  }

  /**
   * @param name
   * @return
   */
  public static String getAsURLPath(String name) {
    return getAsURL(name).getPath();
  }

  /**
   * @param name
   * @return
   * @throws IOException
   */
  public static Enumeration<URL> getAsURLs(String name) throws IOException {
    return classLoader.getResources(name);
  }
}
