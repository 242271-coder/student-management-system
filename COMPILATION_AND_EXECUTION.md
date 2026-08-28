# COMPILATION AND EXECUTION GUIDE

## Prerequisites
- Java Development Kit (JDK) 8 or higher
- Command line terminal/console
- Text editor or IDE (optional)

## Project Structure
```
student-management-system/
├── src/main/java/com/university/
│   ├── StudentManagementSystem.java
│   ├── Student.java (abstract)
│   ├── UndergraduateStudent.java
│   ├── PostgraduateStudent.java
│   ├── Course.java
│   ├── Grade.java
│   ├── CourseManagement.java (interface)
│   └── exceptions/
│       ├── InvalidStudentException.java
│       └── CourseEnrollmentException.java
├── test/java/com/university/
│   ├── StudentTest.java
│   ├── CourseEnrollmentTest.java
│   └── GradeCalculationTest.java
└── README.md
```

## Step-by-Step Compilation

### 1. Clone the Repository
```bash
git clone https://github.com/vh14704/student-management-system.git
cd student-management-system
```

### 2. Create Output Directory
```bash
mkdir -p bin
```

### 3. Compile Exception Classes (First Priority)
```bash
javac -d bin src/main/java/com/university/exceptions/*.java
```

### 4. Compile Interface
```bash
javac -d bin src/main/java/com/university/CourseManagement.java
```

### 5. Compile Core Classes
```bash
javac -d bin src/main/java/com/university/Course.java
javac -d bin src/main/java/com/university/Grade.java
```

### 6. Compile Student Hierarchy
```bash
javac -d bin src/main/java/com/university/Student.java
javac -d bin src/main/java/com/university/UndergraduateStudent.java
javac -d bin src/main/java/com/university/PostgraduateStudent.java
```

### 7. Compile Main System
```bash
javac -d bin src/main/java/com/university/StudentManagementSystem.java
```

### 8. Compile Test Classes
```bash
javac -d bin -cp bin test/java/com/university/*.java
```

## Alternative: Compile All at Once
```bash
javac -d bin src/main/java/com/university/exceptions/*.java src/main/java/com/university/*.java
javac -d bin -cp bin test/java/com/university/*.java
```

## Step-by-Step Execution

### Run Main System (Complete Demo)
```bash
java -cp bin com.university.StudentManagementSystem
```

**Expected Output:**
- System initialization
- 13 test cases execution
- Student creation, enrollment, and grade recording
- Error handling demonstrations
- Final statistics

### Run Test Suites

#### Test 1: Student Functionality
```bash
java -cp bin com.university.StudentTest
```

**Tests:**
- Valid student creation
- Invalid student creation (email, name, ID)
- Polymorphism demonstration
- GPA calculation
- Postgraduate thesis submission

#### Test 2: Course Enrollment
```bash
java -cp bin com.university.CourseEnrollmentTest
```

**Tests:**
- Valid enrollment
- Capacity management
- Duplicate enrollment prevention
- Drop course functionality
- Invalid course operations

#### Test 3: Grade Calculation
```bash
java -cp bin com.university.GradeCalculationTest
```

**Tests:**
- Grade calculation from marks (A, B, C, D, F)
- Grade point conversion (4.0 scale)
- Invalid marks validation
- Undergraduate GPA calculation
- Postgraduate GPA with thesis

## Windows Batch Script

Create `compile.bat`:
```batch
@echo off
REM Create bin directory
if not exist bin mkdir bin

REM Compile exceptions
echo Compiling exceptions...
javac -d bin src\main\java\com\university\exceptions\*.java

REM Compile core classes
echo Compiling core classes...
javac -d bin src\main\java\com\university\*.java

REM Compile tests
echo Compiling tests...
javac -d bin -cp bin test\java\com\university\*.java

echo.
echo Compilation complete!
echo.
echo Run: java -cp bin com.university.StudentManagementSystem
```

Run with: `compile.bat`

## Linux/Mac Shell Script

Create `compile.sh`:
```bash
#!/bin/bash

# Create bin directory
mkdir -p bin

# Compile exceptions
echo "Compiling exceptions..."
javac -d bin src/main/java/com/university/exceptions/*.java

# Compile core classes
echo "Compiling core classes..."
javac -d bin src/main/java/com/university/*.java

# Compile tests
echo "Compiling tests..."
javac -d bin -cp bin test/java/com/university/*.java

echo ""
echo "Compilation complete!"
echo ""
echo "Run: java -cp bin com.university.StudentManagementSystem"
```

Run with: `chmod +x compile.sh && ./compile.sh`

## Troubleshooting

### Issue: "package com.university does not exist"
**Solution:** Ensure class path includes all compiled classes in `bin` directory
```bash
javac -d bin -cp bin test/java/com/university/*.java
```

### Issue: "cannot find symbol: class Student"
**Solution:** Compile classes in correct order (exceptions → core → derived → main)

### Issue: "ClassNotFoundException"
**Solution:** Ensure `-cp bin` is included in run command
```bash
java -cp bin com.university.StudentManagementSystem
```

## Expected Behavior

✓ All classes compile without errors
✓ Main program runs and executes 13 test cases
✓ All test suites pass with expected outputs
✓ Exception handling works as designed
✓ Polymorphic behavior demonstrated
✓ GPA calculations are accurate

## Running with IDE

### IntelliJ IDEA
1. Open File → New → Project from Existing Sources
2. Select project directory
3. Mark `src` as Sources and `test` as Tests
4. Run → Run 'StudentManagementSystem.main()'

### Eclipse
1. File → Import → Existing Projects into Workspace
2. Create source folders for `src` and `test`
3. Right-click project → Run As → Java Application
4. Select class to run

### VS Code
1. Install Extension Pack for Java
2. Open folder
3. Click Run button on main method or test class
4. Output appears in console

## Performance Notes
- Compilation: < 2 seconds
- Execution: < 1 second
- Memory: < 50 MB
- No external dependencies required

## Verification Checklist

- [ ] All 12 Java files compile without errors
- [ ] Main system runs successfully
- [ ] 13 test cases execute and pass
- [ ] All 3 test suites run independently
- [ ] Exception handling works correctly
- [ ] Polymorphic behavior demonstrated
- [ ] Output matches expected format

---

**Last Updated:** 2026-08-28
**Status:** ✓ Ready for Submission
