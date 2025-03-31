#!/bin/bash

set -e

# 📁 Customize these paths if needed
HEADER_DIR=native-worker/include
SRC_DIR=native-worker/src
OUTPUT_DIR=quarkus-service/src/main/java
LIB_NAME=audio
PACKAGE_NAME=com.normalize.audio
LIB_OUTPUT_DIR=quarkus-service

echo "🔧 Compiling C code to lib${LIB_NAME}.so..."
clang -I${HEADER_DIR} -shared -fPIC -o ${LIB_OUTPUT_DIR}/lib${LIB_NAME}.so ${SRC_DIR}/${LIB_NAME}.c

echo "🔁 Running jextract..."
./jextract-22/bin/jextract \
  -I${HEADER_DIR} \
  --library ${LIB_NAME} \
  --use-system-load-library \
  --output ${OUTPUT_DIR} \
  --target-package ${PACKAGE_NAME} \
  ${HEADER_DIR}/${LIB_NAME}.h

echo "✅ Done: lib${LIB_NAME}.so built and Java bindings generated in ${OUTPUT_DIR}"
