#!/bin/bash
# This file is meant to be included by the parent cppbuild.sh script
if [[ -z "$PLATFORM" ]]; then
    pushd ..
    bash cppbuild.sh "$@" arrow
    popd
    exit
fi

LLVM_VERSION=7.1.0
ARROW_VERSION=0.15.1
download https://releases.llvm.org/$LLVM_VERSION/llvm-$LLVM_VERSION.src.tar.xz llvm-$LLVM_VERSION.src.tar.xz
download https://www.apache.org/dist/arrow/arrow-$ARROW_VERSION/apache-arrow-$ARROW_VERSION.tar.gz apache-arrow-$ARROW_VERSION.tar.gz

mkdir -p $PLATFORM
cd $PLATFORM
INSTALL_PATH=`pwd`
echo "Decompressing archives..."
tar --totals -xf ../llvm-$LLVM_VERSION.src.tar.xz
tar --totals -xzf ../apache-arrow-$ARROW_VERSION.tar.gz

sedinplace 's/message(FATAL_ERROR "In-source builds are not allowed./message(WARNING "In-source builds are not allowed./g' llvm-$LLVM_VERSION.src/CMakeLists.txt
sedinplace 's/message(FATAL_ERROR "Couldn''t find clang")/message(WARNING "Couldn''t find clang")/g' apache-arrow-$ARROW_VERSION/cpp/cmake_modules/FindLLVM.cmake

COMPONENTS="-DARROW_FLIGHT=ON -DARROW_GANDIVA=ON -DARROW_ORC=ON -DARROW_PARQUET=ON -DARROW_PLASMA=ON -DARROW_DEPENDENCY_SOURCE=BUNDLED -DARROW_VERBOSE_THIRDPARTY_BUILD=ON"

