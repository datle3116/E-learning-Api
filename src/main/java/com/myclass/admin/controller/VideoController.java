package com.myclass.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myclass.dto.VideoDTO;
import com.myclass.service.VideoService;

@RestController
@RequestMapping(value = "api/video")
public class VideoController {
	@Autowired
	private VideoService videoService;
	@GetMapping(value = "")
	public Object get() {
		List<VideoDTO> dtos = videoService.findAll();
		if(dtos.isEmpty()) {
			return new ResponseEntity<List<VideoDTO>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<VideoDTO>>(dtos, HttpStatus.OK);
	}
	@GetMapping(value = "/{id}")
	public Object get(@PathVariable("id") Integer id) {
		VideoDTO dto = videoService.findById(id);
		if(dto == null) {
			return new ResponseEntity<VideoDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<VideoDTO>(dto, HttpStatus.OK);
	}
	@PostMapping(value = "")
	public Object post(@RequestBody VideoDTO dto) {
		try {
			if(videoService.insert(dto) == false) {
				return new ResponseEntity<String>("Thêm mới thất bại!", HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<String>("Thêm mới thành công!", HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Thêm mới thất bại!", HttpStatus.BAD_REQUEST);
		}
	}
	@PutMapping(value = "")
	public Object put(@RequestBody VideoDTO dto) {
		try {
			if(videoService.findById(dto.getId()) == null || videoService.update(dto) == false) {
				return new ResponseEntity<String>("Cập nhật thất bại!", HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<String>("Cập nhật thành công!", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Cập nhật thất bại!", HttpStatus.BAD_REQUEST);
		}
	}
	@DeleteMapping(value = "/{id}")
	public Object delete(@PathVariable("id") Integer id) {
		try {
			if(videoService.findById(id) == null || videoService.delete(id) == false) {
				return new ResponseEntity<String>("Xoá thất bại!", HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<String>("Xoá thành công!", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Xoá thất bại!", HttpStatus.BAD_REQUEST);
		}
	}
}
