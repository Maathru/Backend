FROM bellsoft/liberica-openjdk-alpine:21 AS builder

WORKDIR /home/app
ADD ./ /home/app/maathru
RUN sed -i 's/${ACTIVE_PROFILE:dev}/${ACTIVE_PROFILE:prod}/g' /home/app/maathru/src/main/resources/application.yml
RUN chmod +x /home/app/maathru/mvnw
RUN cd maathru && ./mvnw -Dmaven.test.skip=true clean package

FROM bellsoft/liberica-openjre-alpine:21

WORKDIR /home/app
EXPOSE 8080
SHELL ["/bin/sh", "-c"]
ENTRYPOINT java -jar ./maathru.jar
COPY --from=builder /home/app/maathru/target/*.jar maathru.jar