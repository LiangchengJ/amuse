package com.liangchengj.amuse.codec;

import org.junit.jupiter.api.Test;

/**
 * Created at 2021/06/10 00:39.
 *
 * @author Liangcheng Juves
 */
class HashFnTest {

  private static final String string = "Liangcheng Juves";

  @Test
  void MD5() {
    System.out.println(HashFn.MD5(string.getBytes()));
  }

  @Test
  void SHA1() {
    System.out.println(HashFn.SHA1(string.getBytes()));
  }

  @Test
  void SHA256() {
    System.out.println(HashFn.SHA256(string.getBytes()));
  }

  @Test
  void SHA512() {
    System.out.println(HashFn.SHA512(string.getBytes()));
  }
}
