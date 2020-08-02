# javafx-springboot-modular-jpackage-gradle-starter
```
git clone https://github.com/mbachmann/javafx-springboot-modular-jpackage-gradle-starter
```

Starter project as a show case on how to integrate javafx with springboot and the badass-jlink-plugin.
JPackage is producing installers for the different platforms.

This starter project has been created with:

- IntelliJ 2020.1
- Gradle 6.5.1
- Spring Boot 2.2.4.RELEASE
- Java FX14
- JDK14 with JPackage (on Win10 with https://wixtoolset.org/)
- JDK11 Target version

## Configure the Project

The configuration can be adjusted in the file build.gradle:

```groovy
ext {
    moduleName = 'javafxspring'
    javaMainClass = 'com.example.javafx.JavaFxSpringApp'
    javafxversion = '14'
    javafxmodules = 'javafx.controls,javafx.fxml,javafx.graphics' // no spaces  between
    platform=getOsName()
    // JLink and JPackage
    appModuleName = moduleName
    appImageName = 'SpringBootJavaFx'
    appInstallerName = 'SpringBootJavaFx'
    // Testing
    jupiterVersion = '5.5.1'
    junitLauncherVersion = '1.5.1'
}
```

The `javafx-springboot-modular-jpackage-gradle-starter` is not using the `Javafx plugin` nor the `modularity plugin`.

## Run the application

Both commands will start the application:

```
 ./gradlew  clean run
```

```
 ./gradlew  clean bootRun
```

### Run the application in Intellij

**Run and debug as Java Gradle Application**

Open the tree in the gradle tool window: project -> Tasks -> application.
There are 2 entries: run and bootRun. On both entries you can run oder debug
the application.

**Run and debug as Java Application**

Select the File `JavaFxSpringApp` in IntelliJ and press run. The application
will start normally. In order to make it work, build gradle contains a
hack. The reason for it is to put the files from the resources folder
ti the build/classes folder, because Intellij does not correctly read
the build/classes **and** the build/resources folder.

```groovy
sourceSets {
    main {
        output.setResourcesDir(java.outputDir)
    }
}
```
or

```groovy
sourceSets.main.output.resourcesDir = sourceSets.main.java.outputDir
```

This hack has been already included in the `build.gradle` file.

## Test

Run the unit tests with:

```
 ./gradlew  clean test
```

## Run as a classic SpringBoot app from the console

For building a fat jar we can use the command from the spring boot plugin:

```
./gradlew  clean bootJar
```

We can start the application with the fat jar:

```
java -jar build/libs/*.jar 
```

## Features of the Starter App

The idea and a part of the projects-view was taken from the repository:
https://github.com/thomasdarimont/spring-labs/tree/master/spring-boot-javafx-jpa-demo.

The starter app is using an H2 embedded memory database. The database setup can be found in the file
`src/main/resources/application.properties`. The app is running under spring-boot `dev` profile.
The dev-Profile triggers a `DataBaseBootstrap` in the config folder. The database schema and the
initial data is loaded from the files `schema.sql` und `data.sql` in the resources folder.

The app is using two entities: Project and Task. The entities are persisted in the database.
The Project-Repository contains two additional methods for eagerly loading the related tasks
to each project.

### Logging
The app is creating a folder javafxspring in the user home directory.
Within this folder is a log folder where the console logs can be reviewed.
The logging can be configures through the file `logback-spring.xml` in the resources folder.
If no logging file is required, just remove the rolling file appender:

```xml
   <root level="info">
      <appender-ref ref="RollingFile" />
      <appender-ref ref="Console" />
   </root>
```

## Build an installer for the MAC

The command builds a dmg type setup for the MAC. The command jpackage must be accessbile from the command line.
JPackage is part of the JDK14. Before starting make sure, there is no old
dmg installer in the finder. If there is one, remove it by ejecting.

```
 ./gradlew  -PinstallerType=dmg clean jpackage 
```

or for more output use:

```
 ./gradlew --info -PinstallerType=dmg clean jpackage 
```


The resulting file can be found under the folder build/jpackage.

## Verifying JLink

JLink is producing a folder `build/image`. The folder 'app' contains all library jar's including our own
`javafx-springboot-modular-jpackage-gradle-starter-1.0.0.jar`. The folder 'bin' contains a `custom jre` to run
the application. The application start can be done through the launcher e.g. launcher.bat, which was produced
by JLink itself.

#### MAC and Linux

The content of the launcher script:
```
#!/bin/sh
DIR="${0%/*}"
"$DIR/java" --add-reads com.example.merged.module=javafxspring --add-opens java.base/java.lang=com.example.merged.module --add-opens java.base/java.io=com.example.merged.module -cp config/ -p "$DIR/../app" -m javafxspring/com.example.javafx.JavaFxSpringApp  "$@"
```

We can start the app with the launcher:

```
build/image/bin/launcher
```

#### Windows

The launcher script produced by JLink:

```
@echo off
set DIR="%~dp0"
set JAVA_EXEC="%DIR:"=%\java"
pushd %DIR% & %JAVA_EXEC% --add-reads com.example.merged.module=javafxspring --add-opens java.base/java.lang=com.example.merged.module --add-opens java.base/java.io=com.example.merged.module -cp config/ -p "%~dp0/../app" -m javafxspring/com.example.javafx.JavaFxSpringApp  %* & popd
```

Start the laucher with:

```
build/image/bin/launcher.bat
```


## Verifying JPackage

JPackage is producing the installer.
For debugging purposes it can be helpful to check the intermediate results.

### Verifying the MAC Installer

Start the app through the launcher:

```
build/jpackage/SpringBootJavaFx.app/Contents/runtime/Contents/Home/bin/launcher
```

Start the app through the MAC launcher:

```
build/jpackage/SpringBootJavaFx.app/Contents/MacOS/SpringBootJavaFx
```
The content of the generated MAC launcher:

#### Verifying the installed application on the MAC

Install the app in the applications folder. You can normally start the app by clicking on it.

Alternativly for debugging purposes we can use the following 3 commands:

```
open /Applications/SpringBootJavaFx.app
```

Verifying the app with a console log output:

```
open /Applications/SpringBootJavaFx.app/Contents/MacOS/SpringBootJavaFx
```

or

```
open /Applications/SpringBootJavaFx.app/Contents/runtime/Contents/Home/bin/launcher
```

#### Verifying the Windows Installer



### The Badass JLink Plugin
The badass-jlink plugin allows you to create custom runtime images for modular applications with
minimal effort. It also lets you create an application installer with the jpackage tool introduced in Java 14.

https://badass-jlink-plugin.beryx.org/releases/latest/#_jpackage

Many modular applications have one or more non-modular dependencies, which are treated as automatic modules by the Java platform.  
However, jlink cannot work with automatic modules.
The plugin combines all non-modular dependencies into a single jar.
This way, only the resulting merged module needs a module descriptor.


## JPackage Instructions

https://docs.oracle.com/en/java/javase/14/jpackage/override-jpackage-resources.html#GUID-1B718F8B-B68D-4D46-B1DB-003D7729AAB6
