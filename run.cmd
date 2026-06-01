@echo off
REM Build the project
echo Compiling...
javac -d bin -cp ".;C:\Users\Zizo Suliman\javafx-sdk-26.0.1\lib\*" src\*.java

REM Run the application
echo Starting GUI...
java -cp "bin;C:\Users\Zizo Suliman\javafx-sdk-26.0.1\lib\*" --module-path "C:\Users\Zizo Suliman\javafx-sdk-26.0.1\lib" --add-modules javafx.controls,javafx.fxml --enable-native-access=javafx.graphics Main

pause
