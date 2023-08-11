## baseHQ
MVVM android app that acts as a vital tool for the users to access relevant products and make data-driven purchasing decisions.

## overview
BaseHQ aims to empower its users with technology-driven solutions to help them
get quality products at all times. The mobile is built with kotlin

## screenshots

## used
- Koin (Dependency Injection)
- Room (Persistence)
- Coroutines (Asynchronous work)
- Retrofit (Network library)

## run app

To build and run an Android project using the Gradle command-line interface (CLI), you need to navigate to the root directory of your Android project where the build.gradle file is located. From there, you can use Gradle commands to build, install, and run your project. Here are the basic steps and commands:

- Navigate to Project Directory:
Open a terminal window and use the cd command to navigate to the root directory of your Android project:

```sh
cd /path/to/your/project
```

- Build the Project:
To build your Android project, including compiling sources and generating the APK, use the following command:

sh
./gradlew build
If you're on Windows, use gradlew.bat instead:

sh
gradlew.bat build

- Install and Run the App on a Connected Device or Emulator:
Once the project is built, you can install and run the app on a connected device or emulator using the installDebug task:

sh
./gradlew installDebug

On Windows:
gradlew.bat installDebug


Remember to replace ./gradlew with gradlew.bat if you're on Windows.
