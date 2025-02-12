package net.mourou.billingservice.entities;

import jakarta.persistence.*;
import lombok.*;
import net.mourou.billingservice.model.Customer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date billingDate;
    private Long customerId;
    @OneToMany(mappedBy = "bill")
    private List<ProductItem> productItem = new ArrayList<>();
    @Transient private Customer customer;

}
