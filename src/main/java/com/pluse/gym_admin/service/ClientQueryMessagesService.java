package com.pluse.gym_admin.service;

import com.pluse.gym_admin.Dto.ClientQueryMessageDto.RequestClientQueryMessageDto;
import com.pluse.gym_admin.Dto.ClientQueryMessageDto.ResponseClientQueryMessageDto;
import org.bson.types.ObjectId;

import java.util.List;

public interface ClientQueryMessagesService {

    ResponseClientQueryMessageDto createClientQueryMessages(RequestClientQueryMessageDto clientQueryMessageDto);

    List<ResponseClientQueryMessageDto> getAllClientQueryMessages();

    boolean deleteClientQueryMessages(ObjectId id);

}
