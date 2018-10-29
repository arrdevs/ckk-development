package com.client.keranjangkita.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
	    //make sure that row is not empty, example: probably cause the cache
	    int loop = 1;
	    for(int i=1;i<worksheet.getPhysicalNumberOfRows() ;i++) {
	    	 XSSFRow row = worksheet.getRow(i);
	    	 String itemCode =(row.getCell(3) != null) ? row.getCell(3).getStringCellValue() : "";
	    	 if(!itemCode.equals("")) {
	    		 loop++;
	    	 }
	    }
	    
	    for(int i=1;i< loop;i++) {
	        XSSFRow row = worksheet.getRow(i);
	        String itemId = (row.getCell(1) != null) ? row.getCell(1).getStringCellValue() : null;
	        String merchantCode = (row.getCell(2) != null) ? row.getCell(2).getStringCellValue() : "";
	        String itemCode =(row.getCell(3) != null) ? row.getCell(3).getStringCellValue() : "";
	        String barCode = (row.getCell(4) != null) ? row.getCell(4).getStringCellValue() : "";
	        String name = (row.getCell(5) != null) ? row.getCell(5).getStringCellValue() : "";
	        String netto = (row.getCell(6) != null) ? row.getCell(6).getStringCellValue() : "";
	        String brutto = (row.getCell(7) != null) ? row.getCell(7).getStringCellValue() : "";
	        String loi = (row.getCell(8) != null) ? row.getCell(8).getStringCellValue() : "";
	        String hoi = (row.getCell(9) != null) ? row.getCell(9).getStringCellValue() : "";
	        String woi = (row.getCell(10) != null) ? row.getCell(10).getStringCellValue() : "";
	        String desc =(row.getCell(11) != null) ? row.getCell(11).getStringCellValue() : "";
	        int stock = (row.getCell(12) != null) ? (int)row.getCell(12).getNumericCellValue() : 0;
	        double price = (row.getCell(13) != null) ? (double)row.getCell(13).getNumericCellValue() : 0;
	        String status = (row.getCell(14) != null) ? row.getCell(14).getStringCellValue() : "";
	        String uom = (row.getCell(15) != null) ? row.getCell(15).getStringCellValue() : "";
	        String weight = (row.getCell(16) != null) ? row.getCell(16).getStringCellValue() : "";
	        String weightType = (row.getCell(17) != null) ? row.getCell(17).getStringCellValue() : "";
	        String imageUrl = (row.getCell(18) != null) ? row.getCell(18).getStringCellValue() : "";
	        int amountAvailability = (row.getCell(19) != null) ? (int)row.getCell(19).getNumericCellValue() : 0;
	        
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
	        item.setInStock(stock);
	        item.setPrice(price);
	        item.setStatus(status);
	        item.setUom(uom);
	        item.setWeight(weight);
	        item.setWeightType(weightType);
	        item.setImageUrl(imageUrl);
	        item.setAmount_availability(amountAvailability);
	        items.add(item);
	    }
	    if(!items.isEmpty()) {
	    	productService.save(items);
	    }	    
	    
	    return "redirect:/dashboard/upload";
	}

}
