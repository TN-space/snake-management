package teksystems.capstone.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import teksystems.capstone.database.entity.Snake;

import java.util.List;

@Repository
public interface SnakeDAO extends JpaRepository<Snake, Long> {
    Snake findById(@Param("id") Integer id);

    List<Snake> findBySpeciesContainingIgnoreCase(@Param("species") String species);

    List<Snake> findAll();
}
