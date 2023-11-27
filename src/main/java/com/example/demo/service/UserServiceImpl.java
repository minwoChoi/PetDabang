package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.UserBoard;
import com.example.demo.exception.DuplicateIdOrUsernameException;
import com.example.demo.repository.UserRepository;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void write(UserBoard userBoard) throws DuplicateIdOrUsernameException {
        if (isIdOrUsernameDuplicate(userBoard)) {
            throw new DuplicateIdOrUsernameException("ID 또는 Username이 이미 존재합니다.");
        }
    
        // 기본 이미지를 BLOB로 설정
        userBoard.setImage(generateDefaultImageBlob());
        
        userRepository.save(userBoard);
    }
    private byte[] generateDefaultImageBlob() {
        try {
            // 프로젝트 내의 resources 디렉토리에 있는 이미지 파일을 읽어옴
            String imagePath = "src/main/resources/static/images/profile_button1.png";
            Path path = Paths.get(imagePath);
    
            // 파일을 바이트 배열로 읽어오기
            return Files.readAllBytes(path);
        } catch (Exception e) {
            // 예외 처리: 파일을 읽어오는 도중에 오류가 발생할 경우
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public boolean authenticateUser(int ID, String PW) {
        Optional<UserBoard> userOptional = userRepository.findById(ID);
        UserBoard user = userOptional.orElse(null);

        return user != null && user.getPW().equals(PW);
    }

    @Override
    public String getUsernameById(int id) {
        return userRepository.findById(id)
                .map(UserBoard::getUSERNAME)
                .orElse(null);
    }

    private boolean isIdOrUsernameDuplicate(UserBoard userBoard) {
        UserBoard existingUserById = userRepository.findById(userBoard.getID()).orElse(null);
        Optional<UserBoard> existingUserByUsernameOptional = userRepository.findByUSERNAME(userBoard.getUSERNAME());
        UserBoard existingUserByUsername = existingUserByUsernameOptional.orElse(null);

        return existingUserById != null || existingUserByUsername != null;
    }
}
