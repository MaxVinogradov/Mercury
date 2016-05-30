cd %WL_HOME%\..\oracle_common\plugins\maven\com\oracle\maven\oracle-maven-sync\12.2.1

call mvn install:install-file -DpomFile=oracle-maven-sync-12.2.1.pom -Dfile=oracle-maven-sync-12.2.1.jar

pause

call mvn com.oracle.maven:oracle-maven-sync:push -DoracleHome=c:\oracle\middleware\oracle_home\

pause

call mvn help:describe -DgroupId=com.oracle.weblogic -DartifactId=weblogic-maven-plugin -Dversion=12.2.1-0-0

pause
pause
pause