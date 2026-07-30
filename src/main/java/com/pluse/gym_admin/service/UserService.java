package com.pluse.gym_admin.service;

import com.pluse.gym_admin.Dto.UserDto.RequestUser;
import com.pluse.gym_admin.Dto.UserDto.ResponseUser;

import java.io.IOException;

public interface UserService {

    ResponseUser createUser(RequestUser requestUser);

    public ResponseUser createAdmin(RequestUser requestUser);

    ResponseUser getUserByUserName(String userName);
    ResponseUser getAdmin();

    ResponseUser updateUser(RequestUser requestUser) throws IOException;

}
