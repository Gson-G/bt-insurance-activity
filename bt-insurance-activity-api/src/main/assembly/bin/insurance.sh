#!/bin/sh
APP_NAME=bt-insurance-activity-api
APP_PORT=6090

tpid=`ps -ef|grep $APP_NAME|grep $APP_PORT|grep -v grep|grep -v kill|awk '{print $2}'`
if [ ${tpid} ]; then
        echo 'App is running.'
else
        echo 'App is NOT running.'
fi