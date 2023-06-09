package mk.ukim.finki.emt.eshop.service.impl;

import mk.ukim.finki.emt.eshop.model.Book;
import mk.ukim.finki.emt.eshop.repository.impl.InMemoryShoppingCartRepository;
import mk.ukim.finki.emt.eshop.repository.impl.InMemoryUserRepository;
import mk.ukim.finki.emt.eshop.model.ShoppingCart;
import mk.ukim.finki.emt.eshop.model.User;
import mk.ukim.finki.emt.eshop.model.enumerations.ShoppingCartStatus;
import mk.ukim.finki.emt.eshop.model.exceptions.ProductAlreadyInShoppingCartException;
import mk.ukim.finki.emt.eshop.model.exceptions.ProductNotFoundException;
import mk.ukim.finki.emt.eshop.model.exceptions.ShoppingCartNotFoundException;
import mk.ukim.finki.emt.eshop.model.exceptions.UserNotFoundException;
import mk.ukim.finki.emt.eshop.repository.ShoppingCartRepository;
import mk.ukim.finki.emt.eshop.repository.UserRepository;
import mk.ukim.finki.emt.eshop.service.BookService;
import mk.ukim.finki.emt.eshop.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final InMemoryShoppingCartRepository shoppingCartRepository;
    private final InMemoryUserRepository userRepository;
    private final BookService productService;

    public ShoppingCartServiceImpl(InMemoryShoppingCartRepository shoppingCartRepository,
                                   InMemoryUserRepository userRepository,
                                   BookService productService) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
        this.productService = productService;
    }

    @Override
    public List<Book> listAllProductsInShoppingCart(Long cartId) {
        if(!this.shoppingCartRepository.findById(cartId).isPresent())
            throw new ShoppingCartNotFoundException(cartId);
        return this.shoppingCartRepository.findById(cartId).get().getProducts();
    }

    @Override
    public ShoppingCart getActiveShoppingCart(String username) {
        User user = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));

        return this.shoppingCartRepository
                .findByUsernameAndStatus(username, ShoppingCartStatus.CREATED)
                .orElseGet(() -> {
                    ShoppingCart cart = new ShoppingCart(user);
                    return this.shoppingCartRepository.save(cart);
                });
    }

    @Override
    public ShoppingCart addProductToShoppingCart(String username, Long productId) {
        ShoppingCart shoppingCart = this.getActiveShoppingCart(username);
        Book product = this.productService.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
        if(shoppingCart.getProducts()
                .stream().filter(i -> i.getId().equals(productId))
                .collect(Collectors.toList()).size() > 0)
            throw new ProductAlreadyInShoppingCartException(productId, username);
        shoppingCart.getProducts().add(product);
        return this.shoppingCartRepository.save(shoppingCart);
    }
}
