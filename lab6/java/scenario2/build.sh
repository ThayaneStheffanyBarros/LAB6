#!/bin/bash

BASE_DIR=$(dirname -- "$( readlink -f -- "$0"; )")

# Compila o código direcionando a saída para a pasta bin
#javac -d $BASE_DIR/bin $BASE_DIR/*.java
javac -d bin java/base/*.java java/scenario1/*.java
