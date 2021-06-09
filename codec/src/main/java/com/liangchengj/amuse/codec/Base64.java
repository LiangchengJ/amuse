package com.liangchengj.amuse.codec;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * Created at 2020/8/11 15:26.
 *
 * @author Liangcheng Juves
 */
public final class Base64 {
  private Base64() {}

  private static boolean reflectAndroid;

  private static Class<?> reflectAndroidBase64Class;
  private static Class<?> reflectJDKBase64Class;

  private static final String JDK_BASE64_CLASS_NAME = "java.util.Base64";

  static {
    try {
      reflectAndroidBase64Class = Class.forName(JDK_BASE64_CLASS_NAME.replace("java", "android"));
      reflectAndroid = true;
    } catch (ClassNotFoundException e) {
      reflectAndroid = false;
      try {
        reflectJDKBase64Class = Class.forName(JDK_BASE64_CLASS_NAME);
      } catch (ClassNotFoundException ex) {
        // Ignore
      }
    }
  }

  public enum AndroidBase64Flag {
    DEFAULT("DEFAULT"),
    NO_PADDING("NO_PADDING"),
    NO_WRAP("NO_WRAP"),
    CRLF("CRLF"),
    URL_SAFE("URL_SAFE"),
    NO_CLOSE("NO_CLOSE");

    private String name;

    AndroidBase64Flag(String name) {
      this.name = name;
    }
  }

  public enum AndroidBase64CodecType {
    ENCODE,
    DECODE
  }

  /**
   * @param androidBase64CodecType
   * @param input
   * @param androidBase64Flags
   * @return
   * @throws UnsupportedOperationException
   */
  public static byte[] invokeAndroidBase64Codec(
      AndroidBase64CodecType androidBase64CodecType,
      byte[] input,
      AndroidBase64Flag... androidBase64Flags)
      throws UnsupportedOperationException {

    Objects.requireNonNull(androidBase64CodecType);
    Objects.requireNonNull(input);

    String codecMethodName =
        androidBase64CodecType == AndroidBase64CodecType.ENCODE ? "encode" : "decode";
    String unsupportedOperationMsg =
        String.format("Can't invoke android.util.Base64.%s(byte[], int)", codecMethodName);

    if (null == reflectAndroidBase64Class) {
      throw new UnsupportedOperationException(unsupportedOperationMsg);
    }

    try {
      final class FieldReflection {
        private FieldReflection() {}

        private int getFlag(AndroidBase64Flag androidBase64Flag)
            throws NoSuchFieldException, IllegalAccessException {
          return reflectAndroidBase64Class
              .getDeclaredField(androidBase64Flag.name)
              .getInt(reflectAndroidBase64Class);
        }
      }
      FieldReflection fieldReflection = new FieldReflection();
      int flags = fieldReflection.getFlag(AndroidBase64Flag.DEFAULT);
      if (null != androidBase64Flags) {
        for (AndroidBase64Flag androidBase64Flag : androidBase64Flags) {
          flags |= fieldReflection.getFlag(androidBase64Flag);
        }
      }

      Method codecMethod =
          reflectAndroidBase64Class.getDeclaredMethod(codecMethodName, byte[].class, int.class);
      return (byte[]) codecMethod.invoke(reflectAndroidBase64Class, input, flags);
    } catch (NoSuchFieldException
        | IllegalAccessException
        | NoSuchMethodException
        | InvocationTargetException e) {
      throw new UnsupportedOperationException(unsupportedOperationMsg, e);
    }
  }

  public enum JDKBase64CodecType {
    NORMAL_ENCODE("getEncoder"),
    MIME_ENCODE("getMimeEncoder"),
    URL_SAFE_ENCODE("getUrlEncoder"),
    ////////////////////////////////////////////
    ////////////////////////////////////////////
    NORMAL_DECODE("getDecoder"),
    MIME_DECODE("getMimeDecoder"),
    URL_SAFE_DECODE("getUrlDecoder");

    private String methodName;

    JDKBase64CodecType(String methodName) {
      this.methodName = methodName;
    }
  }

