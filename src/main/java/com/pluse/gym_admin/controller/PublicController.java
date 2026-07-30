package com.pluse.gym_admin.controller;

import com.pluse.gym_admin.Dto.ClientQueryMessageDto.RequestClientQueryMessageDto;
import com.pluse.gym_admin.Dto.ClientQueryMessageDto.ResponseClientQueryMessageDto;
import com.pluse.gym_admin.Dto.HallOfFameDto.ResponseHallOfFameDto;
import com.pluse.gym_admin.Dto.MemberShipDto.ResponseMembershipDto;
import com.pluse.gym_admin.Dto.SpecializedTrainingDto.ResponseSpecializedTrainingDto;
import com.pluse.gym_admin.Dto.UserDto.ResponseUser;
import com.pluse.gym_admin.entity.GymInfo;
import com.pluse.gym_admin.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private HallOfFameService hallOfFameService;

    @Autowired
    private ClientQueryMessagesService clientQueryMessagesService;

    @Autowired
    private SpecializedTrainingService specializedTrainingService;

    @Autowired
    private MembershipService membershipService;

    @Autowired
    private UserService userService;

    @Autowired
    private GymInfoService gymInfoService;


    @GetMapping("/Admin")
    public ResponseEntity<ResponseUser> getAdmin(){

        ResponseUser user = userService.getAdmin();

        if(user != null){
            return new ResponseEntity<>(user, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/gym-info")
    public ResponseEntity<List<GymInfo>> getGymInfo(){

        List<GymInfo> gymInfo = gymInfoService.getInfo();

        if(gymInfo != null){
            return new ResponseEntity<>(gymInfo, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/hall-of-fame")
    public ResponseEntity<List<ResponseHallOfFameDto>> getAllHallOfFame(){
        List<ResponseHallOfFameDto> allHallOfFame = hallOfFameService.getAllHallOfFame();
        if(allHallOfFame != null){
            return new ResponseEntity<>(allHallOfFame, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/specialized-training")
    public ResponseEntity<List<ResponseSpecializedTrainingDto>> getAllSpecializedTraining(){
        List<ResponseSpecializedTrainingDto> allSpecializedTraining = specializedTrainingService.getAllSpecializedTraining();
        if(allSpecializedTraining != null){
            return new ResponseEntity<>(allSpecializedTraining, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/membership")
    public ResponseEntity<List<ResponseMembershipDto>> getAllMembership(){
        List<ResponseMembershipDto> allMembership = membershipService.getAllMembership();
        if(allMembership!= null){
            return new ResponseEntity<>(allMembership, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create/client-message")
    public ResponseEntity<ResponseClientQueryMessageDto> createClientMessage(@RequestBody RequestClientQueryMessageDto clientQueryMessageDto){
        ResponseClientQueryMessageDto clientQueryMessages = clientQueryMessagesService.createClientQueryMessages(clientQueryMessageDto);
        if(clientQueryMessages != null){
            return new ResponseEntity<>(clientQueryMessages,HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
