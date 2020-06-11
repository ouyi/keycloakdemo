# Keycloak and Spring MVC demo

Based on https://github.com/ThomasVitale/spring-keycloak-tutorials

## How to

Start Keycloak instance:

    keycloak-10.0.2/bin$ ./standalone.sh -Djboss.socket.binding.port-offset=100

Start the demo application:

    ./gradlew bootRun
