package com.xdman.temporalpoc.repository;

import com.xdman.temporalpoc.domain.Items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemsRepository extends JpaRepository<Items,Integer> {
}