  /**
   * @param src
   * @param jdkBase64CodecType
   * @return
   * @throws UnsupportedOperationException
   */
  public static byte[] invokeJDKBase64Codec(byte[] src, JDKBase64CodecType jdkBase64CodecType)
      throws UnsupportedOperationException {

    Objects.requireNonNull(src);
    Objects.requireNonNull(jdkBase64CodecType);

    boolean isByEncode = jdkBase64CodecType.methodName.toLowerCase().contains("encode");
    String coderClassSimpleName = isByEncode ? "Encoder" : "Decoder";
    String codecMethodName = isByEncode ? "encode" : "decode";

    String unsupportedOperationMsg =
        String.format(
            "Can't invoke java.util.Base64.%s.%s(byte[])", coderClassSimpleName, codecMethodName);

    if (null == reflectJDKBase64Class) {
      throw new UnsupportedOperationException(unsupportedOperationMsg);
    }

    try {
      Method reflectionCoderMethod =
          reflectJDKBase64Class.getDeclaredMethod(jdkBase64CodecType.methodName);
      Object coder = reflectionCoderMethod.invoke(reflectJDKBase64Class);

      Method codecMethod = coder.getClass().getDeclaredMethod(codecMethodName, byte[].class);
      return (byte[]) codecMethod.invoke(coder, src);
    } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
      throw new UnsupportedOperationException(unsupportedOperationMsg, e);
    }
  }

  public static byte[] encodeToBytes(byte[] src) {
    try {
      if (reflectAndroid) {
        // Invoke android.util.Base64.encode(byte[], int)
        // Such as: android.util.Base64.encode(src, android.util.Base64.NO_WRAP);
        return invokeAndroidBase64Codec(
            AndroidBase64CodecType.ENCODE, src, AndroidBase64Flag.NO_WRAP);
      } else {
        // Invoke java.util.Base64.Encoder.encode(byte[])
        // Such as: java.util.Base64.getEncoder().encode(src);
        return invokeJDKBase64Codec(src, JDKBase64CodecType.NORMAL_ENCODE);
      }
    } catch (UnsupportedOperationException e) {
      throw new AssertionError("Can't invoke Base64.encodeToBytes(byte[])", e);
    }
  }

  public static byte[] encodeToBytes(String src) {
    return encodeToBytes(src.getBytes());
  }

  public static String encodeToString(byte[] src) {
    return new String(encodeToBytes(src));
  }

  public static String encodeToString(String src) {
    return encodeToString(src.getBytes());
  }

  public static byte[] decodeToBytes(byte[] src) {
    try {
      if (reflectAndroid) {
        // Invoke android.util.Base64.decode(byte[], int)
        // Such as: android.util.Base64.decode(src, android.util.Base64.NO_WRAP);
        return invokeAndroidBase64Codec(
            AndroidBase64CodecType.DECODE, src, AndroidBase64Flag.NO_WRAP);
      } else {
        // Invoke java.util.Base64.Decoder.decode(byte[])
        // Such as: java.util.Base64.getDecoder().decode(src);
        return invokeJDKBase64Codec(src, JDKBase64CodecType.NORMAL_DECODE);
      }
    } catch (UnsupportedOperationException e) {
      throw new AssertionError("Can't invoke Base64.decodeToBytes(byte[])", e);
    }
  }

  public static byte[] decodeToBytes(String src) {
    return decodeToBytes(src.getBytes());
  }

  public static String decodeToString(byte[] src) {
    return new String(decodeToBytes(src));
  }

  public static String decodeToString(String src) {
    return decodeToString(src.getBytes());
  }

  public static byte[] mimeEncodeToBytes(byte[] src) {
    try {
      if (reflectAndroid) {
        // Invoke android.util.Base64.encode(byte[], int)
        // Such as: android.util.Base64.encode(src, android.util.Base64.CRLF);
        return invokeAndroidBase64Codec(AndroidBase64CodecType.ENCODE, src, AndroidBase64Flag.CRLF);
      } else {
        // Invoke java.util.Base64.Encoder.encode(byte[])
        // Such as: java.util.Base64.getMimeEncoder().encode(src);
        return invokeJDKBase64Codec(src, JDKBase64CodecType.MIME_ENCODE);
      }
    } catch (UnsupportedOperationException e) {
      throw new AssertionError("Can't invoke Base64.mimeEncodeToBytes(byte[])", e);
    }
  }

  public static byte[] mimeEncodeToBytes(String src) {
    return mimeEncodeToBytes(src.getBytes());
  }

  public static String mimeEncodeToString(byte[] src) {
    return new String(mimeEncodeToBytes(src));
  }

  public static String mimeEncodeToString(String src) {
    return mimeEncodeToString(src.getBytes());
  }

  public static byte[] mimeDecodeToBytes(byte[] src) {
    try {
      if (reflectAndroid) {
        // Invoke android.util.Base64.decode(byte[], int)
        // Such as: android.util.Base64.decode(src, android.util.Base64.CRLF);
        return invokeAndroidBase64Codec(AndroidBase64CodecType.DECODE, src, AndroidBase64Flag.CRLF);
      } else {
        // Invoke java.util.Base64.Encoder.decode(byte[])
        // Such as: java.util.Base64.getMimeDecoder().decode(src);
        return invokeJDKBase64Codec(src, JDKBase64CodecType.MIME_DECODE);
      }
    } catch (UnsupportedOperationException e) {
      throw new AssertionError("Can't invoke Base64.mimeDecodeToBytes(byte[])", e);
    }
  }

  public static byte[] mimeDecodeToBytes(String src) {
    return mimeDecodeToBytes(src.getBytes());
  }

  public static String mimeDecodeToString(byte[] src) {
    return new String(mimeDecodeToBytes(src));
  }

  public static String mimeDecodeToString(String src) {
    return mimeDecodeToString(src.getBytes());
  }

  public static byte[] urlEncodeToBytes(byte[] src) {
    try {
      if (reflectAndroid) {
        // Invoke android.util.Base64.encode(byte[], int)
        // Such as: android.util.Base64.encode(src, android.util.Base64.URL_SAFE |
        // android.util.Base64.NO_WRAP);
        return invokeAndroidBase64Codec(
            AndroidBase64CodecType.ENCODE,
            src,
            AndroidBase64Flag.URL_SAFE,
            AndroidBase64Flag.NO_WRAP);
      } else {
        // Invoke java.util.Base64.Encoder.encode(byte[])
        // Such as: java.util.Base64.getUrlEncoder().encode(src);
        return invokeJDKBase64Codec(src, JDKBase64CodecType.URL_SAFE_ENCODE);
      }
    } catch (UnsupportedOperationException e) {
      throw new AssertionError("Can't invoke Base64.urlEncodeToBytes(byte[])", e);
    }
  }

  public static byte[] urlEncodeToBytes(String src) {
    return urlEncodeToBytes(src.getBytes());
  }

  public static String urlEncodeToString(byte[] src) {
    return new String(urlEncodeToBytes(src));
  }

  public static String urlEncodeToString(String src) {
    return urlEncodeToString(src.getBytes());
  }

  public static byte[] urlDecodeToBytes(byte[] src) {
    try {
      if (reflectAndroid) {
        // Invoke android.util.Base64.decode(byte[], int)
        // Such as: android.util.Base64.decode(src, android.util.Base64.URL_SAFE |
        // android.util.Base64.NO_WRAP);
        return invokeAndroidBase64Codec(
            AndroidBase64CodecType.DECODE,
            src,
            AndroidBase64Flag.URL_SAFE,
            AndroidBase64Flag.NO_WRAP);
      } else {
        // Invoke java.util.Base64.Encoder.decode(byte[])
        // Such as: java.util.Base64.getUrlDecoder().decode(src);
        return invokeJDKBase64Codec(src, JDKBase64CodecType.URL_SAFE_DECODE);
      }
    } catch (UnsupportedOperationException e) {
      throw new AssertionError("Can't invoke Base64.urlDecodeToBytes(byte[])", e);
    }
  }

  public static byte[] urlDecodeToBytes(String src) {
    return urlDecodeToBytes(src.getBytes());
  }

  public static String urlDecodeToString(byte[] src) {
    return new String(urlDecodeToBytes(src));
  }

  public static String urlDecodeToString(String src) {
    return urlDecodeToString(src.getBytes());
  }
}
