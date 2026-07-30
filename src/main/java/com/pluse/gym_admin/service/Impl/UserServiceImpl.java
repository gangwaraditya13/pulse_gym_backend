package com.pluse.gym_admin.service.Impl;

import com.pluse.gym_admin.Dto.UserDto.RequestUser;
import com.pluse.gym_admin.Dto.UserDto.ResponseUser;
import com.pluse.gym_admin.entity.User;
import com.pluse.gym_admin.entity.emuns.Roles;
import com.pluse.gym_admin.repository.UserRepository;
import com.pluse.gym_admin.service.Impl.Cloudinary.CloudinaryImpl;
import com.pluse.gym_admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder PASSWORD_ENCODER;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CloudinaryImpl cloudinary;

    @Override
    public ResponseUser createUser(RequestUser requestUser) {

        try {
            User user = new User();

            user.setUserName(requestUser.getUserName());
            user.setAbout(requestUser.getAbout());
            user.setEmail(requestUser.getEmail());
            String password = PASSWORD_ENCODER.encode(requestUser.getPassword());
            user.setPassword(password);
            user.setImageUrl(requestUser.getImageUrl());
            user.setPublicId(requestUser.getPublicId());
            user.setCreatedAt(LocalDateTime.now());
            user.setUpdateAt(LocalDateTime.now());
            user.getRole().add(Roles.USER.toString());

            User saved = userRepository.save(user);

            ResponseUser responseUser = new ResponseUser();

            responseUser.setId(saved.getId());
            responseUser.setUserName(saved.getUserName());
            responseUser.setAbout(saved.getAbout());
            responseUser.setEmail(saved.getEmail());
            responseUser.setImageUrl(saved.getImageUrl());
            responseUser.setPublicId(saved.getPublicId());
            responseUser.setCreatedAt(saved.getCreatedAt());
            responseUser.setUpdateAt(saved.getUpdateAt());
            responseUser.setRole(saved.getRole());

            return responseUser;
        } catch (Exception e) {
            throw new RuntimeException("User not created : "+e.getMessage());
        }
    }

    @Override
    public ResponseUser createAdmin(RequestUser requestUser) {

        try {
            User user = new User();

            user.setUserName(requestUser.getUserName());
            user.setAbout(requestUser.getAbout());
            user.setEmail(requestUser.getEmail());
            String password = PASSWORD_ENCODER.encode(requestUser.getPassword());
            user.setPassword(password);
            user.setImageUrl(requestUser.getImageUrl());
            user.setPublicId(requestUser.getPublicId());
            user.setCreatedAt(LocalDateTime.now());
            user.setUpdateAt(LocalDateTime.now());
            user.getRole().add(Roles.ADMIN.toString());

            User saved = userRepository.save(user);

            ResponseUser responseUser = new ResponseUser();

            responseUser.setId(saved.getId());
            responseUser.setUserName(saved.getUserName());
            responseUser.setAbout(saved.getAbout());
            responseUser.setEmail(saved.getEmail());
            responseUser.setImageUrl(saved.getImageUrl());
            responseUser.setPublicId(saved.getPublicId());
            responseUser.setCreatedAt(saved.getCreatedAt());
            responseUser.setUpdateAt(saved.getUpdateAt());
            responseUser.setRole(saved.getRole());

            return responseUser;
        } catch (Exception e) {
            throw new RuntimeException("User not created : "+e.getMessage());
        }
    }

    @Override
    public ResponseUser getUserByUserName(String userName) {

        User user = userRepository.findByUserName(userName).orElseThrow();

        ResponseUser responseUser = new ResponseUser();

        responseUser.setId(user.getId());
        responseUser.setUserName(user.getUserName());
        responseUser.setAbout(user.getAbout());
        responseUser.setEmail(user.getEmail());
        responseUser.setImageUrl(user.getImageUrl());
        responseUser.setPublicId(user.getPublicId());
        responseUser.setCreatedAt(user.getCreatedAt());
        responseUser.setUpdateAt(user.getUpdateAt());
        responseUser.setRole(user.getRole());

        return responseUser;
    }

    @Override
    public ResponseUser updateUser(RequestUser requestUser) throws IOException {
        User user = userRepository.findByUserName(requestUser.getUserName()).orElseThrow();

        boolean isUpdate = false;

        if( requestUser.getUserName() != null
                && !requestUser.getUserName().trim().equals("")
                && !user.getUserName().trim().equals(requestUser.getUserName())){
            user.setUserName(requestUser.getUserName());
            user.setUpdateAt(LocalDateTime.now());
            isUpdate=true;
        }

        if( requestUser.getEmail() != null
                && !requestUser.getEmail().trim().equals("")
                && !user.getEmail().trim().equals(requestUser.getEmail())){
            user.setEmail(requestUser.getEmail());
            user.setUpdateAt(LocalDateTime.now());
            isUpdate=true;
        }
        if( requestUser.getAbout() != null
                && !requestUser.getAbout().trim().equals("")
                && !user.getAbout().trim().equals(requestUser.getAbout())){
            user.setAbout(requestUser.getAbout());
            user.setUpdateAt(LocalDateTime.now());
            isUpdate=true;
        }
        if( requestUser.getImageUrl() != null
                && !requestUser.getImageUrl().trim().equals("")
                && !user.getImageUrl().trim().equals(requestUser.getImageUrl())){
            user.setImageUrl(requestUser.getImageUrl());
            user.setUpdateAt(LocalDateTime.now());
            isUpdate=true;
        }
        if( requestUser.getPublicId() != null
                && !requestUser.getPublicId().trim().equals("")
                && !user.getPublicId().trim().equals(requestUser.getPublicId())){
            //delete image from cloudinary
            if(requestUser.getPublicId() != null
                    && !requestUser.getPublicId().trim().equals("")) {
                cloudinary.deleteImage(user.getPublicId());
            }
            user.setPublicId(requestUser.getPublicId());
            user.setUpdateAt(LocalDateTime.now());
            isUpdate=true;
        }

        if(isUpdate) {
            User saved = userRepository.save(user);

            ResponseUser responseUser = new ResponseUser();
            responseUser.setId(saved.getId());
            responseUser.setUserName(saved.getUserName());
            responseUser.setAbout(saved.getAbout());
            responseUser.setEmail(saved.getEmail());
            responseUser.setImageUrl(saved.getImageUrl());
            responseUser.setPublicId(saved.getPublicId());
            responseUser.setCreatedAt(saved.getCreatedAt());
            responseUser.setUpdateAt(saved.getUpdateAt());
            responseUser.setRole(saved.getRole());

            return responseUser;
        }
        return null;
    }

    @Override
    public ResponseUser getAdmin() {

        User user = userRepository.findByrole("ADMIN").orElse(null);

        ResponseUser responseUser = new ResponseUser();
        responseUser.setId(user.getId());
        responseUser.setUserName(user.getUserName());
        responseUser.setAbout(user.getAbout());
        responseUser.setEmail(user.getEmail());
        responseUser.setImageUrl(user.getImageUrl());
        responseUser.setPublicId(user.getPublicId());
        responseUser.setCreatedAt(user.getCreatedAt());
        responseUser.setUpdateAt(user.getUpdateAt());
        responseUser.setRole(user.getRole());

        return responseUser;
    }
}
