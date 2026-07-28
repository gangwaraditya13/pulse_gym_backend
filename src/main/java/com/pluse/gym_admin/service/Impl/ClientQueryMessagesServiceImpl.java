package com.pluse.gym_admin.service.Impl;

import com.pluse.gym_admin.Dto.ClientQueryMessageDto.RequestClientQueryMessageDto;
import com.pluse.gym_admin.Dto.ClientQueryMessageDto.ResponseClientQueryMessageDto;
import com.pluse.gym_admin.entity.ClientQueryMessages;
import com.pluse.gym_admin.mapper.ClientQueryMessageToResponseClientQueryMessageDtoMapper;
import com.pluse.gym_admin.repository.ClientQueryMessagesRepository;
import com.pluse.gym_admin.service.ClientQueryMessagesService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class ClientQueryMessagesServiceImpl implements ClientQueryMessagesService {
    @Autowired
    private ClientQueryMessagesRepository clientQueryMessagesRepository;

    @Override
    public ResponseClientQueryMessageDto createClientQueryMessages(RequestClientQueryMessageDto clientQueryMessageDto) {

        try{

            ClientQueryMessages clientQueryMessages = new ClientQueryMessages();
            clientQueryMessages.setFullName(clientQueryMessageDto.getFullName());
            clientQueryMessages.setEmail(clientQueryMessageDto.getEmail());
            clientQueryMessages.setMessage(clientQueryMessageDto.getMessage());
            clientQueryMessages.setCreatedAt(LocalDateTime.now());
            ClientQueryMessages save = clientQueryMessagesRepository.save(clientQueryMessages);

            ResponseClientQueryMessageDto responseClientQueryMessageDto = ClientQueryMessageToResponseClientQueryMessageDtoMapper.responseClientQueryMessageDto(save);

            return responseClientQueryMessageDto;

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<ResponseClientQueryMessageDto> getAllClientQueryMessages() {

        List<ClientQueryMessages> clientQueryMessagesList = clientQueryMessagesRepository.findAll();

        List<ResponseClientQueryMessageDto> responseClientQueryMessageDtoList = clientQueryMessagesList.stream().map(ClientQueryMessageToResponseClientQueryMessageDtoMapper::responseClientQueryMessageDto).toList();

        return responseClientQueryMessageDtoList;
    }

    @Override
    public boolean deleteClientQueryMessages(ObjectId id) {

        try{
            clientQueryMessagesRepository.deleteById(id);
            return true;
        }catch (Exception e) {
            return false;
        }
    }
}
