package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Address;
import com.example.entity.Cart;
import com.example.entity.Order;
import com.example.entity.OrderDetail;
import com.example.entity.Product;
import com.example.entity.Seller;
import com.example.entity.User;

public interface Repository {
	public interface ProductRepository extends JpaRepository<Product, Long> {}
	public interface CartRepository extends JpaRepository<Cart, Long> {
	    List<Cart> findByUser(User user);
	}
	public interface OrderRepository extends JpaRepository<Order, Long> {}
	public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {}
	public interface AddressRepository extends JpaRepository<Address, Long> {}
	public interface SellerRepository extends JpaRepository<Seller, Long> {}
	public interface UserRepository extends JpaRepository<User, Long> {}

}
