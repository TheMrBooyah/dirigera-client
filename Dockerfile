ARG BUILD_FROM
FROM $BUILD_FROM

ARG BUILD_ARCH

RUN apt update -y \
    && apt install openjdk-17-jre -y \
    && java --version

COPY ./dirigera-client-mqtt/target/dirigera-client-mqtt.jar /dirigera
COPY ./rootfs /
