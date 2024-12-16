FROM amazoncorretto:17.0.8
WORKDIR /app
COPY build/libs/SHMS.jar /app/SHMS.jar
ENTRYPOINT ["java", "-jar", "SHMS.jar"]
#docker build -t shms-frontend . 
#docker run --rm -d --name shms-service --network photo-app-network --env-file .env -p 9090:9090 shms-service