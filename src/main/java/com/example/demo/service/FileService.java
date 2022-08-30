package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.aop.LatencyMonitor;
import com.example.demo.model.File;
import com.example.demo.repository.FileMapper;

@Service
@Transactional
public class FileService {
    
    @Autowired FileMapper fileMapper;

    @LatencyMonitor
    public void uploadFile(String fileName, byte[] fileData){
        fileMapper.upload(fileName, fileData);
    }

    @LatencyMonitor
    public File downloadFile(int id){
        return fileMapper.download(id).orElse(new File());
    }

    public List<File> searchFiles(){
        return fileMapper.fileList();
    }

}
