package com.website.market;

import com.website.market.dto.FilterDto;
import com.website.market.entities.Cart;
import com.website.market.entities.Item;
import com.website.market.entities.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@DisplayName("Получение каталога")
public class CatalogControllerTests extends AbstractAppTest {

    @Test
    public void getTenItems() {
        itemRepository.saveAll(getItems());
    }

    private List<Item> getItems() {
        return IntStream
                .rangeClosed(1, 10)
                .mapToObj(e -> new Item()
                        .setName("name" + e)
                        .setDescription("desc" + e)
                        .setImageUrl("url" + e)
                        .setPrice(BigDecimal.valueOf(e))
                ).collect(Collectors.toList());
    }

    @Test
    @DisplayName("Список каталога с 1 айтемом")
    public void getCatalogWithItemInDb() {
        FilterDto filterDto = new FilterDto(0, 10);
        Item item = new Item();
        item.setName("name");
        item.setDescription("desc");
        item.setImageUrl("url");
        item.setPrice(BigDecimal.valueOf(1));
        itemRepository.save(item);

        var result = catalogController.getCatalog(filterDto);

        assertEquals(1, result.getItems().size());
        var resultItem = result.getItems().getFirst();

        assertNotNull(resultItem.getId());
        assertEquals("name", resultItem.getName());
        assertEquals("url", resultItem.getImageUrl());
        assertEquals(new BigDecimal("1.00"), resultItem.getPrice());
    }

    @Test
    @DisplayName("Список каталога без айтемов в БД")
    public void getCatalogWithNoItemInDb() {
        FilterDto filterDto = new FilterDto(0, 10);

        var result = catalogController.getCatalog(filterDto);

        assertEquals(0, result.getItems().size());
    }

    @Test
    @DisplayName("Список каталога с 2 айтемами в БД")
    public void getCatalogWithTwoItemsInDb() {
        FilterDto filterDto = new FilterDto(0, 10);
        var items = List.of(
                new Item()
                        .setName("name1")
                        .setDescription("desc1")
                        .setImageUrl("url1")
                        .setPrice(BigDecimal.valueOf(1)),
                new Item()
                        .setName("name2")
                        .setDescription("desc2")
                        .setImageUrl("url2")
                        .setPrice(BigDecimal.valueOf(2))
        );
        itemRepository.saveAll(items);

        var result = catalogController.getCatalog(filterDto);
        var resultItem = result.getItems();

        assertEquals(2, result.getItems().size());
        assertNotNull(resultItem.getFirst().getId());
        assertEquals("name1", resultItem.getFirst().getName());
        assertEquals("url1", resultItem.getFirst().getImageUrl());
        assertEquals(new BigDecimal("1.00"), resultItem.getFirst().getPrice());
        assertNotNull(resultItem.getLast().getId());
        assertEquals("name2", resultItem.getLast().getName());
        assertEquals("url2", resultItem.getLast().getImageUrl());
        assertEquals(new BigDecimal("2.00"), resultItem.getLast().getPrice());
    }

    @Test
    @DisplayName("Список каталога с 10 айтемами в БД")
    public void getCatalogWithTenItemsInDb() {
        FilterDto filterDto = new FilterDto(2, 2);

        getTenItems();
        var result = catalogController.getCatalog(filterDto);
        var resultItem = result.getItems();

        assertEquals(2, result.getItems().size());
        assertNotNull(resultItem.getFirst().getId());
        assertEquals("name5", resultItem.getFirst().getName());
        assertEquals("url5", resultItem.getFirst().getImageUrl());
        assertEquals(new BigDecimal("5.00"), resultItem.getFirst().getPrice());
        assertNotNull(resultItem.getLast().getId());
        assertEquals("name6", resultItem.getLast().getName());
        assertEquals("url6", resultItem.getLast().getImageUrl());
        assertEquals(new BigDecimal("6.00"), resultItem.getLast().getPrice());
    }

    @Test
    @DisplayName("Добавление айтема из каталога в корзину пользователя")
    public void addItemFromCatalogToCartUser() {
        User user = new User();
        user.setUsername("admin1");
        user.setPassword("admin1");
        user.setEmail("admin1@gmail.com");
        user.setCart(new Cart());
        Item item = new Item();
        item.setName("name1");
        item.setDescription("desc1");
        item.setImageUrl("url1");
        item.setPrice(BigDecimal.valueOf(1));
        itemRepository.save(item);
        userRepository.save(user);

        Mockito.when(currentUserService.getCurrentUserName()).thenReturn(user.getUsername());
        catalogController.addItemFromCatalogToCart(item.getId());
        var result = cartRepository.findCartWithItemById(user.getCart().getId()).orElseThrow();

        assertNotNull(result);
        assertEquals(1, result.getCartItems().size());
    }


}
