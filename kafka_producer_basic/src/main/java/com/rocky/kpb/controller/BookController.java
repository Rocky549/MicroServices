package com.rocky.kpb.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rocky.kpb.dto.BookDto;
import com.rocky.kpb.service.BookService;

@RestController
@RequestMapping("/book")
public class BookController {

	@Autowired
	BookService bookService;
	@GetMapping(value = "/getAll",produces = MediaType.APPLICATION_JSON_VALUE)
	public List<BookDto> getAll(){
		BookDto b1=new BookDto(1, "C", "SRS", 2000);
		BookDto b2=new BookDto(2, "C++", "SRS", 3000);
		BookDto b3=new BookDto(3, "JAVA", "SRS", 5000);
		BookDto b4=new BookDto(4, "Sping MVC", "SRS", 15000);
		List<BookDto> bookList=new ArrayList<>();
		bookList.add(b1);
		bookList.add(b2);
		bookList.add(b3);
		bookList.add(b4);
		return bookList;
	}
	
	@PostMapping(value = "/publishBook",consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public String publishBook(@RequestBody List<BookDto> bookDtoList) {
		return bookService.publishBooks(bookDtoList,"bookTopic");
	}
}
