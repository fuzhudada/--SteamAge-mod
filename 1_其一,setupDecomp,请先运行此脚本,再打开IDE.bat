@echo off
echo %JAVA_TOOL_OPTIONS% | find "-Dfile.encoding=" > nul
chcp 65001
call gradlew.bat setupDecompWorkspace idea genIntellijRuns
pause
