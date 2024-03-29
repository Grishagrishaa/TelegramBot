package com.example.userservice.service;

import com.example.userservice.dao.api.IUserRepository;
import com.example.userservice.dao.entity.User;
import com.example.userservice.dto.users.UserCreateDto;
import com.example.userservice.dto.users.UserReadAndUpdateDto;
import com.example.userservice.service.api.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@Transactional(readOnly = true)
public class UserService implements IUserService {
    private final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final IUserRepository repository;
    private final ConversionService conversion;

    public UserService(IUserRepository repository, ConversionService conversion) {
        this.repository = repository;
        this.conversion = conversion;
    }

    @Override
    public User getUser(Long userId) {
        logger.info("SERVICE | getUser() invocation, userId - {}", userId);
        try {
            return repository.findById(userId)
                    .orElseThrow(NoSuchElementException::new);
        }catch (NoSuchElementException e){
            logger.error("CANNOT FIND BY ID - {}", e.getMessage());
            throw e;
        }
    }

    @Override
    @Transactional
    public void saveUser(UserCreateDto userDto) {
        logger.info("SERVICE | saveUser() invocation, user - {}", userDto);
        User newUser = conversion.convert(userDto, User.class);
        try{
            repository.save(newUser);
        }catch (IllegalArgumentException  e){
            logger.error("CANNOT saveUser() - {}, USER - {}", e.getMessage(), userDto);
            throw e;
        }
    }

    @Override
    @Transactional
    public void updateUser(UserReadAndUpdateDto dto) {
        User user = getUser(dto.getUserId());
        logger.info("SERVICE | updateUser() invocation, user - {}", user);


        if(dto.getCity() != null)user.setCity(dto.getCity());
        if(dto.getPageSize() != null)user.setPageSize(dto.getPageSize());

        try{
            repository.save(user);
        }catch (IllegalArgumentException  e){
            logger.error("CANNOT saveUser() - {}", e.getMessage());
            throw e;
        }
    }
}
