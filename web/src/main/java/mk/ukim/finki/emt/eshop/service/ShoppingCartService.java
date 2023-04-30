package mk.ukim.finki.emt.eshop.service;

import mk.ukim.finki.emt.eshop.model.Book;
import mk.ukim.finki.emt.eshop.model.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {

    List<Book> listAllProductsInShoppingCart(Long cartId);

    ShoppingCart getActiveShoppingCart(String username);

    ShoppingCart addProductToShoppingCart(String username, Long productId);
}
