package ru.atroshenko.telegrambot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.atroshenko.telegrambot.entities.Product;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "products", path = "products")
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product getByName(String name);
    List<Product> getProductByCategoryId(Long id);

    String ALL_PRODUCT_BY_CLIENT = "from Product where id in (select product.id from OrderProduct where clientOrder.client.id = :clientId)";
    @Query(ALL_PRODUCT_BY_CLIENT)
    List<Product> getProductsById(@Param("clientId") Long clientId);

    String TOP_PRODUCTS = "select o.product from OrderProduct as o order by o.countProduct DESC";
    @Query(TOP_PRODUCTS)
    List<Product> getTopPopularProducts(@Param("top") Integer top);

}
