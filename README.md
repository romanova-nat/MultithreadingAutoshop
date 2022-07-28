### Задача 2. Продвинутый автосалон

### Описание
Модифицируйте код предыдущей задачи таким образом, чтобы не использовались стандартные средства синхронизации, а только расширенные. Также необходимо соблюдать "честность" покупателей. Пусть те, кто пришел за машиной первее - уезжают также непоследними

### Работа программы
1. Создание потока-производителя и потока-покупателя
2. Покупатель покупает товар, если это возможно
3. С некоторой периодичностью производитель выкладывает товар на рынок

### Требования к программе
1. Каждый ключевой этап должен сопровождаться выводом в консоль текущего статуса, например: Производитель Toyota выпустил 1 автомобиль
2. Все задержки (время сборки авто, таймаут желания купить машину) должны быть оформлены в константах (никаких "Магических чисел")
3. Должно быть несколько потоков-покупателей, которые будут желать несколько раз купить машину. Производитель выпускает по 1 машине. Программу можно завершать после продажи 10 машин
4. Покупатели должны получать свои покупки в порядке очереди

### Пример
```
Покупатель1 зашел в автосалон
Машин нет
Покупатель3 зашел в автосалон
Машин нет
Покупатель2 зашел в автосалон
Машин нет
Производитель Toyota выпустил 1 авто
Покупатель1 уехал на новеньком авто
Производитель Toyota выпустил 1 авто
Покупатель3 уехал на новеньком авто
Покупатель1 зашел в автосалон
Машин нет
Производитель Toyota выпустил 1 авто
Покупатель2 уехал на новеньком авто
...
```

<details>
  <summary>Подсказка</summary>
  
  Используйте ReentrantLock с соблюдением "честности"
</details>
