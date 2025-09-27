package vn.hieesu.bt09.graphsql;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;
import vn.hieesu.bt09.entity.*;
import vn.hieesu.bt09.repo.*;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequiredArgsConstructor
public class MutationResolver {

    private final UserRepository userRepo;
    private final CategoryRepository categoryRepo;
    private final ProductRepository productRepo;

    // ===== Input record (trùng tên field trong schema) =====
    public record UserInput(String fullname, String email, String password, String phone, Set<Long> categoryIds) {}
    public record CategoryInput(String name, String images, Set<Long> userIds, Set<Long> productIds) {}
    public record ProductInput(String title, Integer quantity, String description, Double price, Long userId, Set<Long> categoryIds) {}

    // ---------------- USER ----------------
    @MutationMapping
    public User createUser(@Argument UserInput input) {
        User u = new User();
        u.setFullname(input.fullname());
        u.setEmail(input.email());
        u.setPassword(input.password());
        u.setPhone(input.phone());
        if (input.categoryIds()!=null && !input.categoryIds().isEmpty()) {
            Set<Category> cs = new HashSet<>(categoryRepo.findAllById(input.categoryIds()));
            u.setCategories(cs);
        }
        return userRepo.save(u);
    }

    @MutationMapping
    public User updateUser(@Argument Long id, @Argument UserInput input) {
        User u = userRepo.findById(id).orElseThrow();
        if (input.fullname()!=null) u.setFullname(input.fullname());
        if (input.email()!=null)    u.setEmail(input.email());
        if (input.password()!=null) u.setPassword(input.password());
        if (input.phone()!=null)    u.setPhone(input.phone());
        if (input.categoryIds()!=null) {
            Set<Category> cs = new HashSet<>(categoryRepo.findAllById(input.categoryIds()));
            u.setCategories(cs);
        }
        return userRepo.save(u);
    }

    @MutationMapping
    public Boolean deleteUser(@Argument Long id) {
        if (!userRepo.existsById(id)) return false;
        userRepo.deleteById(id);
        return true;
    }

    // ---------------- CATEGORY ----------------
    @MutationMapping
    public Category createCategory(@Argument CategoryInput input) {
        Category c = new Category();
        c.setName(input.name());
        c.setImages(input.images());
        // gán users/products nếu muốn (không bắt buộc)
        return categoryRepo.save(c);
    }

    @MutationMapping
    public Category updateCategory(@Argument Long id, @Argument CategoryInput input) {
        Category c = categoryRepo.findById(id).orElseThrow();
        if (input.name()!=null)   c.setName(input.name());
        if (input.images()!=null) c.setImages(input.images());
        return categoryRepo.save(c);
    }

    @MutationMapping
    public Boolean deleteCategory(@Argument Long id) {
        if (!categoryRepo.existsById(id)) return false;
        categoryRepo.deleteById(id);
        return true;
    }

    // ---------------- PRODUCT ----------------
    @MutationMapping
    public Product createProduct(@Argument ProductInput input) {
        Product p = new Product();
        p.setTitle(input.title());
        p.setQuantity(input.quantity());
        p.setDescription(input.description());
        p.setPrice(input.price());

        if (input.userId()!=null) {
            User u = userRepo.findById(input.userId()).orElseThrow();
            p.setUser(u);
        }
        if (input.categoryIds()!=null && !input.categoryIds().isEmpty()) {
            Set<Category> cs = new HashSet<>(categoryRepo.findAllById(input.categoryIds()));
            p.setCategories(cs);
        }
        return productRepo.save(p);
    }

    @MutationMapping
    public Product updateProduct(@Argument Long id, @Argument ProductInput input) {
        Product p = productRepo.findById(id).orElseThrow();
        if (input.title()!=null)       p.setTitle(input.title());
        if (input.quantity()!=null)    p.setQuantity(input.quantity());
        if (input.description()!=null) p.setDescription(input.description());
        if (input.price()!=null)       p.setPrice(input.price());
        if (input.userId()!=null) {
            User u = userRepo.findById(input.userId()).orElseThrow();
            p.setUser(u);
        }
        if (input.categoryIds()!=null) {
            Set<Category> cs = new HashSet<>(categoryRepo.findAllById(input.categoryIds()));
            p.setCategories(cs);
        }
        return productRepo.save(p);
    }

    @MutationMapping
    public Boolean deleteProduct(@Argument Long id) {
        if (!productRepo.existsById(id)) return false;
        productRepo.deleteById(id);
        return true;
    }
}
