# GeoShape

GeoShape is a Java-based project designed to handle and visualize geometric shapes. I
it provides tools for performing calculations, visualizations, 
and interactions with various geometric objects using a graphical user interface (GUI).

## Features

- **Shape Creation**: Define and manage basic geometric shapes (e.g., circles, rectangles).
- **Visualization**: Display shapes graphically using the `StdDraw` library.
- **Calculations**: Perform geometric operations such as area and perimeter calculations.
- **GUI Integration**: User-friendly interface for interacting with shapes.

## Project Structure

The project is organized as follows:

```
GeoShape/
├── src/                   # Source code directory
│   ├── Exe/               # Core project logic
│   │   ├── Ex2/           # Main package
│   │   │   ├── Ex2.java          # Main class for project logic
│   │   │   ├── Ex2_GUI.java      # Graphical User Interface
│   │   │   ├── Ex2Test.java      # Unit tests
│   │   │   ├── StdDraw.java      # Library for drawing shapes
├── Ex3.jar               # Compiled JAR file for execution
├── bin/                  # Compiled class files
├── .project              # Eclipse project file
├── .classpath            # Eclipse classpath file
├── .settings/            # IDE settings
```

## Requirements

- Java 8 or later
- An IDE that supports Java (e.g., Eclipse, IntelliJ IDEA)

## How to Run

1. Clone the repository to your local machine.
2. Open the project in your preferred IDE.
3. Compile and run the `Ex2.java` file to start the application.

Alternatively, you can execute the compiled JAR file:

```bash
java -jar Ex3.jar
```

## Usage

1. Launch the application.
2. Use the GUI to create and manipulate geometric shapes.
3. Perform calculations and visualize the results.

## Contribution

Contributions are welcome! If you find any issues or have suggestions for improvement, feel free to open an issue or submit a pull request.

## Acknowledgments

- The `StdDraw` library for providing simple drawing functionality for the course "Introduction to Computing."
