/**
 * Created at 2020/7/21 13:47.
 * 
 * @author Liangcheng Juves
 */
#ifndef _TCHA_H
#define _TCHA_H

#ifdef __cplusplus
extern "C"
{
#endif

#include <jni.h>

    /* env->GetStringUTFChars */
    char const *jstr_cstr(JNIEnv *env, jstring jstr);
    /* env->NewStringUTF */
    jstring cstr_jstr(JNIEnv *env, char const *cs);
    /* env->GetObjectClass */
    jclass envgoc(JNIEnv *env, jobject jobj);
    /* env->FindClass */
    jclass envfc(JNIEnv *env, char const *name);
    /* env->GetMethodID */
    jmethodID envgmid(JNIEnv *env, jclass jclz, char const *name, char const *sig);
    /* env->GetStaticMethodID */
    jmethodID envgsmid(JNIEnv *env, jclass jclz, char const *name, char const *sig);

    jstring jobj_tjstr(JNIEnv *env, jobject jobj);
    char const *jobj_tcstr(JNIEnv *env, jobject jobj);

    inline char const *jstr_cstr(JNIEnv *env, jstring jstr)
    {
        return (*env)->GetStringUTFChars(env, jstr, JNI_FALSE);
    }

    inline jstring cstr_jstr(JNIEnv *env, char const *cs)
    {
        return (*env)->NewStringUTF(env, cs);
    }

    inline jclass envgoc(JNIEnv *env, jobject jobj)
    {
        return (*env)->GetObjectClass(env, jobj);
    }

    inline jclass envfc(JNIEnv *env, char const *name)
    {
        return (*env)->FindClass(env, name);
    }

    inline jmethodID envgmid(JNIEnv *env, jclass jclz, char const *name, char const *sig)
    {
        return (*env)->GetMethodID(env, jclz, name, sig);
    }

    inline jmethodID envgsmid(JNIEnv *env, jclass jclz, char const *name, char const *sig)
    {
        return (*env)->GetStaticMethodID(env, jclz, name, sig);
    }

    jstring jobj_tjstr(JNIEnv *env, jobject jobj)
    {
        jclass jclz_Object = envgoc(env, jobj);
        jmethodID jmid_toString = envgmid(env, jclz_Object, "toString", "()Ljava/lang/String;");
        return (jstring)(*env)->CallObjectMethod(env, jobj, jmid_toString);
    }

    inline char const *jobj_tcstr(JNIEnv *env, jobject jobj)
    {
        return jstr_cstr(env, jobj_tjstr(env, jobj));
    }

#ifdef __cplusplus
}
#endif

#endif /* _TCHA_H */