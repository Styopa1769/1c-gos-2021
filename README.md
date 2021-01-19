# Практическое задание ГОС-2021. 
## Тема: Алгоритмы и структуры данных. 
## Номер задачи: 2

## Условие задачи
Предложите алгоритм, который позволит эффективно хранить множественные однотипные данные - диагностические сообщения. Каждое сообщение выглядит как одна из шаблонных строк, в которую подставлено несколько значений. Количество шаблонов фиксированное и не больше 1000.
Необходимо уметь эффективно сериализовать/десериализовать подобные сообщения в файл/из файла.
Пример: В модуле ТАКСИ произошла ошибка доступа водителя ИВАНА (В модуле НАЗВАНИЕ_МОДУЛЯ произошла ошибка НАЗВАНИЕ_ОШИБКИ СУБЪЕКТ_ОШИБКИ)

## Описание решения
Решение на написано на Java.

Дополнительно используются <b>maven</b> для сборки и <b>junit</b> для тестирования.

Решение лежит в папке <b>src/main/java</b> и состоит из 3х классов:
1. KMP - реализация алгоритма Кнута-Морриса-Пратта (метод search)
2. Pattern - вспомогательный для KMP класс, содержит в себя информацию о паттерне (в том числе таблицу префиксов)
3. TemplateSerializer - класс, осуществляющий сериализацию/десериализацию для переданного в конструктор шаблона.

Шаблонные значения обозначаются символом <b>#</b>

Пример шаблона (из условия): 
```
В модуле # произошла ошибка # водителя #
```
## Описание тестов
Путь к тестам: <b>src/test/java/TemplateSerializerTest</b>

Написано 3 теста:
1. Проверка корректности сериализации/десериализации для шаблона из условия задачи
2. Проверка корректности сериализации/десериализации для специфичных шаблонов: шаблонное значение только в конце/начале
3. Проверка ошибки валидации (пустой шаблон и шаблон без шаблонных значений)

Чтение данных для тестов происходит из ресурсов (<b>src/main/resources</b>), запись во временный файл, который в конце удаляется.
В тестах сверяется, что контенты файла для сериализации и файла, содержащего результат десериализации, идентичны.

## Запуск тестов
mvn test

## UPD, наконец прочел тред в слаке и понял, что сделал все не так:
сериализация: 
```
.\serialize.sh inputfile outputfile
```
десериализация:
```
.\deserialize.sh inputfile outputfile
```