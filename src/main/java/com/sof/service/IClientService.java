package com.sof.service;

import com.sof.model.Client;

public interface IClientService extends ICRUDService<Client, Long> {

    Client save(Client client, String user);
    Client update(Long id, Client client, String user);
    void deleteLogic(Long id, String user);
}
