package com.example.demo.service;

import com.example.demo.aop.LatencyMonitor;
import com.example.demo.common.Crypt;
import com.example.demo.model.File;
import com.example.demo.repository.FileMapper;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FileService {

  @Autowired FileMapper fileMapper;

  @LatencyMonitor
  public void uploadFile(String fileName, byte[] fileData) {
    fileMapper.upload(fileName, fileData);
  }

  @LatencyMonitor
  public File downloadFile(String encryptId) {
    int id = Integer.parseInt(Crypt.decrypt(encryptId));
    return fileMapper.download(id).orElse(new File());
  }

  public List<File> searchFiles() {
    List<File> fileList = fileMapper.fileList();
    // IDを暗号化
    fileList.forEach(s -> {
      try {
        String encodedResult = URLEncoder.encode(Crypt.encrypt(s.getId()), "UTF-8");
        s.setId(encodedResult);
      } catch (UnsupportedEncodingException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    });
    return fileMapper.fileList();
  }

  public void deleteFile(String encryptId) {
    int id = Integer.parseInt(Crypt.decrypt(encryptId));
    fileMapper.delete(id);
  }
}
