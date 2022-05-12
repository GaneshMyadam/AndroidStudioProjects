#include<jni.h>
#include<string.h>

jstring Java_com_example_ndkexample_MainActivity_HelloWorld
(JNIEnv* env, jobject obj){

(*env)->NewStringUTF(env," Helloworld from JNI");

}