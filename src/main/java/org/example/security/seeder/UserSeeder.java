package org.example.security.seeder;

import org.example.security.entity.UserEntity;
import org.example.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserSeeder implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        loadUser();
    }

    private void loadUser() {
        if(userRepository.count() == 0){
            loadUserEntity();
        }
    }

    private void loadUserEntity(){
        createUser("test","12345");
    }

    private void createUser(String name, String password) {

        UserEntity user = new UserEntity(
                name,
                passwordEncoder.encode(password)
        );
        userRepository.save(user);
    }
}