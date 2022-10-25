package com.rocky.kpb.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto implements Serializable{

	private int bookNo;
	private String bookTitle;
	private String author;
	private double price;
}
