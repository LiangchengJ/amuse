package com.liangchengj.amuse.codec;

/**
 * Created at 2021/06/10 00:36.
 *
 * @author Liangcheng Juves
 */
public enum HashFnType {
  MD5("MD5"),
  SHA1("SHA-1"),
  SHA256("SHA-256"),
  SHA512("SHA-512");

  private String type;

  HashFnType(String type) {
    this.type = type;
  }

  public String getType() {
    return this.type;
  }
}
