package com.myclass.repository.impl;

import org.springframework.stereotype.Repository;

import com.myclass.entity.Video;
import com.myclass.repository.VideoRepository;
@Repository
public class VideoRepositoryImpl extends GenericRepositoryImpl<Video, Integer> implements VideoRepository{

}
