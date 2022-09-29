@Echo off

echo .
echo ----- build in process.
echo .

cd src
if not exist .\release mkdir release
copy /y manifest.txt release
javac clone/*.java -d release
cd release
jar cfvm ..\..\clone.jar manifest.txt clone
cd ..
rd /s/q release

echo .
echo ---- build complete
echo .
echo to execute you can you the next instruction.
echo .
echo java -jar clone.jar {Module Name}

cd ..
