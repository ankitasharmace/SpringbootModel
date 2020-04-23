package com.example.demo.Repository;

import com.example.demo.Entities.hotelData;
import org.springframework.data.repository.CrudRepository;

public interface RedisRepository extends CrudRepository<hotelData,String> {
}
