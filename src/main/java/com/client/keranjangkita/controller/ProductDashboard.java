package com.client.keranjangkita.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.client.keranjangkita.model.Item;
import com.client.keranjangkita.service.ProductService;

@Controller
@RequestMapping("/dashboard")
public class ProductDashboard {

	@Autowired
	ProductService productService;
	
	@RequestMapping("/upload")
	public String index() {
		return "upload";
	}
	
	@PostMapping("/import")
	public String mapReapExcelDatatoDB(@RequestParam("file") MultipartFile reapExcelDataFile) throws IOException {
	   List<Item> items = new ArrayList();
	
	   XSSFWorkbook workbook = new XSSFWorkbook(reapExcelDataFile.getInputStream());
	    XSSFSheet worksheet = workbook.getSheetAt(0);   
	    DataFormatter formatter = new DataFormatter();
	    for(int i=1;i< worksheet.getPhysicalNumberOfRows();i++) {
	        XSSFRow row = worksheet.getRow(i);
		        String itemId = formatter.formatCellValue(row.getCell(1));
		        String merchantCode = formatter.formatCellValue(row.getCell(2));
		        String itemCode = formatter.formatCellValue(row.getCell(3));
		        String barCode = formatter.formatCellValue(row.getCell(4));
		        String name = formatter.formatCellValue(row.getCell(5));
		        String netto = formatter.formatCellValue(row.getCell(6));
		        String brutto = formatter.formatCellValue(row.getCell(7));
		        String loi = formatter.formatCellValue(row.getCell(8));
		        String hoi = formatter.formatCellValue(row.getCell(9));
		        String woi = formatter.formatCellValue(row.getCell(10));
		        String desc =formatter.formatCellValue(row.getCell(11));
		        String stock = formatter.formatCellValue(row.getCell(12));
		        double price = (row.getCell(13) != null) ? row.getCell(13).getNumericCellValue() : 0;
		        String status = formatter.formatCellValue(row.getCell(14));
		        String uom = formatter.formatCellValue(row.getCell(15));
		        String weight = formatter.formatCellValue(row.getCell(16));
		        String weightType =formatter.formatCellValue(row.getCell(17));
		        String imageUrl = formatter.formatCellValue(row.getCell(18));
		        String amountAvailability = formatter.formatCellValue(row.getCell(19));
		        
		        Item item = new Item();
		        item.setId(itemId);
		        item.setMerchantCode(merchantCode);
		        item.setItemCode(itemCode);
		        item.setName(name);
		        item.setBarCode(barCode);
		        item.setNetto(netto);
		        item.setBrutto(brutto);
		        item.setLengthOfItem(loi);
		        item.setLengthOfItem(hoi);
		        item.setWidthOfItem(woi);
		        item.setDescription(desc);
		        item.setInStock(Integer.parseInt(stock));
		        item.setPrice(price);
		        item.setStatus(status);
		        item.setUom(uom);
		        item.setWeight(weight);
		        item.setWeightType(weightType);
		        item.setImageUrl(imageUrl);
		        item.setAmount_availability(Integer.parseInt(amountAvailability));
		        items.add(item);
	        
	    }
	    if(!items.isEmpty()) {
	    	productService.save(items);
	    }	    
	    
	    return "redirect:/dashboard/upload";
	}

}
