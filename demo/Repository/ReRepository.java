package com.example.demo.Repository;

import com.example.demo.Entities.hotelData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReRepository extends CrudRepository<hotelData, String> {

}
