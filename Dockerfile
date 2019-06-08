FROM java:8
VOLUME /tmp
ADD target/lifantou.jar app.jar
RUN bash -c 'touch /app.jar'
EXPOSE 8083
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]