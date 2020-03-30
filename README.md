Анонимные чат комнаты с произвольным количеством людей в каждой комнате (Бот для телеграм)

src\main\resources\local.properties:

# Конфигурация Telegram
BOT_TOKEN=<Токен>
API_URL=api.telegram.org
API_PROTOCOL=https

# Конфигурация путей для файлов
# Путь состояний комнат/людей
STATE_PATH = src\\main\\resources\\state.json
# Путь к логам каждой комнаты
ROOMS_LOG = src\\main\\resources\\rooms\\

# Количество людей в каждой комнате
MIN_ROOM_CAPACITY = 2
MAX_ROOM_CAPACITY = 4
