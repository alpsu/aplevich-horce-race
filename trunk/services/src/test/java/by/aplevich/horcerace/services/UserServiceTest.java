package by.aplevich.horcerace.services;

import by.aplevich.horcerace.AbstractServiceTest;
import by.aplevich.horcerace.datamodel.User;
import by.aplevich.horcerace.datamodel.enums.UserRole;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.persistence.PersistenceException;
import java.util.Arrays;

public class UserServiceTest extends AbstractServiceTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceTest.class);

    @Inject
    private UserService userService;

    @Test
    public void crudTest() {
        final User userOne = createUser();
        userService.createNewUser(userOne);

        final User createdUser = userService.get(userOne.getId());
        Assert.assertNotNull(createdUser);
        /*Assert.assertEquals(createdUser.getName(), user.getName());
        Assert.assertEquals(createdUser.getLogin(), user.getLogin());
        Assert.assertEquals(createdUser.getPassword(), user.getPassword());
        Assert.assertEquals(createdUser.getRole(), user.getRole());

        userService.deteteUser(user.getId());
        Assert.assertNull(userService.get(user.getId()));*/
    }

   /* @Test
    public void uniqueConstraintsTest() {
        final User user = createUser();
        final String login = randomString("login");
        user.setLogin(login);
        userService.createNewUser(user);

        final User duplicateUser = createUser();
        duplicateUser.setLogin(login);
        try {
            userService.createNewUser(duplicateUser);
            Assert.fail("Not unique login can`t be saved ");
        } catch (final PersistenceException e) {

        }

        duplicateUser.setLogin(randomString("login"));
        userService.createNewUser(duplicateUser);
    }
*/
    private User createUser() {
        final User userTwo = new User();
        userTwo.setLogin(randomString("login-"));
        userTwo.setPassword(randomString("pass-"));
        userTwo.setName(randomString("name-"));
        userTwo.setRole(randomFromCollection(Arrays.asList(UserRole.values())));
        return userTwo;
    }
}
