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

import com.myclass.dto.UserDTO;
import com.myclass.service.UserService;

@RestController
@RequestMapping(value = "api/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@GetMapping(value = "")
	public Object get() {
		List<UserDTO> dtos = userService.findAll();
		if(dtos.isEmpty()) {
			return new ResponseEntity<List<UserDTO>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<UserDTO>>(dtos, HttpStatus.OK);
	}
	@GetMapping(value = "/{id}")
	public Object get(@PathVariable("id") Integer id) {
		UserDTO dto = userService.findById(id);
		if(dto == null) {
			return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<UserDTO>(dto, HttpStatus.OK);
	}
	@PostMapping(value = "")
	public Object post(@RequestBody UserDTO dto) {
		try {
			if(userService.insert(dto) == false) {
				return new ResponseEntity<String>("Thêm mới thất bại!", HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<String>("Thêm mới thành công!", HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Thêm mới thất bại!", HttpStatus.BAD_REQUEST);
		}
	}
	@PutMapping(value = "")
	public Object put(@RequestBody UserDTO dto) {
		try {
			if(userService.findById(dto.getId()) == null || userService.update(dto) == false) {
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
			if(userService.findById(id) == null || userService.delete(id) == false) {
				return new ResponseEntity<String>("Xoá thất bại!", HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<String>("Xoá thành công!", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Xoá thất bại!", HttpStatus.BAD_REQUEST);
		}
	}
}
