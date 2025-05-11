# Spring Boot Custom Exception Handler Starter

Этот Spring Boot Starter предоставляет автоматическую конфигурацию для обработки исключений в вашем приложении. Он включает в себя:

* Базовые классы исключений (`BaseException`, `BaseLogException`) с поддержкой HTTP статусов и логирования.
* Глобальный обработчик исключений (`ExceptionHandlerAdvice`), который перехватывает исключения и возвращает стандартизированные ответы в формате `ApiResponse`.

## Как использовать

Чтобы включить этот кастомный стартер в вашем Spring Boot приложении, выполните следующие шаги:

1.  **Добавьте зависимость в ваш `pom.xml` (Maven):**
    
    ```xml
    <dependency>
        <groupId>dev.inovexx</groupId>
        <artifactId>exception-handler-starter</artifactId>
        <version>0.0.1</version>
    </dependency>
    ```

2.  **Включите обработку исключений:**

    По умолчанию, обработка исключений **отключена**. Чтобы активировать ее, вам необходимо добавить следующую строку в ваш файл `application.properties` или `application.yml`:

    **application.properties:**

    ```properties
    exception-handler.enabled=true
    ```

    **application.yml:**

    ```yaml
    exception-handler:
      enabled: true
    ```

    Если вы не добавите эту настройку или установите `exception-handler.enabled=false`, автоматическая конфигурация обработчика исключений не будет применена.

## Дополнительная информация

Этот стартер предоставляет базовую структуру для обработки исключений. Вы можете расширять классы `BaseException` и `BaseLogException`, а также настраивать обработку в `ExceptionHandlerAdvice` в соответствии с требованиями вашего приложения.

Для получения дополнительной информации о классах исключений и обработчике, обратитесь к JavaDoc в исходном коде стартера.