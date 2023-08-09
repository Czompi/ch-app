# ch-app
This application is an ordering system and saves orders &amp; products in separate json files.
After you specified what you'd order, the application will show you how much will it cost.

To run this application, the minimum Java version is 17.

The following packages are used:
- Gson
- Log4j
- Lombok

## Build application
To build this application, you can simply run the following command.
You'll find the generated jar file at 
`<project_dir>/build/libs` folder.

### Linux/macOS
```shell
gradle assemble
```

### Windows
#### Powershell
```powershell
.\gradlew assemble
```

#### Command Prompt
```shell
gradlew assemble
```

## Run application
### No arguments
You can run the application without any arguments
```shell
java -jar ch-app-<version>.jar
```

### Json arguments
This application handles all the arguments as part of a json string, and it'll try to convert it to a String list.
It will return the total cost of the items ordered in the arguments
#### Example
```shell
java -jar ch-app-<version>.jar ["A", "B", "C"]
```