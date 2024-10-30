/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class Main */

#ifndef _Included_Main
#define _Included_Main
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     Main
 * Method:    getBoard
 * Signature: ()[[I
 */
JNIEXPORT jobjectArray JNICALL Java_Main_getBoard
  (JNIEnv *, jobject);

/*
 * Class:     Main
 * Method:    makeMovement
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_Main_makeMovement
  (JNIEnv *, jobject, jint, jint, jint);

/*
 * Class:     Main
 * Method:    gameOver
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_Main_gameOver
  (JNIEnv *, jobject);

/*
 * Class:     Main
 * Method:    getNextPlayer
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_Main_getNextPlayer
  (JNIEnv *, jobject);

#ifdef __cplusplus
}
#endif
#endif
