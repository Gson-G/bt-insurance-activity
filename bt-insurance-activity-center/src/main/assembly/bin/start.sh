#!/bin/sh
cd `dirname $0`
BIN_DIR=`pwd`
cd ..
DEPLOY_DIR=`pwd`
APP_NAME=bt-insurance-activity-center
APP_DIR="${DEPLOY_DIR}/lib"
rm -f tpid

nohup java -Dloader.path=$DEPLOY_DIR/lib -jar -Xms3096m -Xmx3096m -Dprofile=$1 $APP_DIR/$APP_NAME*.jar  > $BIN_DIR/run.log 2>&1 &

echo $! > tpid

echo Start Success!