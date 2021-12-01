@SETLOCAL
@SET "ROOT=%~dp0..\src\main\webapp"
@FOR %%i IN (%*) DO @(
    @CALL :x_%%i
)
@IF "%~1" == "" CALL :x_-w
@ENDLOCAL
@EXIT /B 0

:x_--no-source-map
    @SET "NO_SOURCE_MAP=true"
    @EXIT /b 0

:x_-i
:x_--install
    @CALL npm i nodemon -g
    @CALL npm i uglifyjs-folder -g
    @ECHO *> "%~dp0node_modules\.gitignore"
    @EXIT /b 0

:x_--exec-with-nodemon
:x_-x
:x_--exec
    @CALL :x-uglify "WEB-INF\jsp" "."
    @CALL :x-uglify "WEB-INF\js" "js"
    @EXIT /b 0
:x_-w
:x_--watch
    @CALL npx nodemon -x ".nodemon.bat --exec-with-nodemon" --watch "%ROOT%\WEB-INF" --watch "%~dpnx0"
    @EXIT /b 0

::private

:x-uglify
    @CALL :x-uglify-config "%TMP%\.nodemon.uglify.config.json" "%ROOT%\%~1"
    @CALL npx uglifyjs-folder "%ROOT%\%~1" -x .js -eo "%ROOT%\%~2" --config-file "%TMP%\.nodemon.uglify.config.json"
    @EXIT /b 0

:x-uglify-config
    @SET "SRC_ROOT=%~f2"
    @ECHO {> "%~1"
    @IF NOT "%NO_SOURCE_MAP%" == "true" (
        @ECHO   "sourceMap": {>> "%~1"
        @ECHO     "root": "file:///%SRC_ROOT:\=/%",>> "%~1"
        @ECHO     "url": "{file}.map">> "%~1"
        @ECHO   }>> "%~1"
    )
    @ECHO }>> "%~1"
    @EXIT /b 0