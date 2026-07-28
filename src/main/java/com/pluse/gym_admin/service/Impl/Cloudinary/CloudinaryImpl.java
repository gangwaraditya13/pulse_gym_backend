package com.pluse.gym_admin.service.Impl.Cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
@Service
public class CloudinaryImpl {

    @Autowired
    private Cloudinary cloudinary;

    public Map<String,String> uploadImage(MultipartFile multipartFile) throws IOException {

        Map<?,?> result = cloudinary
                .uploader()
                .upload(multipartFile.getBytes(), ObjectUtils.emptyMap());

        Map<String, String> response = new HashMap<>();

        response.put("ImageUrl",result.get("secure_url").toString());
        response.put("publicId",result.get("public_id").toString());

        return response;
    }

    public void deleteImage(String publicId) throws IOException {

        Map<?,?> result = cloudinary
                .uploader()
                .destroy(publicId,ObjectUtils.emptyMap());

        System.out.println("image is deleted : " + result);

    }

}
