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

import com.myclass.dto.TargetDTO;
import com.myclass.service.TargetService;

@RestController
@RequestMapping(value = "api/target")
public class TargetController {
	@Autowired
	private TargetService targetService;
	@GetMapping(value = "")
	public Object get() {
		List<TargetDTO> dtos = targetService.findAll();
		if(dtos.isEmpty()) {
			return new ResponseEntity<List<TargetDTO>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<TargetDTO>>(dtos, HttpStatus.OK);
	}
	@GetMapping(value = "/{id}")
	public Object get(@PathVariable("id") Integer id) {
		TargetDTO dto = targetService.findById(id);
		if(dto == null) {
			return new ResponseEntity<TargetDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<TargetDTO>(dto, HttpStatus.OK);
	}
	@PostMapping(value = "")
	public Object post(@RequestBody TargetDTO dto) {
		try {
			if(targetService.insert(dto) == false) {
				return new ResponseEntity<String>("Thêm mới thất bại!", HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<String>("Thêm mới thành công!", HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Thêm mới thất bại!", HttpStatus.BAD_REQUEST);
		}
	}
	@PutMapping(value = "")
	public Object put(@RequestBody TargetDTO dto) {
		try {
			if(targetService.findById(dto.getId()) == null || targetService.update(dto) == false) {
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
			if(targetService.findById(id) == null || targetService.delete(id) == false) {
				return new ResponseEntity<String>("Xoá thất bại!", HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<String>("Xoá thành công!", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Xoá thất bại!", HttpStatus.BAD_REQUEST);
		}
	}
}
