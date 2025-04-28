
# Test Automation Project

## Описание
Този проект е автоматизирани тестове за API, използващи **RestAssured**, **TestNG** и **Java**. Тестовете са организирани в базов клас, който конфигурира основните параметри (като URL и път за request тялото), и в тестови класове, които изпълняват различни сценарии.

## Структура на проекта

```
src/
├── main/
│   └── java/
│       └── base/
│           └── BaseTest.java          # Базов клас за тестовете
│       └── models/
│           └── CreateUserRequest.java # Модел за създаване на потребители
│       └── tests/
│           └── CreateUserTest.java    # Тест за създаване на потребител
│       └── utils/
│           └── FileUtils.java         # Утилити клас за работа с файлове
└── resources/
    └── config/
        └── dev.properties            # Конфигурация за 'dev' среда
        └── test.properties           # Конфигурация за 'test' среда
    └── requests/
        └── createUserRequest.json   # Тестов JSON файл за създаване на потребител
```

## Инсталация

1. Клонирайте репозитория:

   ```bash
   git clone https://your-repository-url
   cd your-repository-folder
   ```

2. Уверете се, че имате инсталиран **Java** и **Maven**.

3. Инсталирайте зависимостите с Maven:

   ```bash
   mvn install
   ```

## Конфигурация

Конфигурацията за средата се извършва чрез **properties** файлове, разположени в директорията `src/test/resources/config/`. Всеки файл е предназначен за различна среда.

Пример за конфигурационни файлове:
- `dev.properties`
- `test.properties`

Във всеки от тези файлове са зададени важни параметри като `base.url`, `request.bodies.path`, и `env`.

Пример за съдържанието на `dev.properties`:

```properties
base.url=https://reqres.in
request.bodies.path=src/test/resources/requests/
env=dev
```

## Изпълнение на тестовете

### Чрез IntelliJ IDEA

1. Отворете проекта в **IntelliJ IDEA**.
2. Изпълнете тестовете директно от IDE чрез **TestNG**.

### Чрез Maven

Можете да изпълнявате тестовете чрез командата Maven:

```bash
mvn test -Denv=dev
```

или

```bash
mvn test -Denv=test
```

Параметърът `env` трябва да бъде подаден с командата и да съответства на конфигурационния файл, който искате да използвате.

## Тестове

### CreateUserTest

Тестът **CreateUserTest** създава потребител чрез API и проверява дали отговорът съдържа очакваните стойности.

Пример за тестовото изпълнение:
1. Зарежда се request body от JSON файл.
2. Изпраща се POST заявка към API с данните.
3. Проверките включват:
    - Статус код 201.
    - Проверка на полетата `name`, `job`, `id`, и `createdAt`.

```java
@Test
@Parameters({"env"})
public void createUserSuccessfullyTest(String env) {
    // Зареждане на конфигурации и request body
    String requestBody = FileUtils.readFileAsString(baseRequestBodyPath + "createUserRequest.json");
    
    RestAssured
        .given()
        .baseUri(baseUrl)
        .contentType(ContentType.JSON)
        .header("x-api-key", "reqres-free-v1")
        .body(requestBody)
        .when()
        .post("/api/users")
        .then()
        .statusCode(201)
        .body("name", equalTo("Test User"))
        .body("job", equalTo("Automation Tester"))
        .body("id", notNullValue())
        .body("createdAt", notNullValue());
}
```

## Утилити класове

### FileUtils

Утилити класът **FileUtils** съдържа метод за четене на файлове като String. Той се използва за зареждане на request тела от JSON файлове.

```java
public class FileUtils {

    public static String readFileAsString(String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            throw new RuntimeException("Failed to read file: " + filePath, e);
        }
    }
}
```

## Изисквания

- **Java** 11 или по-нова версия.
- **Maven** за управление на зависимостите.
- **TestNG** за изпълнение на тестовете.

## Лиценз

Този проект е с отворен код и се разпространява под **MIT License**.

## Проблеми и поддръжка

Ако срещнете проблеми или имате въпроси, моля, отворете **issue** в GitHub репозитория или се свържете с мен на [email@example.com].
