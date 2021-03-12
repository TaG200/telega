import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


public class JavaBot extends TelegramLongPollingBot {

    private String name = "Covid_java_bot";
    private final String token = "1447295310:AAHKG6wsZlNXWytRj9aEx-G76qhScPbCjKo";

    @Override
    public void onUpdateReceived(Update update) { //Тут будет отправка регеонов в ф-р
        String region = update.getMessage().getText();

        if (region.equals("/start")){
            sendMsg(update.getMessage().getChatId().toString(),
                    "Данный бот предоставляет статистику заболеваний COVID-19.\n" +
                            "Для начала работы введите интересующий вас регион.\n" +
                            "Например, Амурская область \n");
        }

        DataCollection data = new DataCollection();
        Filter filter = new Filter(data.getDoc());
        String answer = filter.getData(filter.getTable(), region);

        sendMsg(update.getMessage().getChatId().toString(), answer);

    }

    public synchronized void sendMsg(String chatId, String s) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(s);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return name;
    }

    @Override
    public String getBotToken() {
        return token;
    }
}

