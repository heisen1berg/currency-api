Инструкция по деплою:
Запустить startscript.bat (нужен рабочий Docker). Работу можно проверить командами из request.txt. Если нужно изменить файлы проекта, то перед запуском startscript.bat, пересобрать jar с помощью repackage.bat.

Описание API
core/
	config/
		ApplicationConfig - файл конфигурации, с некоторыми бинами
	controller/
		ExchangeController - контроллер для маппинга /exchange (1 метод)
		StatsController - контроллер для маппинга /stats (3 метода, можно посмотреть в request.txt)
	data/
		entity/
			ExchangeEntity - ORM для БД (Long id, Long userId, Float value, String currencyFrom, String currencyTo)
		model/
			ExchangeRequest - объект, который приходит при запросе на exchange (Long userId, Float value, String currencyFrom, String currencyTo)
			ExchangeResponse - объект, которым мы отвечаем на exchange (Long id, Double value)
	handle/
		exchange/
			ExchangeHandler - интерфейс для логики обработки exchange-запросов
			DefaultExchangeHandler - имплементация ExchangeHandler
			Exchanger - интерфейс для получения курса
			DefaultOnlineExchanger - имплементация Exchanger по умолчанию, использует 3-rd party api для получения курса
			OfflineExchanger - имплементация Exchanger, извлекает курс из текстового файла
		stats/
			StatsHandler - интерфейс для логики обработки stats-запросов
			DefaultStatsHandler - имплементация StatsHandler
	io/
		CurrencyReader - интерфейс для получения курса из файла
		ListCurrencyReader - имплементация CurrencyReader, читает файл построчно как список со всеми доступными курсами (файл currency.txt)
	repository/
		ExchangeRepository - стандартный CRUD-репозиторий для работы с БД
	ApplicationStarte - Main class
	
	