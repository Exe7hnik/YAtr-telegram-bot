package ru.atroshenko.telegrambot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import ru.atroshenko.telegrambot.entities.*;
import ru.atroshenko.telegrambot.repositories.*;
import ru.atroshenko.telegrambot.Constants.*;

@SpringBootTest
public class FillingTests {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ClientOrderRepository clientOrderRepository;

    @Autowired
    private OrderProductRepository orderProductRepository;

    private Category createSaveCategory(String name, Category parent) {
        Category category = new Category();
        category.setName(name);
        category.setParent(parent);
        return categoryRepository.save(category);
    }

    private Category getCategoryByName(String name) {
        Category category = new Category();
        category.setName(name);
        return categoryRepository.findOne(Example.of(category)).orElse(null);
    }

    private void createSaveProduct(String name, Double price, String description, Category category) {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setDescription(description);
        product.setCategory(category);
        productRepository.save(product);
    }

    private void createSaveClient(String fullName, String address, String phoneNumber, Long externalId) {
        Client client = new Client();
        client.setFullName(fullName);
        client.setAddress(address);
        client.setPhoneNumber(phoneNumber);
        client.setExternalId(externalId);
        clientRepository.save(client);
    }

    private Client getClientByName(String name) {
        return clientRepository.getClientByFullName(name);
    }

    private void createSaveClientOrder(Integer status, Double total, Client client) {
        ClientOrder clientOrder = new ClientOrder();
        clientOrder.setStatus(status);
        clientOrder.setTotal(total);
        clientOrder.setClient(client);
        clientOrderRepository.save(clientOrder);
    }

    private void createSaveOrderProduct(ClientOrder clientOrder, Product product, Long countProduct) {
        OrderProduct orderProduct1 = new OrderProduct();
        orderProduct1.setClientOrder(clientOrder);
        orderProduct1.setProduct(product);
        orderProduct1.setCountProduct(countProduct);
        orderProductRepository.save(orderProduct1);
    }

    /**
     * Создание Категорий
     */
    @Test
    void createCategories() {
        categoryRepository.deleteAll();

        Category parentCategory = createSaveCategory(CategoryNames.CATEGORIES, null);

        Category rollsCategory = createSaveCategory(CategoryNames.ROLL, parentCategory);
        createSaveCategory(CategoryNames.CLASSIC_ROLL, rollsCategory);
        createSaveCategory(CategoryNames.BAKED_ROLL, rollsCategory);
        createSaveCategory(CategoryNames.SWEET_ROLL, rollsCategory);
        createSaveCategory(CategoryNames.PACK_ROLL, rollsCategory);

        Category burgersCategory = createSaveCategory(CategoryNames.BURGERS, parentCategory);

        createSaveCategory(CategoryNames.CLASSICAL_BURGER, burgersCategory);
        createSaveCategory(CategoryNames.SPICY_BURGER, burgersCategory);

        Category drinksCategory = createSaveCategory(CategoryNames.DRINKS, parentCategory);

        createSaveCategory(CategoryNames.CARBONATED_DRINKS, drinksCategory);
        createSaveCategory(CategoryNames.ENERGY_DRINKS, drinksCategory);
        createSaveCategory(CategoryNames.JUICE, drinksCategory);
        createSaveCategory(CategoryNames.OTHER, drinksCategory);
    }

