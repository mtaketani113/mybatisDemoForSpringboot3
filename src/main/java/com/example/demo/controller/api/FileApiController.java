package com.example.demo.controller.api;

import com.example.demo.annotation.Authorize;
import com.example.demo.annotation.NonAuthorize;
import com.example.demo.model.File;
import com.example.demo.service.FileService;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileApiController {

  @Autowired FileService fileService;

  @PostMapping("/api/file")
  @Authorize
  public String fileDownload(@RequestParam("fileDatas") MultipartFile multipartFile) {

    try {
      fileService.uploadFile(multipartFile.getOriginalFilename(), multipartFile.getBytes());
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return "{\"result\": \"OK\"}";
  }

  @GetMapping(value = "/api/file/{id:^[0-9]+$}")
  public void fileDownload(HttpServletResponse response, @PathVariable("id") String id) {

    try (OutputStream os = response.getOutputStream()) {

      File file = fileService.downloadFile(Integer.parseInt(id));

      byte[] fileByteArray = Optional.ofNullable(file.getFileData()).orElse(new byte[0]);

      response.setContentType("application/octet-stream");
      response.setHeader(
          "Content-Disposition",
          "attachment; filename=" + URLEncoder.encode(file.getFileName(), "UTF-8"));
      response.setContentLength(fileByteArray.length);

      os.write(fileByteArray);
      os.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @GetMapping(value = "/api/file")
  @NonAuthorize
  public List<File> fileList() {
    return fileService.searchFiles();
  }

  @DeleteMapping("/api/file/delete/{id:^[0-9]+$}")
  @Authorize
  public String delete(@PathVariable("id") String id) {
    fileService.deleteFile(Integer.parseInt(id));
    return "{\"result\": \"OK\"}";
  }
}
