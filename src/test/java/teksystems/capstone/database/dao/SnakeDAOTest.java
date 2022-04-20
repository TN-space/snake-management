package teksystems.capstone.database.dao;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import teksystems.capstone.database.entity.FeederSnake;
import teksystems.capstone.database.entity.Snake;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SnakeDAOTest {

    @Autowired
    private SnakeDAO snakeDAO;

    @Autowired
    private FeederSnakeDAO feederSnakeDAO;


    @Test
    @Order(1)
    public void getSnakeTest() {
        Snake expected = new Snake();
        expected.setId(112);
        expected.setSpecies("Reticulated Python");
        expected.setSex("male");
        expected.setNote("juvenile");
        expected.setImgUrl("https://reptilianostra.com/wp-content/uploads/2019/04/Orange-glow-tiger-retic.jpg");
        expected.setUserId(1);

        Snake actual = snakeDAO.findById(112);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @Order(2)
    public void createSnakeTest() {
        Snake snake = new Snake();
        snake.setSpecies("Retic");
        snake.setSex("male");
        snake.setNote("juvenile");
        snake.setImgUrl("this isn't null");
        snake.setBirthDate(LocalDate.now());
        snake.setUserId(1);

        snakeDAO.save(snake);

        Assertions.assertTrue(snake.getId() > 0);
    }

    @Test
    @Order(3)
    public void updateSnakeTest() {
        Snake snake = snakeDAO.findById(112);
        snake.setSex("Female");

        snakeDAO.save(snake);

        Assertions.assertEquals(snake.getSex(), snakeDAO.findById(112).getSex());
    }

    // not working yet!!
    @Test
    @Order(4)
    public void deleteSnakeTest() {
        Snake snake = snakeDAO.findById(112);
//        List<FeederSnake> feederSnakes = feederSnakeDAO.findAllById(112);
//
//        for (FeederSnake x:feederSnakes) {
//            feederSnakeDAO.delete(x);
//        }

        snakeDAO.delete(snake);

        Assertions.assertNull(snakeDAO.findById(112));
    }
}
