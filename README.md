# MockMessenger: Android MVVM Messaging App

MockMessenger is a simple messaging application for Android, written in Kotlin, designed to serve as a practical example for implementing the Model-View-ViewModel (MVVM) architecture in Android development. This project provides a mock environment for messaging, allowing developers to understand and implement key MVVM concepts in their own Android applications.

## Features

- **Mocked Messaging**: Simulates a messaging environment for testing and learning purposes.
- **MVVM Architecture**: Demonstrates the use of MVVM architecture, separating concerns to enhance maintainability and scalability.
- **Kotlin Language**: Developed entirely in Kotlin, promoting concise and expressive code.

## Getting Started

### Prerequisites

- Android Studio 4.1.3 or higher
- Kotlin 1.4.21 or higher

### Installation

1. Clone the repository:
  
  ```bash
  git clone https://github.com/RamzyK/MVVM_Message.git
  ```
  
2. Open the project in Android Studio.
  
3. Build and run the application on an Android emulator or a physical device.
  

## MVVM Architecture Overview

The project follows the MVVM architectural pattern, which separates the application into three main components:

1. **Model**: Represents the data and business logic of the application. In this project, the `Message` class serves as the model.
  
2. **View**: Responsible for displaying the user interface and capturing user input. Activities and Fragments in the `ui` package constitute the view layer.
  
3. **ViewModel**: Acts as a mediator between the model and the view. The `MessageViewModel` class, found in the `viewmodel` package, manages the UI-related data.
  

## Project Structure

- **`app`**: Contains the main Android application code.
  - `manifests`: AndroidManifest.xml file.
  - `java`: Java packages.
    - `com.example.messagingapp`: Main package.
      - `db`: Local database component
      - `di`: Package where we implement Dependency Injection with Koin
      - `model`: Data models.
      - `services`: Retrofit services with the endpoints
      - `repositories`: Data layer implemented in the repositories used by the viewmodels
      - `utils`: Utils class and code for the app
      - `view`: User interface components.
      - `viewmodel`: ViewModel classes.
  - `res`: Android resources.
- **`gradle`**: Gradle wrapper and configuration files.

## Dependencies

The project uses the following dependencies:

- [AndroidX](https://developer.android.com/jetpack/androidx)
- [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
- [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)
- [RxKotlin](https://github.com/ReactiveX/RxKotlin)
- [Room DB](https://developer.android.com/training/data-storage/room?hl=fr)
- [Koin](https://insert-koin.io)
- [Glide](https://github.com/bumptech/glide)
- [Retrofit](https://square.github.io/retrofit/)
- [OkHttp Client](https://square.github.io/okhttp/)

Check the `build.gradle` file for the latest version information.

## Contributing

Feel free to contribute by submitting issues or pull requests. Follow the [contribution guidelines](CONTRIBUTING.md) for more details.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgments

- Inspired by the Android MVVM architecture.
- Special thanks to the Android and Kotlin communities.

Happy coding! 🚀
