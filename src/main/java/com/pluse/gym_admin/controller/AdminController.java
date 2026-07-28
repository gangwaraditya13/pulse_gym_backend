package com.pluse.gym_admin.controller;

import com.pluse.gym_admin.Dto.ClientQueryMessageDto.ResponseClientQueryMessageDto;
import com.pluse.gym_admin.Dto.HallOfFameDto.RequestHallOfFameDto;
import com.pluse.gym_admin.Dto.HallOfFameDto.ResponseHallOfFameDto;
import com.pluse.gym_admin.Dto.MemberShipDto.RequestMembershipDto;
import com.pluse.gym_admin.Dto.MemberShipDto.ResponseMembershipDto;
import com.pluse.gym_admin.Dto.SpecializedTrainingDto.RequestSpecializedTrainingDto;
import com.pluse.gym_admin.Dto.SpecializedTrainingDto.ResponseSpecializedTrainingDto;
import com.pluse.gym_admin.Dto.UserDto.RequestUser;
import com.pluse.gym_admin.Dto.UserDto.ResponseUser;
import com.pluse.gym_admin.service.*;
import com.pluse.gym_admin.service.Impl.Cloudinary.CloudinaryImpl;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private HallOfFameService hallOfFameService;

    @Autowired
    private ClientQueryMessagesService clientQueryMessagesService;

    @Autowired
    private SpecializedTrainingService specializedTrainingService;

    @Autowired
    private MembershipService membershipService;

    @Autowired
    private CloudinaryImpl cloudinary;

    /// upload Image and get url and publicId, call before updateAdmin if image want to update, call before createHallOfFame
    @PostMapping("/upload-image")
    public ResponseEntity<Map<String, String>> uploadImage(MultipartFile multipartFile)throws IOException{
        Map<String, String> stringStringMap = cloudinary.uploadImage(multipartFile);

        return new ResponseEntity<>(stringStringMap,HttpStatus.OK);
    }

    /// manage Admin
    @PostMapping("/create/admin")
    public ResponseEntity<ResponseUser> createAdmin(@RequestBody RequestUser requestUser){
        ResponseUser user = userService.createAdmin(requestUser);

        if(user != null){
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{userName}")
    public ResponseEntity<ResponseUser> getAdmin(@PathVariable("userName") String userName){

        //todo: get userName from security context

        ResponseUser user = userService.getUserByUserName(userName);

        if(user != null){
            return new ResponseEntity<>(user, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/admin")
    public ResponseEntity<ResponseUser> updateAdmin(@RequestBody RequestUser requestUser)throws IOException {
        ResponseUser user = userService.updateUser(requestUser);

        if(user != null){
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /// manage Hall Of Fame

    @PostMapping("/create/hall-of-fame")
    public ResponseEntity<ResponseHallOfFameDto> createHallOfFame(@RequestBody RequestHallOfFameDto hallOfFameDto){

        ResponseHallOfFameDto hallOfFame = hallOfFameService.createHallOfFame(hallOfFameDto);

        if(hallOfFame != null){
            return new ResponseEntity<>(hallOfFame, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/hall-of-fame/{hallOfFameId}")
    public ResponseEntity<ResponseHallOfFameDto> updateHallOfFame(@PathVariable("hallOfFameId") ObjectId hallOfFameId, @RequestBody RequestHallOfFameDto hallOfFameDto)throws IOException {

        ResponseHallOfFameDto hallOfFame = hallOfFameService.updateHallOfFame(hallOfFameDto, hallOfFameId);

        if(hallOfFame != null){
            return new ResponseEntity<>(hallOfFame, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/hall-of-fame/{hallOfFameId}")
    public ResponseEntity<?> updateHallOfFame(@PathVariable("hallOfFameId") ObjectId hallOfFameId){

        boolean deleteHallOfFame = hallOfFameService.deleteHallOfFame(hallOfFameId);

        if(deleteHallOfFame){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /// Client Query Message

    @GetMapping("/client-messages")
    public ResponseEntity<List<ResponseClientQueryMessageDto>> getAllClientMessage(){
        List<ResponseClientQueryMessageDto> allClientQueryMessages = clientQueryMessagesService.getAllClientQueryMessages();
        if(allClientQueryMessages != null){
            return new ResponseEntity<>(allClientQueryMessages,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/client-message/{clientMessageId}")
    public ResponseEntity<?> deleteClientMessage(@PathVariable("clientMessageId") ObjectId clientMessageId){
        boolean deleteClientQueryMessages = clientQueryMessagesService.deleteClientQueryMessages(clientMessageId);
        if(deleteClientQueryMessages){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /// Specialized Training
    @PostMapping("/create/specialized-training")
    public ResponseEntity<ResponseSpecializedTrainingDto> createSpecializedTraining(@RequestBody RequestSpecializedTrainingDto requestSpecializedTrainingDto){
        ResponseSpecializedTrainingDto specializedTraining = specializedTrainingService.createSpecializedTraining(requestSpecializedTrainingDto);

        if(specializedTraining != null){
            return new ResponseEntity<>(specializedTraining, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/update/specialized-training/{specializedTrainingId}")
    public ResponseEntity<ResponseSpecializedTrainingDto> updateSpecializedTraining(@PathVariable("specializedTrainingId") ObjectId specializedTrainingId, @RequestBody RequestSpecializedTrainingDto requestSpecializedTrainingDto){
        ResponseSpecializedTrainingDto specializedTraining = specializedTrainingService.updateSpecializedTraining(requestSpecializedTrainingDto, specializedTrainingId);

        if(specializedTraining != null){
            return new ResponseEntity<>(specializedTraining, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/specialized-training/{specializedTrainingId}")
    public ResponseEntity<?> deleteSpecializedTraining(@PathVariable("specializedTrainingId") ObjectId specializedTrainingId){
        boolean deleteSpecializedTraining = specializedTrainingService.deleteSpecializedTraining(specializedTrainingId);

        if(deleteSpecializedTraining){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /// Membership
    @PostMapping("create/membership")
    public ResponseEntity<ResponseMembershipDto> createMembership(@RequestBody RequestMembershipDto requestMembershipDto){
        ResponseMembershipDto membership = membershipService.createMembership(requestMembershipDto);
        if(membership != null){
            return new ResponseEntity<>(membership, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("update/membership/{membershipId}")
    public ResponseEntity<ResponseMembershipDto> updateMembership(@PathVariable("membershipId") ObjectId membershipId, @RequestBody RequestMembershipDto requestMembershipDto){
        ResponseMembershipDto membership = membershipService.updateMembership(requestMembershipDto,membershipId);
        if(membership != null){
            return new ResponseEntity<>(membership, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("delete/membership/{membershipId}")
    public ResponseEntity<ResponseMembershipDto> deleteMembership(@PathVariable("membershipId") ObjectId membershipId){
        boolean deletemembership = membershipService.deleteMembershipById(membershipId);
        if(deletemembership){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
