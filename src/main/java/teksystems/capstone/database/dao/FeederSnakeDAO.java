package teksystems.capstone.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import teksystems.capstone.database.entity.FeederSnake;

import java.util.List;

@Repository
public interface FeederSnakeDAO extends JpaRepository<FeederSnake, Long> {

    @Query("Select fs from FeederSnake fs where fs.feederId = :feederId and fs.snakeId = :snakeId")
    List<FeederSnake> findAll(@Param("feederId") Integer feederId, @Param("snakeId") Integer snakeId);

//    List<FeederSnake>


//    List<FeederSnake> findByNameContainingIgnoreCase(@Param("name") String name);
}
