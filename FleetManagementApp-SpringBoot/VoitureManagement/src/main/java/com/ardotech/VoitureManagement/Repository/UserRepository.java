package com.ardotech.VoitureManagement.Repository;

import com.ardotech.VoitureManagement.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ardotech.VoitureManagement.dto.UserDto;
import org.springframework.web.multipart.MultipartFile;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);

}
