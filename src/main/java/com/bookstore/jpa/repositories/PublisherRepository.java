package com.bookstore.jpa.repositories;

import com.bookstore.jpa.model.PublisherModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PublisherRepository extends JpaRepository<PublisherModel, UUID> {
    PublisherModel findByName(String name);
}
