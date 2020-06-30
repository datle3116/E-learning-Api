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

import com.myclass.dto.UserCourseDTO;
import com.myclass.service.UserCourseService;

@RestController
@RequestMapping(value = "api/user_course")
public class UserCourseController {
	@Autowired
	private UserCourseService userCourseService;
	@GetMapping(value = "")
	public Object get() {
		List<UserCourseDTO> dtos = userCourseService.findAll();
		if(dtos.isEmpty()) {
			return new ResponseEntity<List<UserCourseDTO>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<UserCourseDTO>>(dtos, HttpStatus.OK);
	}
	@GetMapping(value = "/{id}")
	public Object get(@PathVariable("id") Integer id) {
		UserCourseDTO dto = userCourseService.findById(id);
		if(dto == null) {
			return new ResponseEntity<UserCourseDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<UserCourseDTO>(dto, HttpStatus.OK);
	}
	@PostMapping(value = "")
	public Object post(@RequestBody UserCourseDTO dto) {
		try {
			if(userCourseService.insert(dto) == false) {
				return new ResponseEntity<String>("Thêm mới thất bại!", HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<String>("Thêm mới thành công!", HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Thêm mới thất bại!", HttpStatus.BAD_REQUEST);
		}
	}
// Không có chức năng sửa vì 3 thuộc tính trong userCourse đều không được sửa
//	@PutMapping(value = "")
//	public Object put(@RequestBody UserCourseDTO dto) {
//		try {
//			if(userCourseService.update(dto) == false) {
//				return new ResponseEntity<String>("Cập nhật thất bại!", HttpStatus.BAD_REQUEST);
//			}
//			return new ResponseEntity<String>("Cập nhật thành công!", HttpStatus.OK);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return new ResponseEntity<String>("Cập nhật thất bại!", HttpStatus.BAD_REQUEST);
//		}
//	}
	@DeleteMapping(value = "/{id}")
	public Object delete(@PathVariable("id") Integer id) {
		try {
			if(userCourseService.findById(id) == null || userCourseService.delete(id) == false) {
				return new ResponseEntity<String>("Xoá thất bại!", HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<String>("Xoá thành công!", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Xoá thất bại!", HttpStatus.BAD_REQUEST);
		}
	}
}
