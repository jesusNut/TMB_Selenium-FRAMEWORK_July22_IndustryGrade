#!/bin/sh
 if [ "$#" -ne 3 ] 
 then
  PROFILE="Smoke" 
  REMOTEURL="http://192.168.0.104:4444"
  BROWSER="chrome"
 else
  PROFILE=$1
  REMOTEURL=$2
  BROWSER=$3 
  fi 
  echo Running Maven profile : $PROFILE
  echo Running on Remote URL : $REMOTEURL
  echo Running on browser :  $BROWSER 
  mvn clean test -P$PROFILE -DremoteURL=$REMOTEURL -Dbrowser=$BROWSER