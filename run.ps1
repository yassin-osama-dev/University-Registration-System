# Build the project
Write-Host "Compiling..." -ForegroundColor Cyan
javac -d bin -cp ".;C:\Users\Zizo Suliman\javafx-sdk-26.0.1\lib\*" src\*.java

# Check if compilation was successful
if ($LASTEXITCODE -ne 0) {
    Write-Host "Compilation failed!" -ForegroundColor Red
    exit 1
}

# Run the application
Write-Host "Starting GUI..." -ForegroundColor Cyan
java -cp "bin;C:\Users\Zizo Suliman\javafx-sdk-26.0.1\lib\*" --module-path "C:\Users\Zizo Suliman\javafx-sdk-26.0.1\lib" --add-modules javafx.controls,javafx.fxml --enable-native-access=javafx.graphics Main

if ($LASTEXITCODE -eq 0) {
    Write-Host "Application closed successfully" -ForegroundColor Green
} else {
    Write-Host "Application exited with error code: $LASTEXITCODE" -ForegroundColor Red
}
