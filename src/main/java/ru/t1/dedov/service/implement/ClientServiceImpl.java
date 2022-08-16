package ru.t1.dedov.service.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.t1.dedov.dto.ClientDto;
import ru.t1.dedov.exceptions.InvalidCapacityException;
import ru.t1.dedov.exceptions.InvalidTypeException;
import ru.t1.dedov.mapper.ClientMapper;
import ru.t1.dedov.model.entity.Card;
import ru.t1.dedov.model.entity.Client;
import ru.t1.dedov.model.entity.Schedule;
import ru.t1.dedov.model.entity.enums.Role;
import ru.t1.dedov.model.repository.CardRepository;
import ru.t1.dedov.model.repository.ClientRepository;
import ru.t1.dedov.model.repository.ScheduleRepository;
import ru.t1.dedov.service.interfaces.ClientService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final ScheduleRepository scheduleRepository;
    private final CardRepository cardRepository;
    private final ClientMapper clientMapper;

    @Override
    public List<ClientDto> findAll() {
        return clientRepository.findAll()
                .stream()
                .map(clientMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ClientDto findById(Long id) {
        return clientMapper.toDto(clientRepository.findById(id).get());
    }

    @Override
    public ClientDto save(ClientDto clientDto) {
        clientRepository.save(clientMapper.toEntity(clientDto));
        return clientDto;
    }

    @Override
    public void deleteById(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public void editById(Long id, ClientDto clientDto) {
        Client client = clientRepository.getReferenceById(id);
        client.setFirstName(clientDto.getFirstName());
        client.setLastName(clientDto.getLastName());
        client.setPatronymic(clientDto.getPatronymic());
        client.setPassport(clientDto.getPassport());
        client.setDateOfBirth(clientDto.getDateOfBirth());
        client.setHomeAddress(clientDto.getHomeAddress());
        client.setGender(clientDto.getGender());
        client.setPhoneNumber(clientDto.getPhoneNumber());
        clientRepository.save(client);
    }

    @Override
    @Transactional
    public void addCardToClient(Long cardId, Long clientId) {
        Client client = clientRepository.getReferenceById(clientId);
        client.setRole(Role.USER);
        clientRepository.save(client);
        Card card = cardRepository.getReferenceById(cardId);
        card.setClient(client);
        cardRepository.save(card);
    }

    @Override
    public void signClientOnSchedule(Long clientId, Long scheduleId) throws InvalidTypeException, InvalidCapacityException {
        Client client = clientRepository.getReferenceById(clientId);
        Schedule schedule = scheduleRepository.getReferenceById(scheduleId);
        if(client.getCardSet().stream()
                        .noneMatch(x -> x.getTrainingTypes()
                        .contains(schedule.getEmployeeTrainingType().getTrainingType()))){
            throw new InvalidTypeException("no approachable training type in your card for this training");
        }
        Set<Client> clientSet = schedule.getClientSet();
        if (schedule.getPeopleCapacity().equals(clientSet.size())){
            throw new InvalidCapacityException("this training is full of clients");
        }
        clientSet.add(client);
        scheduleRepository.save(schedule);
    }
}
