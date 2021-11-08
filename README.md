# Scrape 100 top handphone products Tokopedia

## Requirement
* Java 11
* Maven
* Chrome Driver - (https://github.com/SeleniumHQ/selenium/wiki/ChromeDriver)

## Project Build
```mvn clean install```

## JAR Build
```mvn clean compile assembly:single```

JAR file can be found in target directory.

## Run
```java -jar {jar_file} {path/to/chrome/driver/executable/file}```

Include the ChromeDriver location in your system PATH environment variable or specify the location of ChromeDriver through the command argument
