# javafx-springboot-modular-jpackage-gradle-starter
```
git clone https://github.com/mbachmann/javafx-springboot-modular-jpackage-gradle-starter
```

Starter project as a show case on how to integrate javafx with springboot and the badass-jlink-plugin.
JPackage is producing installers for the different platforms.

![splash-screen](https://github.com/mbachmann/javafx-springboot-modular-jpackage-gradle-starter/raw/master/assets/app/splash-screen.png)

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

![main-view](https://github.com/mbachmann/javafx-springboot-modular-jpackage-gradle-starter/raw/master/assets/app/main-view.png)

The app is using two entities: Project and Task. The entities are persisted in the database.
The Project-Repository contains two additional methods for eagerly loading the related tasks
to each project.

### Profiles and Database Options

The starter app is using an `H2 embedded memory` or `mysql database`.
The database setup can be found in the folder `src/main/resources/`:

| property file                          | profile         | database  | migration                          |
|:---------------------------------------|:----------------|:----------|:-----------------------------------|
| application-dev.properties             |  dev            | H2-Memory |db/schema-h2.sql and db/data-h2.sql |
| application-mysql.properties           |  prod           | MySQL     |db/schema-mysql.sql                 |
| application-liquibase-h2.properties    |  liquibase-h2   | H2-Memory |db/changelog/db.changelog-master.xml|
| application-liquibase-mysql.properties |  liquibase-mysql| MySQL     |db/changelog/db.changelog-master.xml|


The profile can be set in the file `application.properties`:

```
# Switch between dev and prod or liquibase-h2 and liquibase-mysql
# dev = H2 database, prod = mysql database
# liquibase profiles are not working after JPackage 
spring.profiles.active=${STAGE:dev}
```

In the src/main/java/config folder is a `DataBaseBootstrap` class which
saves at the first start a dummy project and a dummy task .

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

![logging-to-user-home](https://github.com/mbachmann/javafx-springboot-modular-jpackage-gradle-starter/raw/master/assets/app/logging-to-user-home.png)

## Build an installer for the MAC

The commands build a dmg or pkg type setup for the MAC. The command jpackage must be accessbile from the command line.
JPackage is part of the JDK14. Before starting make sure, there is no old
dmg installer in the finder. If there is one, remove it by ejecting.

```
 ./gradlew  -PinstallerType=dmg clean jpackage 
```

```
 ./gradlew  -PinstallerType=pkg clean jpackage 
```

or for more output use `--info`:

```
 ./gradlew --info -PinstallerType=dmg clean jpackage 
```

The packaging through the badass-jlink-plugin uses the following tasks:

- compileJava
- processResources
- classes
- jar
- prepareMergedJarsDir
- createMergedModule
- createDelegatingModules
- prepareModulesDir
- jlink
- jpackageImage
- jpackage


The resulting file can be found under the folder build/jpackage.

![dmg-in-JPackage.jpg](https://github.com/mbachmann/javafx-springboot-modular-jpackage-gradle-starter/raw/master/assets/mac/dmg-in-JPackage.jpg)

<br>

Drag and drop the file to the desktop. The install symbol gets visible.


![dmg](https://github.com/mbachmann/javafx-springboot-modular-jpackage-gradle-starter/raw/master/assets/mac/dmg.png)

## Build an installer for Windows

The commands build a msi or exe type setup for the Windows 10 operating system. Make
sure the Wit Toolset is already installed (download from https://wixtoolset.org/).
The command jpackage must be accessbile from the command line.
JPackage is part of the `JDK14`. Depending on the availabilty of `--win-per-user-install` in
the `JPackage installerOptions` (`build.gradle`) the install path will be:

- without `--win-per-user-install` -> install path `C:\Program Files\SpringBootJavaFx`
- with    `--win-per-user-install` -> install path `C:\Users\<UserName>\AppData\Local\SpringBootJavaFx`

```
 gradlew  -PinstallerType=msi clean jpackage 
```

```
 gradlew  -PinstallerType=exe clean jpackage 
```
<br>
The resulting file can be found under build/JPackage.

![windows-jpackage-structure](https://github.com/mbachmann/javafx-springboot-modular-jpackage-gradle-starter/raw/master/assets/win/windows-jpackage-structure.png)

<br>
Drag and drop the installer file to the desktop. The install symbol gets visible.

![msi-installer](https://github.com/mbachmann/javafx-springboot-modular-jpackage-gradle-starter/raw/master/assets/win/msi-installer.png) &nbsp;&nbsp; ![setupexe](https://github.com/mbachmann/javafx-springboot-modular-jpackage-gradle-starter/raw/master/assets/win/setupexe.png)

<br>
Start the installer. The application will be installed to the target folder (depending on `--win-per-user-install` )

![destination-folder-with-win-per-user-install](https://github.com/mbachmann/javafx-springboot-modular-jpackage-gradle-starter/raw/master/assets/win/destination-folder-with-win-per-user-install.png)

<br>
The installerOptions '--win-menu-group',"$appImageName"' provides an own window menu group:

![windows-group](https://github.com/mbachmann/javafx-springboot-modular-jpackage-gradle-starter/raw/master/assets/win/windows-group.png)

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
