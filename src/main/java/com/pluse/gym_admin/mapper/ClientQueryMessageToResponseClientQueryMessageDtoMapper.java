package com.pluse.gym_admin.mapper;

import com.pluse.gym_admin.Dto.ClientQueryMessageDto.ResponseClientQueryMessageDto;
import com.pluse.gym_admin.entity.ClientQueryMessages;

public class ClientQueryMessageToResponseClientQueryMessageDtoMapper {

    public static ResponseClientQueryMessageDto responseClientQueryMessageDto(ClientQueryMessages clientQueryMessages){
        ResponseClientQueryMessageDto responseClientQueryMessageDto = new ResponseClientQueryMessageDto();
        responseClientQueryMessageDto.setId(clientQueryMessages.getId());
        responseClientQueryMessageDto.setFullName(clientQueryMessages.getFullName());
        responseClientQueryMessageDto.setEmail(clientQueryMessages.getEmail());
        responseClientQueryMessageDto.setMessage(clientQueryMessages.getMessage());
        return responseClientQueryMessageDto;
    }
}
