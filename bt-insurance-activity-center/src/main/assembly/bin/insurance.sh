#!/bin/sh
APP_NAME=bt-insurance-activity-center
APP_PORT=6080

tpid=`ps -ef|grep $APP_NAME|grep $APP_PORT|grep -v grep|grep -v kill|awk '{print $2}'`
if [ ${tpid} ]; then
        echo 'App is running.'
else
        echo 'App is NOT running.'
fi