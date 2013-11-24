/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class gpio_EpollDescriptor */

#ifndef _Included_gpio_EpollDescriptor
#define _Included_gpio_EpollDescriptor
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     gpio_EpollDescriptor
 * Method:    createEpFd
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_gpio_EpollDescriptor_createEpFd
  (JNIEnv *, jobject);

/*
 * Class:     gpio_EpollDescriptor
 * Method:    addFile
 * Signature: (ILjava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_gpio_EpollDescriptor_addFile
  (JNIEnv *, jobject, jint, jstring);

/*
 * Class:     gpio_EpollDescriptor
 * Method:    epollWait
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_gpio_EpollDescriptor_epollWait
  (JNIEnv *, jobject, jint);

/*
 * Class:     gpio_EpollDescriptor
 * Method:    closeEpFd
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_gpio_EpollDescriptor_closeEpFd
  (JNIEnv *, jobject, jint);

#ifdef __cplusplus
}
#endif
#endif