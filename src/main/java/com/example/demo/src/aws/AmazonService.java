package com.example.demo.src.aws;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class AmazonService {
    @Autowired
    private AmazonS3 amazonS3;

    @Value("${amazon.aws.bucket-name}")
    private String bucketName;

    @Autowired
    private HttpServletRequest request;

    static final List<String> toDeleteUrls = new ArrayList<>();

    public String uploadImageAndGetUrl(MultipartFile photo){
        String uploadsDir = "/uploads/";
        String realPathtoUploads = null;
        if (request != null) {
            realPathtoUploads = request.getServletContext().getRealPath(uploadsDir);
            log.warn("request is null");
        }
        else
            realPathtoUploads = "."+uploadsDir;
        if(! new File(realPathtoUploads).exists())
        {
            new File(realPathtoUploads).mkdir();
        }
        log.info("realPathtoUploads = {}", realPathtoUploads);
        String orgName = photo.getOriginalFilename();
        String filePath = realPathtoUploads + orgName;
        try {
            File uploadFile = new File(filePath);
            photo.transferTo(uploadFile);
            if (!uploadFile.canRead()) {
                throw new Exception("File Failed to read");
            }
            String key = "public/" + String.valueOf(UUID.randomUUID()) + "."+photo.getContentType();
            PutObjectResult putObjectResult = amazonS3.putObject(new PutObjectRequest(bucketName, key, uploadFile));
            String url = amazonS3.getUrl(bucketName, key).toString();
            log.info("Uploaded url is {}", url);
            uploadFile.delete();
            return url;
        }
        catch (Exception e){
            log.error("Image failed Upload {} because {}", photo.getOriginalFilename(), e.getMessage());
            e.printStackTrace();
            return null;
        }

    }

    public void deleteObjectFromAWS3(String url){
        try {
            String keyName = url.substring(url.indexOf("/public/"));
            log.info(keyName);
            amazonS3.deleteObject(new DeleteObjectRequest(bucketName, keyName));
        } catch (AmazonServiceException e) {
            e.printStackTrace();
        } catch (SdkClientException e) {
            e.printStackTrace();
        }
    }

    public List<String> getEveryPhotoFromAWS3(){
        List<S3ObjectSummary> objectSummaries = amazonS3.listObjects(bucketName).getObjectSummaries();
        List<String> urls = new ArrayList<>();
        for(S3ObjectSummary summary : objectSummaries){
            urls.add(summary.getKey());
        }
        return urls;
    }

}