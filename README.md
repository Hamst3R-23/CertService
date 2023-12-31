# CertService

## Техническое задание:

* **Бизнес задача:**
    * Есть большая система, состоящая из множества разных сервисов. Необходимо создать
      центрилизованное решение для ообработки сертификатов, входящих в данную систему.
* **Техничесĸая задача:**
    * Необходимо разработать сервис, ĸоторый будет хранить сертифиĸаты, пользователей,
      заĸрепленных за сертифиĸатами, а также роли пользователя.
* **Веб-интерфейс:**
    + Необходимо создать:
        + endpiont, на ĸоторый будет отправляться сертифиĸат в формате pem-файла и, на
          основе содержимого этого сертифиĸата, будет определятся пользователь, ĸоторый стоит
          за этим сертифиĸатом, и его роли. На запрос с сертифиĸатом в ответ ожидается: имя
          пользователя, списоĸ его ролей, ĸратĸая ĸлючевая информация о сертифиĸате (Subject,
          Fingerprint, FingerprintAlgorithm и тп).
        + endpoint-ы, через ĸоторые можно будет зарегистрировать пользоватлей в
          сервисе, т.е создать пользоватлей, приĸрепить ĸ пользователю сертифиĸат, удалить
          пользователя, удалить сертифиĸат для пользователя.
        + endpoint для получения всех доступных ролей, создание ролей,
          удаление ролей.
* **База данных:**
    * Необходимо иметь 3 таблицы:
        * Пользователи
        * Сертифиĸаты
        * Роли
    * За одним пользователем может быть заĸреплено несĸольĸо сертифиĸатов. У одного
      пользователя может быть несĸольĸо ролей.
    * Сертифиĸат заĸреплен за единственным пользователем.
    * Одна роль может быть у любого ĸоличества пользователей.

## URI: http://localhost:8080

### Описание запросов к RestController

#### GET запросы:

* **/role/info**
    + Описание:
        + Возвращает список всех ролей
    + Тело запроса *(JSON)*:
        + без параметров
    + Примеры запросов:
        + (http://localhost:8080/role/info)

* **/user/info**
    + Описание:
        + Возвращает список всех пользователей
    + Тело запроса *(JSON)*:
        + без параметров
    + Примеры запросов:
        + (http://localhost:8080/user/info)

* **/certificate/info**
    + Описание:
        + Возвращает список всех сертификатов
    + Тело запроса *(JSON)*:
        + без параметров
    + Примеры запросов:
        + (http://localhost:8080/certificate/info)

#### POST запросы:

* **/user/addition**
    + Описание:
        + Создание пользователя
    + Тело запроса *(JSON)*:
        + name
    + Примеры запросов:
        + (http://localhost:8080/user/addition)

* **/user/role/addition**
    + Описание:
        + Добавление роли пользователю
    + Тело запроса *(JSON)*:
        + roleId
        + userId
    + Примеры запросов:
        + (http://localhost:8080/user/role/addition)

* **/role/addition**
    + Описание:
        + Создание роли
    + Тело запроса *(JSON)*:
        + name
    + Примеры запросов:
        + (http://localhost:8080/role/addition)

* **/certificate/addition**
    + Описание:
        + Добавление сертификата
    + Тело запроса *(JSON)*:
        + certificate (String)
    + Примеры запросов:
        + (http://localhost:8080/certificate/addition)

* **/user/certificate/addition**
    + Описание:
        + Добавление сертификата пользователю
    + Тело запроса *(JSON)*:
        + userId
        + certificateId
    + Примеры запросов:
        + (http://localhost:8080/user/certificate/addition)

* **/check**
    + Описание:
        + Проверка сертификата на наличие в БД
    + Тело запроса *(JSON)*:
        + certificate (String)
    + Примеры запросов:
        + (http://localhost:8080/check)

* **/check/service** *(используется для сервиса авторизации "CertificateSecurity")*

#### Delete запросы:

* **/user/deletion**
    + Описание:
        + Удаление пользователя
    + Тело запроса *(JSON)*:
        + id (опционально)
        + name (опционально)
    + Примеры запросов:
        + (http://localhost:8080/user/deletion)

* **/user/role/deletion**
    + Описание:
        + Удаление роли пользователя
    + Тело запроса *(JSON)*:
        + roleId
        + userId
    + Примеры запросов:
        + (http://localhost:8080/user/role/deletion)

* **/role/deletion**
    + Описание:
        + Удаление роли
    + Тело запроса *(JSON)*:
        + id (опционально)
        + name (опционально)
    + Примеры запросов:
        + (http://localhost:8080/role/deletion)

* **/certificate/deletion**
    + Описание:
        + Удаление сертификата
    + Тело запроса *(JSON)*:
        + id
    + Примеры запросов:
        + (http://localhost:8080/certificate/deletion)

* **/user/certificate/deletion**
    + Описание:
        + Удаление сертификата пользователя
    + Тело запроса *(JSON)*:
        + userId
        + certificateId
    + Примеры запросов:
        + (http://localhost:8080/user/certificate/deletion)

