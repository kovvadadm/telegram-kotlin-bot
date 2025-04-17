# Базовий образ з Java (JDK) + Gradle
FROM gradle:8.5-jdk17 AS build

# Копіюємо проєкт у контейнер
COPY --chown=gradle:gradle . /home/gradle/project
WORKDIR /home/gradle/project

# Будуємо проект без тестів
RUN gradle build -x test

# Створюємо легкий образ лише з JRE
FROM openjdk:17-jdk-slim

# Копіюємо зібраний .jar файл
COPY --from=build /home/gradle/project/app/build/libs/*.jar /app/app.jar

# Запускаємо Telegram-бота
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
