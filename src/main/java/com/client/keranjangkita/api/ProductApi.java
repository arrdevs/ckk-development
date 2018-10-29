package com.client.keranjangkita.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.client.keranjangkita.model.Item;
import com.client.keranjangkita.service.ProductService;


@RestController
@RequestMapping("/api/product")
public class ProductApi {

	@Autowired
	ProductService productService;
	
	@RequestMapping(value="/search", method=RequestMethod.POST)
	@ResponseBody
	public List<Item> findByItemCodeOrBarCode(@RequestBody List<Item> items){
		List<Item> itemResults = productService.findByItemCode(items);
		return itemResults;
	}
}