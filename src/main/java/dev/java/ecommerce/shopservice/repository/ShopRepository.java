package dev.java.ecommerce.shopservice.repository;

import dev.java.ecommerce.shopservice.entities.Shop;
import dev.java.ecommerce.shopservice.entities.Status;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShopRepository extends MongoRepository<Shop, String> {

    Optional<Shop> findByClientAndStatus(Long client, Status status);
}
