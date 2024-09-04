#!/bin/bash
# NOTE: Update these variables to target different files with this script. #
MAIN="com.zunescape.app.App"
PROJECT="src/main/java/com/zunescape"
# ======================================================================== #
CP_DELIM=":"
if [[ "$OSTYPE" == "cygwin" || "$OSTYPE" == "msys" || "$OSTYPE" == "win32" ]]; then
    CP_DELIM=";" # changes to ; for Windows
fi
# ======================================================================== #
if [[ $1 == "" || $1 == "default" ]]
then
    javac -d target -cp "lib/*${CP_DELIM}src" $PROJECT/**/*.java
elif [[ $1 == "run" ]]
then
    javac -d target -cp "lib/*${CP_DELIM}src" "src/main/java/${MAIN//.//}.java"
    java -cp "lib/*${CP_DELIM}target" $MAIN "${@:2}"
elif [[ $1 == "docs" ]]
then
    javadoc -cp "lib/*${CP_DELIM}src" -d docs $PROJECT/**/*.java
elif [[ $1 == "fmt" ]]
then
    java -jar lib/google-java-format.jar --replace --skip-javadoc-formatting $PROJECT/**/*.java
elif [[ $1 == "sync" ]]
then
    git add -A
    git commit -m "Syncs changes"
    git fetch origin main
    git pull origin main
    git push origin main
elif [[ $1 == "submit" ]]
then
    java -jar lib/google-java-format.jar --replace --skip-javadoc-formatting $PROJECT/**/*.java
    git add -A
    git commit -m "Submits assignment"
    git fetch origin main
    git pull origin main
    git push origin main
elif [[ $1 == "clean" ]]
then
    rm -r target/*
else
    echo "build: *** No rule to make target '$1'.  Stop."
fi
