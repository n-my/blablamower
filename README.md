# BlaBlaMower

BlaBlaMower is an app written in Scala to run mower simulations according to the company X specifications.

## Build the app

An already compiled JAR is provided in `build/libs/` but feel free to build it for yourself
```bash
./gradlew clean build
``` 
Note: this command will overwrite the provided JAR.

## Run the app

### App usage
```
Usage: com.blablamower.app.BlaBlaMower [options]
  -f, --file <value>  The input file path. Required.
```

### Run the JAR
⚠️ Requires Java 1.8

You may run this app on any platform as long as Java 1.8 is installed.
```bash
java -cp build/libs/blablamower.jar com.blablamower.app.BlaBlaMowerApp --file src/main/resources/input.txt
```
