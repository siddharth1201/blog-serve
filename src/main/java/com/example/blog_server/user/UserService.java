package com.example.blog_server.user;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.blog_server.user.dtos.CreateUserRequest;

@Service
public class UserService {

    private final UserRepository usersRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository usersRepository, ModelMapper modelMapper,PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public UserEntity createUser(CreateUserRequest u){
        UserEntity newUser = modelMapper.map(u ,UserEntity.class);
        newUser.setPassword(passwordEncoder.encode(u.getPassword()));//todo password encoder
        return usersRepository.save(newUser);
    }

    public UserEntity getUser(String username){
        return usersRepository.findByUsername(username).orElseThrow(()-> new UserNotFoundException(username));
    }

    public UserEntity getUser(Long userId) {
        return usersRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }

    public UserEntity loginUser(String username, String password){
        var user = usersRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
        var passMatch = passwordEncoder.matches(password, user.getPassword());
        if(!passMatch) throw new InvalidCredentialsException();
        return user;
    }



    public static class UserNotFoundException extends IllegalArgumentException {
        public UserNotFoundException(String username) {
            super("User with username: " + username + " not found");
        }

        public UserNotFoundException(Long userId) {
            super("User with id: " + userId + " not found");
        }
    }

    public static class InvalidCredentialsException extends IllegalArgumentException {
        public InvalidCredentialsException() {
            super("Invalid username or password combination");
        }
    }
}
