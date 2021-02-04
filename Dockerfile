FROM openjdk:11
VOLUME /tmp
ADD ./build/libs/mf-customer-api-0.0.1-SNAPSHOT.jar mf-customer-api.jar
ENTRYPOINT ["java","-jar","/*.jar"]