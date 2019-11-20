call runcrud.bat
if "%ERRORLEVEL%" == "0" goto runurl
echo There were errors in runcrud.bat
goto end

:runurl
start http://localhost:8080/crud/v1/task/getTasks
echo.
echo URL has been opened.

:end
echo.
echo End script showtasks.