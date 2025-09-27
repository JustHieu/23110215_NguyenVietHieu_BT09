// vn/hieesu/bt09/graphql/QueryResolver.java
package vn.hieesu.bt09.graphsql;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.graphql.data.method.annotation.Argument;
import vn.hieesu.bt09.entity.*;
import vn.hieesu.bt09.repo.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class QueryResolver {

    private final UserRepository userRepo;
    private final CategoryRepository categoryRepo;
    private final ProductRepository productRepo;

    @QueryMapping
    public List<Product> productsSortedByPrice() {
        return productRepo.findAllByOrderByPriceAsc();
    }

    @QueryMapping
    public List<Product> productsByCategory(@Argument Long categoryId) {
        return productRepo.findByCategories_Id(categoryId);
    }

    @QueryMapping
    public List<User> users() { return userRepo.findAll(); }

    @QueryMapping
    public User user(@Argument Long id) { return userRepo.findById(id).orElse(null); }

    @QueryMapping
    public List<Category> categories() { return categoryRepo.findAll(); }

    @QueryMapping
    public Category category(@Argument Long id) { return categoryRepo.findById(id).orElse(null); }

    @QueryMapping
    public List<Product> products() { return productRepo.findAll(); }

    @QueryMapping
    public Product product(@Argument Long id) { return productRepo.findById(id).orElse(null); }
}