case $PLATFORM in
    linux-armhf)
        cd llvm-$LLVM_VERSION.src
        mkdir -p tblgen
        cd tblgen
        "$CMAKE" -DLLVM_CCACHE_BUILD=ON -DCMAKE_BUILD_TYPE=Release -DLLVM_HOST_TRIPLE=arm-unknown-linux-gnueabihf -DLLVM_DEFAULT_TARGET_TRIPLE=arm-unknown-linux-gnueabihf -DLLVM_TARGET_ARCH=ARM -DLLVM_TARGETS_TO_BUILD=ARM -DLLVM_ENABLE_LIBXML2=OFF -DLLVM_INCLUDE_TESTS=OFF ..
        make -j $MAKEJ llvm-tblgen
        TBLGEN=`pwd`
        mkdir -p ../build
        cd ../build
        export CC="arm-linux-gnueabihf-gcc"
        export CXX="arm-linux-gnueabihf-g++ -std=c++11"
        "$CMAKE" -DCMAKE_EXE_LINKER_FLAGS="-ldl" -DCMAKE_SHARED_LINKER_FLAGS="-ldl" -DLLVM_CCACHE_BUILD=ON -DCMAKE_CROSSCOMPILING=True -DCMAKE_INSTALL_PREFIX="$INSTALL_PATH" -DLLVM_TABLEGEN="$TBLGEN/bin/llvm-tblgen" -DCMAKE_C_FLAGS="-march=armv6 -mfpu=vfp -mfloat-abi=hard" -DCMAKE_CXX_FLAGS="-march=armv6 -mfpu=vfp -mfloat-abi=hard" -DCMAKE_BUILD_TYPE=Release -DLLVM_HOST_TRIPLE=arm-unknown-linux-gnueabihf -DLLVM_DEFAULT_TARGET_TRIPLE=arm-unknown-linux-gnueabihf -DLLVM_TARGET_ARCH=ARM -DLLVM_TARGETS_TO_BUILD=ARM -DLLVM_ENABLE_LIBXML2=OFF -DLLVM_INCLUDE_TESTS=OFF ..
        make -j $MAKEJ
        make install
        cd ../../apache-arrow-$ARROW_VERSION/cpp
        "$CMAKE" -DCMAKE_C_FLAGS="-march=armv6 -mfpu=vfp -mfloat-abi=hard" -DCMAKE_CXX_FLAGS="-march=armv6 -mfpu=vfp -mfloat-abi=hard" -DLLVM_ROOT="$INSTALL_PATH" $COMPONENTS -DARROW_RPATH_ORIGIN=ON -DCMAKE_BUILD_TYPE=Release -DCMAKE_INSTALL_PREFIX="$INSTALL_PATH" -DCMAKE_INSTALL_LIBDIR="lib" -DARROW_BUILD_UTILITIES=OFF .
        make -j $MAKEJ
        make install/strip
        ;;
    linux-arm64)
        cd llvm-$LLVM_VERSION.src
        mkdir -p tblgen
        cd tblgen
        "$CMAKE" -DLLVM_CCACHE_BUILD=ON -DCMAKE_BUILD_TYPE=Release -DLLVM_HOST_TRIPLE=aarch64-unknown-linux-gnu -DLLVM_DEFAULT_TARGET_TRIPLE=aarch64-unknown-linux-gnu -DLLVM_TARGET_ARCH=AArch64 -DLLVM_TARGETS_TO_BUILD=AArch64 -DLLVM_ENABLE_LIBXML2=OFF -DLLVM_INCLUDE_TESTS=OFF ..
        make -j $MAKEJ llvm-tblgen
        TBLGEN=`pwd`
        mkdir -p ../build
        cd ../build
        export CC="aarch64-linux-gnu-gcc"
        export CXX="aarch64-linux-gnu-g++ -std=c++11"
        cd ../../apache-arrow-$ARROW_VERSION/cpp
        "$CMAKE" -DLLVM_CCACHE_BUILD=ON -DCMAKE_CROSSCOMPILING=True -DCMAKE_INSTALL_PREFIX="$INSTALL_PATH" -DLLVM_TABLEGEN="$TBLGEN/bin/llvm-tblgen" -DCMAKE_BUILD_TYPE=Release -DLLVM_HOST_TRIPLE=aarch64-unknown-linux-gnu -DLLVM_DEFAULT_TARGET_TRIPLE=aarch64-unknown-linux-gnu -DLLVM_TARGET_ARCH=AArch64 -DLLVM_TARGETS_TO_BUILD=AArch64 -DLLVM_ENABLE_LIBXML2=OFF -DLLVM_INCLUDE_TESTS=OFF ..
        make -j $MAKEJ
        make install
        "$CMAKE" -DCMAKE_C_FLAGS="-mabi=lp64" -DCMAKE_CXX_FLAGS="-std=c++11 -mabi=lp64" -DLLVM_ROOT="$INSTALL_PATH" $COMPONENTS -DARROW_RPATH_ORIGIN=ON -DCMAKE_BUILD_TYPE=Release -DCMAKE_INSTALL_PREFIX="$INSTALL_PATH" -DCMAKE_INSTALL_LIBDIR="lib" -DARROW_BUILD_UTILITIES=OFF .
        make -j $MAKEJ
        make install/strip
        ;;
    linux-ppc64le)
        cd llvm-$LLVM_VERSION.src
        mkdir -p tblgen
        cd tblgen
        "$CMAKE" -DLLVM_CCACHE_BUILD=ON -DCMAKE_BUILD_TYPE=Release -DLLVM_HOST_TRIPLE=powerpc64le-unknown-linux-gnu -DLLVM_DEFAULT_TARGET_TRIPLE=powerpc64le-unknown-linux-gnu -DLLVM_TARGET_ARCH=PowerPC -DLLVM_TARGETS_TO_BUILD=PowerPC -DLLVM_ENABLE_LIBXML2=OFF -DLLVM_INCLUDE_TESTS=OFF ..
        make -j $MAKEJ llvm-tblgen
        TBLGEN=`pwd`
        mkdir -p ../build
        cd ../build
        export CC="powerpc64le-linux-gnu-gcc"
        export CXX="powerpc64le-linux-gnu-g++ -std=c++11"
        cd ../../apache-arrow-$ARROW_VERSION/cpp
        "$CMAKE" -DLLVM_CCACHE_BUILD=ON -DCMAKE_CROSSCOMPILING=True -DCMAKE_INSTALL_PREFIX="$INSTALL_PATH" -DLLVM_TABLEGEN="$TBLGEN/bin/llvm-tblgen" -DCMAKE_BUILD_TYPE=Release -DLLVM_HOST_TRIPLE=powerpc64le-unknown-linux-gnu -DLLVM_DEFAULT_TARGET_TRIPLE=powerpc64le-unknown-linux-gnu -DLLVM_TARGET_ARCH=PowerPC -DLLVM_TARGETS_TO_BUILD=PowerPC -DLLVM_ENABLE_LIBXML2=OFF -DLLVM_INCLUDE_TESTS=OFF ..
        make -j $MAKEJ
        make install
        "$CMAKE" -DCMAKE_C_FLAGS="-m64" -DCMAKE_CXX_FLAGS="-std=c++11 -m64" -DLLVM_ROOT="$INSTALL_PATH" $COMPONENTS -DARROW_RPATH_ORIGIN=ON -DCMAKE_BUILD_TYPE=Release -DCMAKE_INSTALL_PREFIX="$INSTALL_PATH" -DCMAKE_INSTALL_LIBDIR="lib" -DARROW_BUILD_UTILITIES=OFF .
        make -j $MAKEJ
        make install/strip
        ;;
    linux-x86)
        cd llvm-$LLVM_VERSION.src
        mkdir -p build
        cd build
        export CC="gcc -m32"
        export CXX="g++ -std=c++11 -m32"
        "$CMAKE" -DLLVM_CCACHE_BUILD=ON -DCMAKE_INSTALL_PREFIX="$INSTALL_PATH" -DCMAKE_BUILD_TYPE=Release -DLLVM_TARGETS_TO_BUILD=host -DLLVM_ENABLE_LIBXML2=OFF -DLLVM_INCLUDE_TESTS=OFF ..
        make -j $MAKEJ
        make install
        cd ../../apache-arrow-$ARROW_VERSION/cpp
        "$CMAKE" -DCMAKE_C_FLAGS="-m32" -DCMAKE_CXX_FLAGS="-std=c++11 -m32" -DLLVM_ROOT="$INSTALL_PATH" $COMPONENTS -DARROW_RPATH_ORIGIN=ON -DCMAKE_BUILD_TYPE=Release -DCMAKE_INSTALL_PREFIX="$INSTALL_PATH" -DCMAKE_INSTALL_LIBDIR="lib" -DARROW_BUILD_UTILITIES=OFF .
        make -j $MAKEJ
        make install/strip
        ;;
    linux-x86_64)
        cd llvm-$LLVM_VERSION.src
        mkdir -p build
        cd build
        export CC="gcc -m64"
        export CXX="g++ -std=c++11 -m64"
        "$CMAKE" -DLLVM_CCACHE_BUILD=ON -DCMAKE_INSTALL_PREFIX="$INSTALL_PATH" -DCMAKE_BUILD_TYPE=Release -DLLVM_TARGETS_TO_BUILD=host -DLLVM_ENABLE_LIBXML2=OFF -DLLVM_INCLUDE_TESTS=OFF ..
        make -j $MAKEJ
        make install
        cd ../../apache-arrow-$ARROW_VERSION/cpp
        "$CMAKE" -DCMAKE_C_FLAGS="-m64" -DCMAKE_CXX_FLAGS="-std=c++11 -m64" -DLLVM_ROOT="$INSTALL_PATH" $COMPONENTS -DARROW_RPATH_ORIGIN=ON -DCMAKE_BUILD_TYPE=Release -DCMAKE_INSTALL_PREFIX="$INSTALL_PATH" -DCMAKE_INSTALL_LIBDIR="lib" -DARROW_BUILD_UTILITIES=OFF .
        make -j $MAKEJ
        make install/strip
        ;;
    macosx-*)
        cd llvm-$LLVM_VERSION.src
        mkdir -p build
        cd build
        export CC="clang"
        export CXX="clang++"
        "$CMAKE" -DLLVM_CCACHE_BUILD=ON -DCMAKE_INSTALL_PREFIX="$INSTALL_PATH" -DCMAKE_BUILD_TYPE=Release -DLLVM_TARGETS_TO_BUILD=host -DLLVM_ENABLE_LIBXML2=OFF -DLLVM_INCLUDE_TESTS=OFF ..
        make -j $MAKEJ
        make install
        cd ../../apache-arrow-$ARROW_VERSION/cpp
        "$CMAKE" -DLLVM_ROOT="$INSTALL_PATH" $COMPONENTS -DARROW_RPATH_ORIGIN=ON -DCMAKE_BUILD_TYPE=Release -DCMAKE_INSTALL_PREFIX="$INSTALL_PATH" -DCMAKE_INSTALL_LIBDIR="lib" -DARROW_BUILD_UTILITIES=OFF .
        make -j $MAKEJ
        make install/strip
        ;;
    windows-x86)
        cd llvm-$LLVM_VERSION.src
        mkdir -p build
        cd build
        "$CMAKE" -G "Visual Studio 15 2017" -DLLVM_USE_CRT_RELEASE=MD -DCMAKE_INSTALL_PREFIX="$INSTALL_PATH" -DCMAKE_BUILD_TYPE=Release -DLLVM_TARGETS_TO_BUILD=host -DLLVM_ENABLE_DIA_SDK=OFF -DLLVM_ENABLE_LIBXML2=OFF -DLLVM_INCLUDE_TESTS=OFF -DPYTHON_EXECUTABLE="C:/Python27/python.exe" ..
        MSBuild.exe INSTALL.vcxproj //p:Configuration=Release //p:CL_MPCount=$MAKEJ
        cd ../..
        "$CMAKE" -G "Visual Studio 15 2017" -DLLVM_ROOT="$INSTALL_PATH" $COMPONENTS -DARROW_RPATH_ORIGIN=ON -DCMAKE_BUILD_TYPE=Release -DCMAKE_INSTALL_PREFIX="$INSTALL_PATH" -DCMAKE_INSTALL_LIBDIR="lib" -DARROW_BUILD_UTILITIES=OFF apache-arrow-$ARROW_VERSION/cpp
        MSBuild.exe INSTALL.vcxproj //p:Configuration=Release //p:CL_MPCount=$MAKEJ
        cd apache-arrow-$ARROW_VERSION/cpp
        ;;
    windows-x86_64)
        cd llvm-$LLVM_VERSION.src
        mkdir -p build
        cd build
        "$CMAKE" -G "Visual Studio 15 2017 Win64" -DLLVM_USE_CRT_RELEASE=MD -DCMAKE_INSTALL_PREFIX="$INSTALL_PATH" -DCMAKE_BUILD_TYPE=Release -DLLVM_TARGETS_TO_BUILD=host -DLLVM_ENABLE_DIA_SDK=OFF -DLLVM_ENABLE_LIBXML2=OFF -DLLVM_INCLUDE_TESTS=OFF -DPYTHON_EXECUTABLE="C:/Python27/python.exe" ..
        MSBuild.exe INSTALL.vcxproj //p:Configuration=Release //p:CL_MPCount=$MAKEJ
        cd ../..
        "$CMAKE" -G "Visual Studio 15 2017 Win64" -DLLVM_ROOT="$INSTALL_PATH" $COMPONENTS -DARROW_RPATH_ORIGIN=ON -DCMAKE_BUILD_TYPE=Release -DCMAKE_INSTALL_PREFIX="$INSTALL_PATH" -DCMAKE_INSTALL_LIBDIR="lib" -DARROW_BUILD_UTILITIES=OFF apache-arrow-$ARROW_VERSION/cpp
        MSBuild.exe INSTALL.vcxproj //p:Configuration=Release //p:CL_MPCount=$MAKEJ
        cd apache-arrow-$ARROW_VERSION/cpp
        ;;
    *)
        echo "Error: Platform \"$PLATFORM\" is not supported"
        ;;
esac

cd ../../..
