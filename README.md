# ex2_starter

# CS520 Spring 2026 - Exercise 2

## Overview and Goal
In this assignment, you will work with an existing implementation of a File Explorer application.
Rather than building brand-new user features, your task is to understand, analyze, and restructure the system using correct by design concepts.

Specifically, we'll be using architecture and design patterns.

## Getting Started
1. Clone the repository: `git clone https://github.com/CS520-Spring2026/ex2_starter`
2. Read this `README.md` file.
3. Build, test, and run the application using the commands below.
4. Explore source code in `src/` and tests in `test/`.

We'll use the ant build tool (`https://ant.apache.org/manual/installlist.html`) to build and run the application.

## Optional Working Files
You may draft your answers in local markdown files (for example under `docs/`) while working, but these files are optional and are not required for grading.
All written graded content must appear in `EX1_answers.pdf`.

## Build and Run

The File Explorer application has the following structure:
- `bin/`: Contains the generated class files
- `jdoc/`: Contains the generated javadoc folders/files
- `lib/`: Contains the third party software jar files
- `src/`: Contains the Java folders and source files
- `test/`: Contains the JUnit test suite source files
- `build.xml`: Is the ant build tool input file
- `build/`: Contains the ant build tool output files

The build requirements are:
- JDK 21+: Generate API doc (javadoc), compile (javac), run (java)
- Ant 1.10.15+: Build and run the application and test suite(s)

From the root directory (containing the build.xml file):

1. Build app: `ant compile`

2. Run the app: `java -cp bin cs520.FileExplorer`

3. Build and run tests: `ant test` (See the build/TEST-*.txt files for more details.)

4. Generate Javadoc: `ant document`

5. Perform linting `ant lint`

6. Clean generated artifacts (e.g., class files, javadoc files): `ant clean`
