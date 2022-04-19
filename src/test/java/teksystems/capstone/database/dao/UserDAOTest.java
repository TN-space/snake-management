package teksystems.capstone.database.dao;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import teksystems.capstone.database.entity.User;
import teksystems.capstone.database.entity.UserRole;

import java.util.Date;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserDAOTest {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserRoleDAO userRoleDAO;

    @Test
    @Order(1)
    public void getUserTest() {
        User expected = new User();
        expected.setId(1);
        expected.setEmail("eheilig@perscholas.org");
        expected.setFirstName("Eric");
        expected.setLastName("Heilig");
        expected.setPassword("$2a$12$nnrT/t19EUoAZx1KGYphyehcuAFVxhYLQ2uL4/.SOdkrZXTJnQpVi");

        User actual = userDAO.findById(1);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @Order(2)
    public void createUserTest() {
        User user = new User();
        user.setFirstName("T");
        user.setLastName("N");
        user.setEmail("tha@gmail.com");
        user.setPassword("password");
        user.setCreateDate(new Date());

        userDAO.save(user);

        Assertions.assertTrue(user.getId() > 0);
    }

    @Test
    @Order(3)
    public void updateUser() {
        User user = userDAO.findById(1);
        user.setFirstName("Eriko");

        userDAO.save(user);

        Assertions.assertEquals(user.getFirstName(), userDAO.findById(1).getFirstName());
    }

    @Test
    @Order(4)
    public void deleteUser() {
        User user = userDAO.findById(2);
        List<UserRole> roles = userRoleDAO.findByUserId(2);

        for (UserRole x:roles) {
            userRoleDAO.delete(x);
        }

        userDAO.delete(user);

        Assertions.assertNull(userDAO.findById(2));
    }
}
