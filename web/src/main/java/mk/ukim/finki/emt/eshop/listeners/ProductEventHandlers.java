package mk.ukim.finki.emt.eshop.listeners;

import mk.ukim.finki.emt.eshop.model.events.ProductCreatedEvent;
import mk.ukim.finki.emt.eshop.service.BookService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ProductEventHandlers {

    private final BookService productService;

    public ProductEventHandlers(BookService productService) {
        this.productService = productService;
    }

    @EventListener
    public void onProductCreated(ProductCreatedEvent event) {
        this.productService.refreshMaterializedView();
    }
}
