package com.example.smart_waiting.market;

import com.example.smart_waiting.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MarketRepository extends JpaRepository<Market,Long> {
    Optional<Market> findByUser(User user);
}
