package io.github.emvnuel.statefulapp.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
