package com.client.keranjangkita.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.client.keranjangkita.dao.ProductDao;
import com.client.keranjangkita.model.Item;
import com.client.keranjangkita.model.Merchant;

@Service
@Transactional
public class ProductService {

	@Autowired
	ProductDao productDao;
	
	@Transactional
	public void save(List<Item> items) {
		//for new instance if there are no any id before.
		for(Item item : items) {
				Item itemku = (productDao.findByItemCode(item.getItemCode()) == null) ? item : productDao.findByItemCode(item.getItemCode());
				item.setId(itemku.getId());
		}
		productDao.saveAll(items);
	}

	public List<Item> findByItemCode(List<Item> items) {
		// TODO Auto-generated method stub
		
		List<String> dataCode = new ArrayList();
		int i = 0;
		for(Item item : items) {
			dataCode.add(item.getItemCode());
			i++;
		}
		return productDao.findByItemCodeIn(dataCode);
	}
	
	public List<Merchant> findByMerchantListItem(List<Merchant> merchants){
		List<Item> resultItem = new ArrayList();	
		for(Merchant merchant : merchants) {
				List<String> itemsCodes = new ArrayList();
				for(Item item : merchant.getItems()) {
					String itemCode = item.getItemCode();
					itemsCodes.add(itemCode);
				}
				//datasource
				List<Item> itemsRs = productDao.findByMerchantListItem(itemsCodes, merchant.getMerchantCode());
				
				//select merchant item result
				for(Item searchItem : merchant.getItems()) {
							  Item mit = new Item();
							  mit =itemsRs.stream()
				    		  .filter(it -> it.getItemCode().equals(searchItem.getItemCode()))
				    		  .findAny().orElse(null);
							  double itemPrice = 0;
							  int itemInStock = 0;
							  if(mit != null) {
								itemPrice = mit.getPrice();
								itemInStock = mit.getInStock();
							  } 
							  searchItem.setPrice(itemPrice);
							  searchItem.setInStock(itemInStock);
							  //merchant.setItems(items);
					resultItem.add(searchItem);
				}
				merchant.setItems(resultItem);
			}
			return merchants;
	}
}
