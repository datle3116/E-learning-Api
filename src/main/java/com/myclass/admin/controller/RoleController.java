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

import com.myclass.dto.RoleDTO;
import com.myclass.service.RoleService;

@RestController
@RequestMapping(value = "api/role")
public class RoleController {
	@Autowired
	private RoleService roleService;
	@GetMapping(value = "")
	public Object get() {
		List<RoleDTO> dtos = roleService.findAll();
		if(dtos.isEmpty()) {
			return new ResponseEntity<List<RoleDTO>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<RoleDTO>>(dtos, HttpStatus.OK);
	}
	@GetMapping(value = "/{id}")
	public Object get(@PathVariable("id") Integer id) {
		RoleDTO dto = roleService.findById(id);
		if(dto == null) {
			return new ResponseEntity<RoleDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<RoleDTO>(dto, HttpStatus.OK);
	}
	@PostMapping(value = "")
	public Object post(@RequestBody RoleDTO dto) {
		try {
			if(roleService.insert(dto) == false) {
				return new ResponseEntity<String>("Thêm mới thất bại!", HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<String>("Thêm mới thành công!", HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Thêm mới thất bại!", HttpStatus.BAD_REQUEST);
		}
	}
	@PutMapping(value = "")
	public Object put(@RequestBody RoleDTO dto) {
		try {
			if(roleService.findById(dto.getId()) == null || roleService.update(dto) == false) {
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
			if(roleService.findById(id) == null || roleService.delete(id) == false) {
				return new ResponseEntity<String>("Xoá thất bại!", HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<String>("Xoá thành công!", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Xoá thất bại!", HttpStatus.BAD_REQUEST);
		}
	}
}
