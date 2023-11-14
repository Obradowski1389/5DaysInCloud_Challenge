package com.example.challenge.repository;

import com.example.challenge.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    Optional<Player> getByFullName(String fullName);

    @Query(value = "SELECT * FROM Players", nativeQuery = true)
    List<Player> getAll();

}
