package teksystems.capstone.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import teksystems.capstone.database.entity.FeederSnake;

import java.util.List;

@Repository
public interface FeederSnakeDAO extends JpaRepository<FeederSnake, Long> {

    FeederSnake findById(@Param("id") Integer id);

    @Query(value = "select fs.id, s.species, f.name, f.size, f.quantity, fs.feeding_date " +
            "from feeders f, feeder_snakes fs, snakes s " +
            "where fs.feeder_id=f.id and fs.snake_id = s.id", nativeQuery = true)
    List<FeederSnake> findAllFeedings();

    List<FeederSnake> findAllById(@Param("id") Integer id);

    //    List<FeederSnake> findBySpeciesContainingIgnoreCase(@Param("species") String species);

    //    List<FeederSnake>


//    List<FeederSnake> findByNameContainingIgnoreCase(@Param("name") String name);
}
