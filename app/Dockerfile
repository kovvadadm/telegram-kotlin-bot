# Етап 1: Збирання проєкту
FROM gradle:8.5-jdk17 AS build

# Встановлюємо робочу директорію та копіюємо проєкт
WORKDIR /home/gradle/project
COPY --chown=gradle:gradle . .

# Даємо дозвіл на виконання gradlew
RUN chmod +x ./gradlew

# Збираємо проєкт без тестів
RUN ./gradlew clean build -x test -x check --stacktrace

# Етап 2: Створення фінального образу з легким JRE
FROM openjdk:17-jdk-slim

# Створюємо директорію для застосунку
WORKDIR /app

# Копіюємо зібраний .jar файл із попереднього етапу
COPY --from=build /home/gradle/project/app/build/libs/*.jar /app/app.jar

# Вказуємо команду запуску бота
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
