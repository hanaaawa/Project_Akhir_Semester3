@echo off
cd /d %~dp0
javac --module-path C:\javafx\lib --add-modules javafx.controls,javafx.fxml -cp ".;lib\postgresql-42.7.9.jar" *.java
java --module-path C:\javafx\lib --add-modules javafx.controls,javafx.fxml --enable-native-access=javafx.graphics -cp ".;lib\postgresql-42.7.9.jar" TagihanListrikfx
pause
