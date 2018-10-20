package com.client.keranjangkita.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.client.keranjangkita.model.Item;

public interface ProductDao extends PagingAndSortingRepository<Item, String>{

	public Item findByItemCode(String itemCode);

	public List<Item> findAllByItemCode(List<String> dataCode);

	public List<Item> findByItemCodeIn(List<String> dataCode);
}
