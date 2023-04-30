package mk.ukim.finki.emt.eshop.jobs;
import mk.ukim.finki.emt.eshop.service.BookService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private final BookService productService;

    public ScheduledTasks(BookService productService) {
        this.productService = productService;
    }

    @Scheduled(fixedDelay = 5000)
    public void refreshMaterializedView() {
        //this.productService.refreshMaterializedView();
    }
}
