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

import com.myclass.dto.CategoryDTO;
import com.myclass.dto.CourseDTO;
import com.myclass.service.CategoryService;
import com.myclass.service.CourseService;

@RestController
@RequestMapping(value = "api/category")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping(value = "")
	public Object get() {
		List<CategoryDTO> dtos = categoryService.findAll();
		if(dtos.isEmpty()) {
			return new ResponseEntity<List<CategoryDTO>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<CategoryDTO>>(dtos, HttpStatus.OK);
	}
	@GetMapping(value = "/{id}")
	public Object get(@PathVariable("id") Integer id) {
		CategoryDTO dto = categoryService.findById(id);
		if(dto == null) {
			return new ResponseEntity<List<CategoryDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<CategoryDTO>(dto, HttpStatus.OK);
	}
	@PostMapping(value = "")
	public Object post(@RequestBody CategoryDTO dto) {
		try {
			if(categoryService.insert(dto) == false) {
				return new ResponseEntity<String>("Thêm mới thất bại!", HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<String>("Thêm mới thành công!", HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Thêm mới thất bại!", HttpStatus.BAD_REQUEST);
		}
	}
	@PutMapping(value = "")
	public Object put(@RequestBody CategoryDTO dto) {
		try {
			if(categoryService.findById(dto.getId()) == null || categoryService.update(dto) == false) {
				return new ResponseEntity<String>("Cật nhật thất bại!", HttpStatus.BAD_REQUEST);
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
			if(categoryService.findById(id) == null || categoryService.delete(id) == false) {
				return new ResponseEntity<String>("Xoá thất bại!", HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<String>("Xoá thành công!", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Xoá thất bại!", HttpStatus.BAD_REQUEST);
		}
	}
}
