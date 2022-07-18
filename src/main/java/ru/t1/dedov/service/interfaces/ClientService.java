package ru.t1.dedov.service.interfaces;

import ru.t1.dedov.dto.ClientDto;

import java.util.List;

public interface ClientService {
    List<ClientDto> findAll();

    ClientDto findById(Long id);

    ClientDto save(ClientDto clientDto);

    void deleteById(Long id);
}
