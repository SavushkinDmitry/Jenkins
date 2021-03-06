#language: ru
  @1

  Функционал: Поиск на авито
    Структура сценария: Найдем самые дорогие товары на авито
      Пусть открыт ресурс авито
      И в выпадающем списке категорий выбрана оргтехника
      И в поле поиска введено значение <Товар>
      И активирован чекбокс только с фотографией
      Тогда кликнуть по выпадающему списку региона
      Тогда в поле регион введено значение <Регион>
      И нажата кнопка показать объявления
      Тогда открылась страница результата по запросу <Товар>
      И в выпадающем списке сортировка выбрано значение дороже
      И в консоль выведено значение названия и цены <Количество> первых товаров

      Примеры:
      |Товар|Регион|Количество|
      |принтер|Владивосток|3|