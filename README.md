# RESTfulHomeWork

**<span color="orange">Важно!</span><br>Перед началом сборки настройте подключение к БД в файле application.properties**

#### Сборка

Linux:
```
$ ./gradlew build
```
Windows:
```
> gradlew.bat build
```

#### Запуск
```
$ java -jar build/libs/rest-homework-1.0.jar
Запускать нужно находясь в корне проекта. 
Сервер запустится на том порту, который указан в application.properties.
По-умолчанию :8080
```
## Список команд
результат | путь | метод | Content-Type | параметры
-------|------|--------|------|--------
получить список студентов | /student | GET | |
создать студента | /student | POST | application/json | {"first_name":"","last_name":""}
удалить студента | /student/{id} | DELETE | | id студента
получить студента по id | /student/{id} | GET | | id студента
получить список всех групп со студентами | /group | GET | |
создать группу | /group | POST | application/json | {"number":""}
удалить группу | /group/{id} | DELETE | | id группы
переместить студента в группу | /group/{id}/student | PUT | application/x-www-form-urlencoded | id группы, student_id=""
удалить студента из группы | /group/{id}/student/{student_id} | DELETE | | id группы; {student_id} - id студента
