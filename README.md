[![Build status](https://ci.appveyor.com/api/projects/status/g7qfliyjkhfk8xt7?svg=true)](https://ci.appveyor.com/project/Maskinka/pageobject)



## Задача №1: Page Object's

Вам необходимо добить тестирование функции перевода с карты на карту. 

Для этого разработчики захардкодили одного пользователя:
```
* login: 'vasya'
* password: 'qwerty123'
* verification code (hardcoded): '12345'
* cards:
    * first:
        * number: '5559 0000 0000 0001'
        * balance: 10 000 RUB
    * second:
        * number: '5559 0000 0000 0002'
        * balance: 10 000 RUB
```

После логина, который уже мы сделали на лекции, вы получите список карт.

Нажав на кнопку «Пополнить», вы перейдёте на страницу перевода средств.

При успешном переводе вы вернётесь назад на страницу со списком карт.

Это ключевой кейс, который нужно протестировать.

Нужно, чтобы вы через Page Object's добавили доменные методы:
* перевода с определённой карты на другую карту энной суммы,
* проверки баланса по карте со страницы списка карт.
