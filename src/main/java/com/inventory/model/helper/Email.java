package com.inventory.model.helper;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Data
public class Email {
    private  String from;
    private  String []to;
    //header-->subject
    private  String header;
    private  String message;
    //attached files
    private MultipartFile file;
//    private List<File> fileList;

}