    /**
     * Создание продуктов
     */
    @Test
    void createProducts() {
        productRepository.deleteAll();

        Category classicRolls = getCategoryByName(CategoryNames.CLASSIC_ROLL);
        Category backedRolls = getCategoryByName(CategoryNames.BAKED_ROLL);
        Category sweetRolls = getCategoryByName(CategoryNames.SWEET_ROLL);
        Category packRoll = getCategoryByName(CategoryNames.PACK_ROLL);
        Category classicalBurger = getCategoryByName(CategoryNames.CLASSICAL_BURGER);
        Category spicyBurger = getCategoryByName(CategoryNames.SPICY_BURGER);
        Category carbonatedDrinks = getCategoryByName(CategoryNames.CARBONATED_DRINKS);
        Category energyDrinks = getCategoryByName(CategoryNames.ENERGY_DRINKS);
        Category juice = getCategoryByName(CategoryNames.JUICE);
        Category other = getCategoryByName(CategoryNames.OTHER);

        createSaveProduct("Классический ролл", 32.2,"Описание", classicRolls);
        createSaveProduct("Классический ролл2", 342.2,"Описание2", classicRolls);
        createSaveProduct("Классический ролл3", 312.2,"Описание3", classicRolls);
        createSaveProduct("ПЕЧЕНЫЙ ролл", 322.2,"Описание1", backedRolls);
        createSaveProduct("ПЕЧЕНЫЙ ролл2", 322.12,"Описание2", backedRolls);
        createSaveProduct("ПЕЧЕНЫЙ ролл3", 3224.2,"Описание3", backedRolls);
        createSaveProduct("Сладкий ролл", 32232.2,"Описаниkjhе", sweetRolls);
        createSaveProduct("Наборы", 32233232.2322,"Описаниеhjk", packRoll);
        createSaveProduct("Классический бургер1", 120.0,"Бургер 1", classicalBurger);
        createSaveProduct("Классический бургер2", 150.0,"Бургер 2", classicalBurger);
        createSaveProduct("Классический бургер3", 160.5,"Бургер 3", classicalBurger);
        createSaveProduct("Острый бургер 1", 90.0,"Острый бургер 1", spicyBurger);
        createSaveProduct("Острый бургер 2", 10.0,"Острый бургер 2", spicyBurger);
        createSaveProduct("Острый бургер 3", 900.0,"Острый бургер 3", spicyBurger);
        createSaveProduct("Газированый напиток кола", 50.0,"Кола", carbonatedDrinks);
        createSaveProduct("Газированый напиток холодный чай", 50.0,"Холодный чай с лимоном", carbonatedDrinks);
        createSaveProduct("Газированый напиток фанта", 60.0,"Фанта оригинал", carbonatedDrinks);
        createSaveProduct("Энергетик 1", 60.0,"Энергетик 1 с чабрецом", energyDrinks);
        createSaveProduct("Энергетик 2", 70.0,"Энергетик 2 с малиной", energyDrinks);
        createSaveProduct("Энергетик 3", 80.0,"Энергетик 3 с лимоном", energyDrinks);
        createSaveProduct("Сок 1", 30.0,"Сок персиковый", juice);
        createSaveProduct("Сок 2", 30.0,"Сок яблочный", juice);
        createSaveProduct("Сок 3", 30.0,"Сок мультифрукт", juice);
        createSaveProduct("Другое посуда 1", 30.0,"Тарелка одноразовая", other);
        createSaveProduct("Другое посуда 2", 30.0,"Ложка одноразовая", other);
        createSaveProduct("Другое посуда 3", 30.0,"Стакан одноразовый", other);
    }

    /**
     * Создание клиентов
     */
    @Test
    public void createClients() {
        clientRepository.deleteAll();

        createSaveClient(ClientNames.CLIENT1, ClientAddress.ADDRESS1, ClientPhones.PHONE1, 1L);
        createSaveClient(ClientNames.CLIENT2, ClientAddress.ADDRESS2, ClientPhones.PHONE2, 2L);
        createSaveClient(ClientNames.CLIENT3, ClientAddress.ADDRESS3, ClientPhones.PHONE3, 3L);
    }

    /**
     * Создание заказов клиентов
     */
    @Test
    public void createClientOrders() {
        clientOrderRepository.deleteAll();

        createSaveClientOrder(1, 123.5, getClientByName(ClientNames.CLIENT1));
        createSaveClientOrder(2, 234.5, getClientByName(ClientNames.CLIENT2));
        createSaveClientOrder(1, 345.5, getClientByName(ClientNames.CLIENT3));
        createSaveClientOrder(2, 456.5, getClientByName(ClientNames.CLIENT1));
    }

    @Test
    public void createOrderProducts() {
        orderProductRepository.deleteAll();

        createSaveOrderProduct(clientOrderRepository.getById(1L), productRepository.getByName("Классический ролл"), 1L);
        createSaveOrderProduct(clientOrderRepository.getById(2L), productRepository.getByName("Классический ролл2"), 2L);
        createSaveOrderProduct(clientOrderRepository.getById(3L), productRepository.getByName("Классический ролл3"), 3L);
        createSaveOrderProduct(clientOrderRepository.getById(1L), productRepository.getByName("Классический ролл"), 1L);
    }
}
