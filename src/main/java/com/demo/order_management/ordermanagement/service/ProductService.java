package com.demo.order_management.ordermanagement.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.order_management.ordermanagement.dao.ProductDao;
import com.demo.order_management.ordermanagement.model.Product;
import com.demo.order_management.ordermanagement.request.ProductRequest;

@Service
public class ProductService {

	@Autowired
	ProductDao productDao;

	public Product create(ProductRequest productRequest) {
		Product product = new Product();
		try {
			product.setProduct_name(productRequest.getProduct_name());
			product.setProduct_price(productRequest.getPrice());
			product.setProduct_quantity(productRequest.getQuantity());
			product = productDao.save(product);
			return product;
		} catch (Exception e) {
			// System.out.println("Exception: "+ e);
		}
		return product;
	}

	public Product find(Long id) {
		Product product = new Product();
		try {
			if (productDao.existsById(id)) {
				product = productDao.findByProductId(id);
			}
			return product;
		} catch (Exception e) {
			// System.out.println("Exception: "+ e);
		}
		return product;
	}

	
	public List<Product> find_all() {
		List<Product> product = new LinkedList<Product>();
		try {
			product = productDao.findAll();
			return product;
		} catch (Exception e) {
			// System.out.println("Exception: "+ e);
		}
		return product;
	}
	
	public boolean delete(Long id) {
		try {
			if(productDao.existsById(id)) {
				productDao.deleteById(id);
				return true;
			}
			return false;
		} catch (Exception e) {
			// System.out.println("Exception: "+ e);
		}
		return false;
	}
	
	public Product update(Long id, ProductRequest productRequest) {
		Product product = new Product();
		try {
			if(productDao.existsById(id)) {
				product.setProductId(id);
				if(productRequest.getPrice() != null && productRequest.getPrice()>0)
					product.setProduct_price(productRequest.getPrice());
				if(productRequest.getQuantity() != null && productRequest.getQuantity()>=0)
					product.setProduct_quantity(productRequest.getQuantity());
				
			}
		} catch (Exception e) {
			// System.out.println("Exception: "+ e);
		}
		return product;
	}
}
