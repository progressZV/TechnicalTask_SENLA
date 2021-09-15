set path = "C:\Users\progr\.jdks\openjdk-15.0.2\bin";

javac -sourcepath ./src -d bin src/com/senla/application/Application.java

java -classpath ./bin com.senla.application.Application

pause