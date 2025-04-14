package com.college.bus.repository;

import com.college.bus.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/*public interface DriverRepository extends JpaRepository<Driver, Long> {
    Optional<Driver> findByPhone(String phone);
    
}
*/

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
    
    @Query("SELECT d FROM Driver d WHERE d.phone = :phone")
    Optional<Driver> findByPhone(@Param("phone") String phone);
}
