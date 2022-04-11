package teksystems.capstone.database.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import teksystems.capstone.database.entity.Feeder;

import java.util.List;

@Repository
public interface FeederDAO extends JpaRepository<Feeder, Long> {

    Feeder findById(@Param("id") Integer id);

    List<Feeder> findAll();

    List<Feeder> findByNameContainingIgnoreCase(@Param("name") String name);
}
