package ru.t1.dedov.service.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.t1.dedov.dto.ClientDto;
import ru.t1.dedov.mapper.ClientMapper;
import ru.t1.dedov.model.repository.ClientRepository;
import ru.t1.dedov.service.interfaces.ClientService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
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
}
