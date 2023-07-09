package com.msnishan.basicgraphql.basicgraphql.service;

import com.msnishan.basicgraphql.basicgraphql.record.Purchase;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PurchaseService {

    private Map<Long, Purchase> purchases = new HashMap<>();

    public Purchase getPurchaseById(Long txnId) {
        return purchases.get(txnId);
    }

    public Collection<Purchase> getPurchasesByCustomer(Long customerId) {
        return purchases.values().stream()
                .filter(purchase -> purchase.customerId().equals(customerId))
                .collect(Collectors.toList());
    }

    @PostConstruct
    public void loadPurchase() {
        purchases.put(1L, new Purchase(1L, 1L, 100.33));
        purchases.put(2L, new Purchase(2L, 1L, 110.33));
        purchases.put(3L, new Purchase(3L, 2L, 120.33));
        purchases.put(4L, new Purchase(4L, 3L, 130.33));
        purchases.put(5L, new Purchase(5L, 4L, 140.33));
    }
}
