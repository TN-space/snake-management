package teksystems.capstone.database.dao;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import teksystems.capstone.database.entity.Feeder;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FeederDAOTest {

    @Autowired
    private FeederDAO feederDAO;

    @Test
    @Order(1)
    public void getFeederTest() {
        Feeder expected = new Feeder();
        expected.setId(4);
        expected.setName("Rat");
        expected.setSize("Medium");
        expected.setStatus("F/T");
        expected.setImgUrl("https://i.ytimg.com/vi/RrTTmBIdFqo/maxresdefault.jpg");
        expected.setQuantity(0);
        expected.setUserId(4);

        Feeder actual = feederDAO.findById(4);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @Order(2)
    public void createFeederTest() {
        Feeder feeder = new Feeder();
        feeder.setName("Rat");
        feeder.setSize("Medium");
        feeder.setStatus("F/T");
        feeder.setImgUrl("https://i.ytimg.com/vi/RrTTmBIdFqo/maxresdefault.jpg");
        feeder.setQuantity(10);
        feeder.setUserId(4);

        feederDAO.save(feeder);

        Assertions.assertTrue(feeder.getId() > 0);
    }

    @Test
    @Order(3)
    public void updateFeederTest() {
        Feeder feeder = feederDAO.findById(1);
        feeder.setQuantity(100);

        feederDAO.save(feeder);

        Assertions.assertEquals(feeder.getQuantity(), feederDAO.findById(1).getQuantity());
    }

    // Need working test for delete for feeder
}
