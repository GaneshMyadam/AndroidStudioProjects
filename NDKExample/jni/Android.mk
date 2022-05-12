LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE := jniex
LOCAL_SRC_FILE := jniex.c

include $(BUILD_SHARED_LIBRARY)