@echo off
cd /d %~dp0
javac --module-path C:\javafx\lib --add-modules javafx.controls,javafx.fxml -cp ".;lib\postgresql.jar" *.java
java --module-path C:\javafx\lib --add-modules javafx.controls,javafx.fxml -cp ".;lib\postgresql.jar" TagihanListrikfx
pause
