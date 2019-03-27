package parsers.vk_parser.VkApiParser;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.httpclient.HttpTransportClient;

/**
 * Информация о зарегистрированном приложении в ВК:
 * Название: lemmeknow.com
 * ID приложения:	6915826
 * Защищённый ключ: 0J2wwocAJfuGrkcKUqxO
 * Сервисный ключ доступа: 71520c0b71520c0b71520c0b4f713b8af97715271520c0b2dc884c68485171e8451050f
 */
//Ключ доступа пользователя: https://vk.com/dev/access_token
public class VkEventsApiParser {
    public static void ParseVkEventsUsingApi(){
        TransportClient transportClient = HttpTransportClient.getInstance();
        VkApiClient vk = new VkApiClient(transportClient);
    }
}
