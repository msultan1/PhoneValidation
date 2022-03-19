#define Docker base image
FROM openjdk:11
LABEL maintianer = "M.s(mohamed.sultan0101@gmail.com)"
ADD target/phoneValidation-0.0.1-SNAPSHOT.jar phoneValidation.jar
ENTRYPOINT ["java" , "-jar", "phoneValidation.jar"]