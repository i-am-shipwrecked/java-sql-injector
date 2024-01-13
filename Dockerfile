# Используем образ Java 20
FROM openjdk:20-slim

# Указываем рабочую директорию внутри контейнера
WORKDIR /app

# Копируем собранный JAR-файл в образ
COPY target/sql-injector-atf-1.0-SNAPSHOT.jar app.jar

# Устанавливаем порт, который будет прослушивать приложение
EXPOSE 8080

# Запускаем приложение
ENTRYPOINT ["java","-jar","app.jar"]
