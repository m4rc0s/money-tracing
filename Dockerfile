FROM openjdk:17.0.2 AS build

WORKDIR build-context

ADD . /build-context

RUN curl -O https://download.newrelic.com/newrelic/java-agent/newrelic-agent/current/newrelic-java.zip

RUN ./gradlew build

FROM openjdk:17.0.2

ENV NEW_RELIC_APP_NAME="cash-flow-service"
ENV NEW_RELIC_LICENSE_KEY="922840f9a1ae6e6d4791ea240e55c3beb8bdNRAL"
ENV NEW_RELIC_LOG_FILE_NAME=STDOUT
ENV ENVIRONMENT="dev"
ENV JAVA_OPTS="$JAVA_OPTS -javaagent:newrelic-java.zip"

EXPOSE 9007

CMD ["java", "-Dnewrelic.environment=$ENVIRONMENT", "-jar", "build/libs/app.jar"]