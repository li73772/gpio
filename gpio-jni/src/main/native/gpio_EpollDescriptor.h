/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class gpio_epoll_EpollDescriptor */

#ifndef _Included_gpio_epoll_EpollDescriptor
#define _Included_gpio_epoll_EpollDescriptor
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     gpio_epoll_EpollDescriptor
 * Method:    createEpFd
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_gpio_epoll_EpollDescriptor_createEpFd
  (JNIEnv *, jobject);

/*
 * Class:     gpio_epoll_EpollDescriptor
 * Method:    addFile
 * Signature: (ILjava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_gpio_epoll_EpollDescriptor_addFile
  (JNIEnv *, jobject, jint, jstring);

/*
 * Class:     gpio_epoll_EpollDescriptor
 * Method:    removeFile
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_gpio_epoll_EpollDescriptor_removeFile
  (JNIEnv *, jobject, jint, jint);

/*
 * Class:     gpio_epoll_EpollDescriptor
 * Method:    epollWait
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_gpio_epoll_EpollDescriptor_epollWait
  (JNIEnv *, jobject, jint);

/*
 * Class:     gpio_epoll_EpollDescriptor
 * Method:    closeEpFd
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_gpio_epoll_EpollDescriptor_closeEpFd
  (JNIEnv *, jobject, jint);

#ifdef __cplusplus
}
#endif
#endif
