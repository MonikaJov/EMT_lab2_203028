//package mk.ukim.finki.emt.eshop.service.impl;
//
//import mk.ukim.finki.emt.eshop.model.Category;
//import mk.ukim.finki.emt.eshop.repository.CategoryRepository;
//import mk.ukim.finki.emt.eshop.repository.impl.InMemoryCategoryRepository;
//import mk.ukim.finki.emt.eshop.service.CategoryService;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class CategoryServiceImpl implements CategoryService {
//
//    private final InMemoryCategoryRepository categoryRepository;
//
//    public CategoryServiceImpl(InMemoryCategoryRepository categoryRepository) {
//        this.categoryRepository = categoryRepository;
//    }
//
//    @Override
//    public Category create(String name, String description) {
//        if (name==null || name.isEmpty()) {
//            throw new IllegalArgumentException();
//        }
//        Category c = new Category(name,description);
//        categoryRepository.save(c);
//        return c;
//    }
//
//    @Override
//    public Category update(String name, String description) {
//        if (name==null || name.isEmpty()) {
//            throw new IllegalArgumentException();
//        }
//        Category c= new Category(name,description);
//        categoryRepository.save(c);
//        return c;
//    }
//
//    @Override
//    public void delete(String name) {
//        if (name==null || name.isEmpty()) {
//            throw new IllegalArgumentException();
//        }
////        categoryRepository.deleteByName(name);
//        categoryRepository.delete(name);
//    }
//
//    @Override
//    public List<Category> listCategories() {
//        return categoryRepository.findAll();
//    }
//
//    @Override
//    public List<Category> searchCategories(String searchText) {
////        return categoryRepository.findAllByNameLike(searchText);
//        return categoryRepository.search(searchText);
//    }
//}
