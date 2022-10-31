package ru.t1.dedov.service.interfaces;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import ru.t1.dedov.dto.ClientDto;
import ru.t1.dedov.exceptions.CardAlreadyAttachedException;
import ru.t1.dedov.exceptions.InvalidCapacityException;
import ru.t1.dedov.exceptions.InvalidTypeException;
import ru.t1.dedov.model.entity.Client;

import java.util.List;

public interface ClientService {
    List<ClientDto> findAll(String search, Pageable page);

    ClientDto findById(Long id);

    ClientDto save(ClientDto clientDto);

    void deleteById(Long id);

    void editById(Long id, ClientDto clientDto);

    void addCardToClient(Long cardId, Long clientId) throws CardAlreadyAttachedException;

    void signClientOnSchedule(Long clientId, Long scheduleId) throws InvalidTypeException, InvalidCapacityException;
}
