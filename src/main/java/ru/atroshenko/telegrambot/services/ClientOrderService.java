package ru.atroshenko.telegrambot.services;

import org.springframework.stereotype.Service;
import ru.atroshenko.telegrambot.entities.ClientOrder;
import ru.atroshenko.telegrambot.repositories.ClientOrderRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ClientOrderService {

    private final ClientOrderRepository clientOrderRepository;

    public ClientOrderService(ClientOrderRepository clientOrderRepository) {
        this.clientOrderRepository = clientOrderRepository;
    }

    public List<ClientOrder> getOrdersByStatus(Integer status) {
        return clientOrderRepository.getClientOrderByStatus(status);
    }

    public List<ClientOrder> getClientOrderByClientFullName(String clientName) {
        return clientOrderRepository.getClientOrderByClientFullName(clientName);
    }
}
