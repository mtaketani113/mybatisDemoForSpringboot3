package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.demo.model.File;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Sql("/init.sql")
@Transactional
public class FileServiceTest {

  @Autowired private FileService fileService;

  @Test
  public void ファイルの追加テスト() {

    fileService.uploadFile("testFileName", new byte[0]);
    List<File> files = fileService.searchFiles();

    assertEquals(1, files.size());
    assertEquals("testFileName", files.get(0).getFileName());
    assertEquals(0, files.get(0).getFileData().length);

  }

  @Test
  public void ファイルの削除テスト() {

    fileService.uploadFile("testFileName", new byte[0]);
    List<File> files = fileService.searchFiles();

    assertEquals(1, files.size());

    fileService.deleteFile(files.get(0).getId());
    
    // 0件になっていることを確認
    files = fileService.searchFiles();
    assertEquals(0, files.size());

  }

}
