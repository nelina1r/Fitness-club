package ru.t1.dedov.service.interfaces;

import ru.t1.dedov.dto.ClientDto;
import ru.t1.dedov.exceptions.InvalidTypeException;

import java.util.List;

public interface ClientService {
    List<ClientDto> findAll();

    ClientDto findById(Long id);

    ClientDto save(ClientDto clientDto);

    void deleteById(Long id);

    void addCardToClient(Long cardId, Long clientId);

    void signClientOnSchedule(Long clientId, Long scheduleId) throws InvalidTypeException;
}
