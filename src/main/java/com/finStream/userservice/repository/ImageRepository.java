package com.finStream.userservice.repository;

import com.finStream.userservice.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Integer> {

    List<Image> findByOrderById();
}

