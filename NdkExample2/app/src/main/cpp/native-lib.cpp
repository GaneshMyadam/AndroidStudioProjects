#include <jni.h>
#include <string>
extern "C"
JNIEXPORT jint JNICALL
Java_com_example_ndkexample_MainActivity_getTextLen(
        JNIEnv* env,
        jobject,
        jstring txt_) {
 const char *txt = env->GetStringUTFChars(txt_,0);
int len= strlen(txt);
 return len;
}