## For more information about using CMake with Android Studio, read the
## documentation: https://d.android.com/studio/projects/add-native-code.html
#
## Sets the minimum version of CMake required to build the native library.
#
#cmake_minimum_required(VERSION 3.10.2)
#
## Creates and names a library, sets it as either STATIC
## or SHARED, and provides the relative paths to its source code.
## You can define multiple libraries, and CMake builds them for you.
## Gradle automatically packages shared libraries with your APK.
#
#add_library( # Sets the name of the library.
#        native-lib
#        # Sets the library as a shared library.
#        SHARED
#        # Provides a relative path to your source file(s).
#        src/main/cpp/native-lib.cpp )
#
## Searches for a specified prebuilt library and stores the path as a
## variable. Because CMake includes system libraries in the search path by
## default, you only need to specify the name of the public NDK library
## you want to add. CMake verifies that the library exists before
## completing its build.
#
#find_library( # Sets the name of the path variable.
#        log-lib
#        # Specifies the name of the NDK library that
#        # you want CMake to locate.
#        log )
#
## Specifies libraries CMake should link to your target library. You
## can link multiple libraries, such as libraries you define in this
## build script, prebuilt third-party libraries, or system libraries.
#
#target_link_libraries( # Specifies the target library.
#        native-lib
#        # Links the target library to the log library
#        # included in the NDK.
#        ${log-lib} )


# -------------------------------------------------------
# ✅ Final CMakeLists.txt for NDK 29 (16KB Alignment Safe)
# -------------------------------------------------------
cmake_minimum_required(VERSION 3.10.2)

# ---------------------------
# Define native library
# ---------------------------
add_library(
        native-lib
        SHARED
        src/main/cpp/native-lib.cpp
)

# ---------------------------
# Find Android log library
# ---------------------------
find_library(log-lib log)

# ---------------------------
# Link libraries
# ---------------------------
target_link_libraries(native-lib ${log-lib})

# ---------------------------
# ✅ Add linker flags for 16KB alignment (NDK 29 supports this)
# ---------------------------
set(CMAKE_SHARED_LINKER_FLAGS
        "${CMAKE_SHARED_LINKER_FLAGS} -Wl,-z,max-page-size=0x4000 -Wl,-z,common-page-size=0x4000"
)

message(STATUS "✅ Applying 16KB alignment flags for native-lib")

#
## Minimum CMake version required
#cmake_minimum_required(VERSION 3.10.2)
#
## ---------------------------
## Native library: native-lib
## ---------------------------
#add_library(
#        native-lib
#        SHARED
#        src/main/cpp/native-lib.cpp
#)
#
## Find Android log library
#find_library(log-lib log)
#
## Link to native logging
#target_link_libraries(native-lib ${log-lib})
#
## ---------------------------
## Optional: If you also have image_processing_util_jni
## ---------------------------
## add_library(
##         image_processing_util_jni
##         SHARED
##         src/main/cpp/image_processing_util_jni.cpp
## )
## target_link_libraries(image_processing_util_jni ${log-lib})
#
## ---------------------------
## Post-build: Fix alignment to 16KB for Play Store compliance
## ---------------------------
## Use llvm-objcopy that comes with the NDK
#find_program(OBJCOPY llvm-objcopy
#        HINTS "${ANDROID_NDK}/toolchains/llvm/prebuilt/${CMAKE_HOST_SYSTEM_NAME_LOWER}/bin"
#)
#
#if (OBJCOPY)
#    message(STATUS "Using objcopy: ${OBJCOPY}")
#
#    add_custom_command(TARGET native-lib POST_BUILD
#            COMMAND "${OBJCOPY}" --set-section-alignment .text=0x4000 $<TARGET_FILE:native-lib>
#            COMMENT "✅ Aligning native-lib.so to 16KB boundaries"
#    )
#
#    # Uncomment below if you also use image_processing_util_jni
#    # add_custom_command(TARGET image_processing_util_jni POST_BUILD
#    #     COMMAND "${OBJCOPY}" --set-section-alignment .text=0x4000 $<TARGET_FILE:image_processing_util_jni>
#    #     COMMENT "✅ Aligning image_processing_util_jni.so to 16KB boundaries"
#    # )
#else()
#    message(WARNING "⚠️ llvm-objcopy not found; .so alignment may not be 16KB")
#endif()
