package ru.atroshenko.telegrambot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.atroshenko.telegrambot.entities.ClientOrder;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "clientOrders", path = "clientOrders")
public interface ClientOrderRepository extends JpaRepository<ClientOrder, Long> {
    List<ClientOrder> getClientOrderByStatus(Integer status);
    List<ClientOrder> getClientOrderByClientFullName(String clientName);
}
