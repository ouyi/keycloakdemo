# Keycloak and Spring MVC demo

Based on https://github.com/ThomasVitale/spring-keycloak-tutorials

## How to

Start Keycloak instance:

    keycloak-10.0.2/bin$ ./standalone.sh -Djboss.socket.binding.port-offset=100

Start the demo application:

    ./gradlew bootRun

Create realm, client, roles, groups, and users

    realm: public-library-realm
    client: spring-boot-app-client
    roles: member-role, librarian-role (composite)
    groups: reader, admin
    users: alice:alice (reader), bob:bob (reader), chris:chris (admin)

Retrieve an access token:

    curl -s -X POST "http://localhost:8180/auth/realms/public-library-realm/protocol/openid-connect/token" \
        -H "Content-Type: application/x-www-form-urlencoded" \
        -d "username=alice" \
        -d "password=alice" \
        -d "grant_type=password" \
        -d "client_id=spring-boot-app-client"

Use the token:

    curl -i -X GET http://localhost:8080/private --header "Authorization: bearer $TOKEN"
