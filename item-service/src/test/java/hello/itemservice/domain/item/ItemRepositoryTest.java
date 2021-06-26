package hello.itemservice.domain.item;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Item item = new Item("itemA", 10000, 10);

        //when
        Item savedItem = itemRepository.save(item);

        //then
        Item findItem = itemRepository.findById(item.getId());
        assertThat(findItem).isEqualTo(savedItem);
    }

    @Test
    void findAll() {
        //given
        Item item1 = new Item("itemA", 10000, 10);
        Item item2 = new Item("itemB", 20000, 5);

        itemRepository.save(item1);
        itemRepository.save(item2);

        //when
        List<Item> itemList = itemRepository.findAll();

        //then
        assertThat(itemList.size()).isEqualTo(2);
        assertThat(itemList).contains(item1);
        assertThat(itemList).contains(item2);

    }

    @Test
    void updateItem() {
        //given
        Item item = new Item("itemA", 10000, 10);

        Item savedItem = itemRepository.save(item);

        //when
        Item updateParam = new Item("itemB", 20000, 5);
        itemRepository.updateItem(item.getId(), updateParam);

        //then
        Item findItem = itemRepository.findById(item.getId());

        assertThat(findItem.getItemName()).isEqualTo(updateParam.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(updateParam.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(updateParam.getQuantity());
    }


}