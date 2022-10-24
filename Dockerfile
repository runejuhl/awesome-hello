FROM bitnami/java:17-debian-11

MAINTAINER Rune Juhl Jacobsen <rune.juhl@atea.dk>

COPY ./target/uberjar/awesome-hello-0.1.0-SNAPSHOT-standalone.jar /usr/local/lib/

ENTRYPOINT ["java", "-jar", "/usr/local/lib/awesome-hello-0.1.0-SNAPSHOT-standalone.jar"]
