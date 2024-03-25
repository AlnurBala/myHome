package com.startup.myhome.repository;

import com.startup.myhome.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PropertyRepository extends JpaRepository<Property,Integer> {


}
