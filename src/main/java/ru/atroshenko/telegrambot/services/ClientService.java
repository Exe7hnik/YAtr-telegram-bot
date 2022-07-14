package ru.atroshenko.telegrambot.services;

import org.springframework.stereotype.Service;
import ru.atroshenko.telegrambot.entities.Client;
import ru.atroshenko.telegrambot.repositories.ClientRepository;

import javax.transaction.Transactional;

@Service
@Transactional
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client getClientByFullName(String name) {
        return clientRepository.getClientByFullName(name);
    }
}
