package com.target.myretail;

import com.target.myretail.model.Product;
import com.target.myretail.model.ProductPrice;
import com.target.myretail.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
@SpringBootTest
class MyretailServiceApplicationTests {

	@Test
	void contextLoads() {
	}

	@Mock
	private ProductRepository productRepository;

	@Test
	public void whenFindAllProducts_thenReturnProductList() {
		ProductPrice productPriceOne = new ProductPrice(599.5,"INR");
		Product productone = new Product("372193791", "APPLE IPAD",productPriceOne);
		ProductPrice productPriceTwo = new ProductPrice(1599.5,"USD");
		Product productTwo = new Product("432422", "APPLE MAC",productPriceOne);
		ProductPrice productPriceThree = new ProductPrice(799.5,"EUR");
		Product productThree = new Product("422219", "SAMSUNG Mobile",productPriceOne);
		List<Product> prodcuts = Arrays.asList(productone,productTwo,productThree);
		when(productRepository.findAll()).thenReturn(prodcuts);
		List<Product> products = productRepository.findAll();
		Assertions.assertEquals (3, products.size());
		verify(productRepository, times(1)).findAll();
	}

	@Test
	public void whenValidId_thenReturnProduct() {
		ProductPrice productPriceOne = new ProductPrice(599.5,"INR");
		Product productOne = new Product("372193791", "APPLE IPAD",productPriceOne);
		when(productRepository.findById("372193791")).thenReturn(
				Optional.of(productOne)
		);

		Optional<Product> product = productRepository.findById("372193791");

		if (product.isPresent()) {
			Assertions.assertEquals("372193791", product.get().getId());
			Assertions.assertEquals(599.5, product.get().getProductPrice().getPrice(), 599.5);
			Assertions.assertEquals("INR", product.get().getProductPrice().getCurrencyCode());
		}
	}


	@Test
	public void whenValidInput_thenCreateProduct() {
		ProductPrice productPriceOne = new ProductPrice(599.5,"INR");
		Product productOne = new Product("372193791", "APPLE IPAD",productPriceOne);
		productRepository.save(productOne);
		verify(productRepository, times(1)).save(productOne);
	}

}