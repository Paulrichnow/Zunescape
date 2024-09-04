param(
    $rule = "default"
)
<# NOTE: Update these variables to target different files with this script. #>
$MAIN = "com.zunescape.app.App"
$PROJECT = "src/main/java/com/zunescape"
# ======================================================================== #
$CP_DELIM = ";"
if ( $IsMacOS -or $IsLinux ) {
    $CP_DELIM = ":" # changes to : for Mac or Linux
}
<# ======================================================================== #>
if ( $rule -eq "" -or $rule -eq "default" ) {
    javac -d target -cp "lib/*$($CP_DELIM)src" $PROJECT/**/*.java
} elseif ( $rule -eq "run" ) {
    javac -d target -cp "lib/*$($CP_DELIM)src" "src/main/java/$($MAIN.replace('.', '/')).java"
    java -cp "lib/*$($CP_DELIM)target" $MAIN $args
} elseif ( $rule -eq "docs" ) {
    javadoc -cp "lib/*$($CP_DELIM)target" -d docs $PROJECT/**/*.java
} elseif ( $rule -eq "fmt" ) {
    java -jar lib/google-java-format.jar --replace --skip-javadoc-formatting $PROJECT/**/*.java
} elseif ( $rule -eq "sync" ) {
    git add -A
    git commit -m "Syncs changes"
    git fetch origin main
    git pull origin main
    git push origin main
} elseif ( $rule -eq "submit" ) {
    java -jar lib/google-java-format.jar --replace --skip-javadoc-formatting $PROJECT/**/*.java
    git add -A
    git commit -m "Submits assignment"
    git fetch origin main
    git pull origin main
    git push origin main
} elseif ( $rule -eq "clean" ) {
    rm -r target/*
} else {
    Write-Output "build: *** No rule to make target '$rule'.  Stop."
}
