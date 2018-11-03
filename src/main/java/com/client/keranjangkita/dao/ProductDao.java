package com.client.keranjangkita.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.client.keranjangkita.model.Item;
import com.client.keranjangkita.model.Merchant;

public interface ProductDao extends PagingAndSortingRepository<Item, String>{

	public Item findByItemCode(String itemCode);

	public List<Item> findAllByItemCode(List<String> dataCode);

	public List<Item> findByItemCodeIn(List<String> dataCode);
	
	@Query("select item from Item item where item.itemCode in :itemCodes and item.merchantCode =:merchantCode")
	public List<Item> findByMerchantListItem(@Param("itemCodes") List<String> itemCodes, @Param("merchantCode") String merchantCode);
}
