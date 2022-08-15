package ru.t1.dedov.service.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.t1.dedov.model.entity.UserAction;
import ru.t1.dedov.model.repository.UserActionRepository;
import ru.t1.dedov.service.interfaces.UserActionService;

@Service
@RequiredArgsConstructor
public class UserActionServiceImpl implements UserActionService {

    private final UserActionRepository userActionRepository;

    @Override
    public void saveLog(UserAction userAction) {
        userActionRepository.insert(userAction);
    }
}
