@echo off

set packageName=MSPGrupo04
set srcPath=./src/%packageName%
set outPath=./out
set filename=App

@REM Add Java bin folder to path, in case it isn't already
path c:\Program Files\Java\jdk-17.0.1\bin;%path%

echo   Removing files from %outPath% ...
del /s/q "%outPath%\*" > nul
rmdir /s/q "%outPath%"
mkdir "%outPath%"

echo   Compiling .java files...
javac -encoding UTF8 %srcPath%/*.java -d %outPath%

echo   Creating .jar file...
cd %outPath%
jar cfe ./%filename%.jar %packageName%.Main ./%packageName%/*.class
@REM v flag: verbose output

echo   Created %outPath%/%filename%.jar
echo   To launch the .jar app: java -jar %outPath%/%filename%.jar
@REM PAUSE
