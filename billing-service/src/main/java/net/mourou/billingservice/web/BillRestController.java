package net.mourou.billingservice.web;


import net.mourou.billingservice.entities.Bill;
import net.mourou.billingservice.feign.CustomerRestClient;
import net.mourou.billingservice.feign.ProductRestClient;
import net.mourou.billingservice.model.Product;
import net.mourou.billingservice.repository.BillRepository;
import net.mourou.billingservice.repository.ProductItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillRestController {
    @Autowired private BillRepository billRepository;
    @Autowired private ProductItemRepository productItemRepository;
    @Autowired private CustomerRestClient customerRestClient;
    @Autowired private ProductRestClient productRestClient;

    @GetMapping("/api/bills/{id}")
    Bill getBill(@PathVariable Long id){
        Bill bill=billRepository.findById(id).get();
        bill.setCustomer(customerRestClient.getCustomerById(bill.getCustomerId()));

        bill.getProductItem().forEach(productItem->{
            productItem.setProduct(productRestClient.getProductById(productItem.getProductId()));
        });
        /*bill.setProductItems(productItemRepository.findByBillId(id));*/
        return bill;
    }
}
