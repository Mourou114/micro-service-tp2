package net.mourou.billingservice.feign;


import net.mourou.billingservice.model.Customer;
import net.mourou.billingservice.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "inventory-service")
public interface ProductRestClient {
    @GetMapping("/api/products/{id}")
    Product getProductById(@PathVariable String id);
    @GetMapping("/api/products")
    PagedModel<Product> getALLProducts();
}
