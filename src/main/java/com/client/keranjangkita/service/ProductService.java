package com.client.keranjangkita.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.client.keranjangkita.dao.ProductDao;
import com.client.keranjangkita.model.Item;

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
}
