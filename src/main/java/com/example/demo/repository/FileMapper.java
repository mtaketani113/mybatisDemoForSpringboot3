package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.demo.model.File;

@Mapper
public interface FileMapper {

    @Insert("INSERT INTO file_manager (file_name, file_data) VALUES (#{fileName}, #{fileData})")
    void upload(@Param("fileName")String fileName, @Param("fileData")byte[] fileData);

    @Select("SELECT file_name, file_data FROM file_manager where id = #{id}")
    Optional<File> download(int id);

    @Select("SELECT id, file_name, file_data FROM file_manager ORDER BY id")
    List<File> fileList();

    @Delete("DELETE FROM file_manager WHERE id = #{id}")
    void delete(int id);

}