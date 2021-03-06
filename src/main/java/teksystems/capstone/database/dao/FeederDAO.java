package teksystems.capstone.database.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import teksystems.capstone.database.entity.Feeder;
import teksystems.capstone.database.entity.Snake;

import java.util.List;

@Repository
public interface FeederDAO extends JpaRepository<Feeder, Long> {

    Feeder findById(@Param("id") Integer id);

    Feeder findFeederById(@Param("id") Integer id);

    Feeder findFeederByNameIgnoreCase(@Param("name") String name);

    List<Feeder> findAll();

    List<Feeder> findByNameContainingIgnoreCase(@Param("name") String name);
}
