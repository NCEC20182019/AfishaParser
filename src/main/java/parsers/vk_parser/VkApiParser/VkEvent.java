package parsers.vk_parser.VkApiParser;

//Это то, что мы хотим получить из API методом
//https://vk.com/dev/groups.search
//https://vk.com/dev/Java_SDK
public class VkEvent {
    private int count;
    private Group[] items;

    public VkEvent(int count, Group[] items) {
        this.count = count;
        this.items = items;
    }

    public int getCount() {

        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Group[] getItems() {
        return items;
    }

    public void setItems(Group[] items) {
        this.items = items;
    }
}
