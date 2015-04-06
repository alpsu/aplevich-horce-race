package by.aplevich.horcerace.services;

import by.aplevich.horcerace.AbstractServiceTest;
import by.aplevich.horcerace.datamodel.UserAccount;
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
        UserAccount userOne = createUser();
        userService.createNewUser(userOne);

        final UserAccount createdUserAccount = userService.get(userOne.getId());
        Assert.assertNotNull(createdUserAccount);
        Assert.assertEquals(createdUserAccount.getName(), userOne.getName());
        Assert.assertEquals(createdUserAccount.getLogin(), userOne.getLogin());
        Assert.assertEquals(createdUserAccount.getPassword(), userOne.getPassword());
        Assert.assertEquals(createdUserAccount.getRole(), userOne.getRole());

        userService.deteteUser(userOne.getId());
        Assert.assertNull(userService.get(userOne.getId()));
    }

    @Test
    public void uniqueConstraintsTest() {
        final UserAccount user = createUser();
        final String login = randomString("login");
        user.setLogin(login);
        userService.createNewUser(user);

        final UserAccount duplicateUser = createUser();
        duplicateUser.setLogin(login);
        try {
            userService.createNewUser(duplicateUser);
            Assert.fail("Not unique login can`t be saved ");
        } catch (final PersistenceException e) {

        }

        duplicateUser.setLogin(randomString("login"));
        userService.createNewUser(duplicateUser);
    }
    private UserAccount createUser() {
        final UserAccount userTwo = new UserAccount();
        userTwo.setLogin(randomString("login-"));
        userTwo.setPassword(randomString("pass-"));
        userTwo.setName(randomString("name-"));
        userTwo.setRole(randomFromCollection(Arrays.asList(UserRole.values())));
        return userTwo;
    }
}
