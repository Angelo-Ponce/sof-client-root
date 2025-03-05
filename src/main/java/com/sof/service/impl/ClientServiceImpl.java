package com.sof.service.impl;

import com.sof.exception.ModelNotFoundException;
import com.sof.model.Client;
import com.sof.repository.IClientRepository;
import com.sof.repository.IGenericRepository;
import com.sof.service.IClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl extends CRUDServiceImpl<Client, Long> implements IClientService {

    private final IClientRepository repository;

    @Override
    protected IGenericRepository<Client, Long> getRepository() {
        return repository;
    }

    @Override
    public Client save(Client client, String user) {
        client.setCreatedByUser(user);
        client.setCreatedDate(LocalDateTime.now());
        return repository.save(client);
    }

    @Override
    public Client update(Long id, Client client, String user) {
        Client clientEntity = repository.findById(id).orElseThrow(() -> new ModelNotFoundException("ID not found: " + id));
        clientEntity.setIdentification(client.getIdentification());
        clientEntity.setName(client.getName());
        clientEntity.setGender(client.getGender());
        clientEntity.setAge(client.getAge());
        clientEntity.setAddress(client.getAddress());
        clientEntity.setPhone(client.getPhone());
        clientEntity.setClientId(client.getClientId());
        clientEntity.setPassword(client.getPassword());
        clientEntity.setStatus(client.getStatus());
        clientEntity.setLastModifiedByUser(user);
        clientEntity.setLastModifiedDate(LocalDateTime.now());
        return repository.save(clientEntity);
    }

    @Override
    public void deleteLogic(Long id, String user) {
        Client client = repository.findById(id).orElseThrow(() -> new ModelNotFoundException("ID not found: " + id));
        client.setStatus(Boolean.FALSE);
        client.setLastModifiedDate(LocalDateTime.now());
        client.setLastModifiedByUser(user);
        repository.save(client);
    }
}
