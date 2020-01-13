#!/usr/bin/env bash

export CATALINA_HOME=/Users/sebastiankalinkowski/Documents/apache-tomcat-9.0.30

stop_tomcat()
{
  $CATALINA_HOME/bin/catalina.sh stop
}

start_tomcat()
{
  $CATALINA_HOME/bin/catalina.sh start
  end
}

rename() {
  rm build/libs/crud.war
  if mv build/libs/tasks-0.0.1-SNAPSHOT.war build/libs/crud.war; then
     echo "Successfully renamed file"
  else
     echo "Cannot rename file"
     fail
  fi
}

copy_file() {
  if cp build/libs/crud.war $CATALINA_HOME/webapps; then
     start_tomcat
  else
     fail
  fi
}
run_browser(){
  open -a Safari http://localhost:8080/crud/v1/task/getTasks
}

fail() {
  echo "There were errors"
}

end() {
  echo "Work is finished. Website has been opened"
}

if ./gradlew build; then
   rename
   copy_file
   run_browser
else
   stop_tomcat
   fail
fi