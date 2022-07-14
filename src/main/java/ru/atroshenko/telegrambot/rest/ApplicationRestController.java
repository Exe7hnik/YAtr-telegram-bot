package ru.atroshenko.telegrambot.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.atroshenko.telegrambot.entities.Client;
import ru.atroshenko.telegrambot.entities.ClientOrder;
import ru.atroshenko.telegrambot.entities.Product;
import ru.atroshenko.telegrambot.services.ClientOrderService;
import ru.atroshenko.telegrambot.services.ClientService;
import ru.atroshenko.telegrambot.services.ProductService;

import java.util.List;

@RestController
public class ApplicationRestController {

    private final ClientService clientService;
    private final ClientOrderService clientOrderService;
    private final ProductService productService;

    public ApplicationRestController(ClientService clientService, ClientOrderService clientOrderService,
                                     ProductService productService) {
        this.clientService = clientService;
        this.clientOrderService = clientOrderService;
        this.productService = productService;
    }

    @GetMapping(value = "/rest/clients", params = "name")
    public Client getClientByFullName(@RequestParam String name) {
        return clientService.getClientByFullName(name);
    }

    @GetMapping(value = "/rest/orders", params = "status")
    public List<ClientOrder> getOrdersByStatus(@RequestParam Integer status) {
        return clientOrderService.getOrdersByStatus(status);
    }

    @GetMapping(value = "/rest/products", params = "name")
    public Product getProductsByName(@RequestParam String name) {
        return productService.getProductByName(name);
    }

    @GetMapping(value = "/rest/products", params = "categoryId")
    public List<Product> getProductsByCategory(@RequestParam Long categoryId) {
        return productService.getProductsByCategoryId(categoryId);
    }

    @GetMapping(value = "/rest/listClientOrders", params = "clientName")
    public List<ClientOrder> getClientOrdersByName(@RequestParam String clientName) {
        return clientOrderService.getClientOrderByClientFullName(clientName);
    }

    @GetMapping(value = "/rest/listClientProducts", params = "clientId")
    public List<Product> getProductsByClientId(@RequestParam Long clientId) {
        return productService.getProductsByClientId(clientId);
    }

    @GetMapping(value = "/rest/topPopularProducts", params = "top")
    public List<Product> getTopPopularProducts(@RequestParam Integer top) {
        return productService.getTopPopularProducts(top);
    }

}
