Библиотека для сбора статистики по неким событиям

Например клиент может встроить библиотеку для мониторинга публикаций сообщений в новостной ленте. Библиотека предоставляет клиентам класс с интерфейсом:

1. Учесть событие. На входе время события.

2. Выдать число событий за последнюю минуту (60 секунд).

3. Выдать число событий за последний час (60 минут).

4. Выдать число событий за последние сутки (86400 секунд).

События поступают в систему асинхронно в произвольный момент времени.

Возможна нагрузка как 10000 событий в секунду так, и 2 всего события в час. Библиотека не предоставляет клиентам функциональность постоянного хранения событий и статистики по событиям.

В репозитарии находится исходный код и pom.xml. Для сборки бибилотеки используйте Maven.
Команда для сборки: mvn package
