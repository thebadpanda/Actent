package com.softserve.actent.verification.service;

import com.softserve.actent.model.entity.Status;
import com.softserve.actent.model.entity.User;
import com.softserve.actent.repository.UserRepository;
import com.softserve.actent.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@Slf4j
@Service
public class Verification {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    public String createUuidWithSalt(){
        MessageDigest salt = null;

        try {
            salt = MessageDigest.getInstance("SHA-256");
            salt.update(UUID.randomUUID().toString().getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            log.error("No such algorithm exception.");
        } catch (UnsupportedEncodingException e) {
            log.error("Unsupported encoding exception.");
        }
        String digest = bytesToHex(salt.digest());
        return digest;
    }

    private static String bytesToHex(byte[] hashInBytes) {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < hashInBytes.length; i++) {
            sb.append(Integer.toString((hashInBytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

    public boolean confirmUser(String login, String uuid){

        if(userRepository.existsByLogin(login)){
            User user = userService.getUserByLogin(login);
            if(user.getUuid() != null && user.getUuid().equals(uuid)){
                user.setUuid(null);
                user.setStatus(Status.ACTIVE);
                userService.registrationUpdate(user, user.getId());
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }
    }

}
