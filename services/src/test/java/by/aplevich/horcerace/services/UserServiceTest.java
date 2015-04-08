package by.aplevich.horcerace.services;

import by.aplevich.horcerace.AbstractServiceTest;
import by.aplevich.horcerace.datamodel.UserAccount;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.persistence.PersistenceException;

public class UserServiceTest extends AbstractServiceTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceTest.class);

    @Inject
    private UserService userService;

    @Before
    public void cleanUpData() {
        userService.deleteAll();
    }

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

    @Test
    public void updateUserTest() {
        final UserAccount user = createUser();
        userService.createNewUser(user);

        final UserAccount createdUserAccount = userService.get(user.getId());
        createdUserAccount.setName("NewUserName");
        userService.updateUser(createdUserAccount);
        final UserAccount createdUserAccountFromDB = userService.get(createdUserAccount.getId());
        Assert.assertEquals(createdUserAccountFromDB.getName(), createdUserAccount.getName());

        userService.deteteUser(createdUserAccountFromDB.getId());
        Assert.assertNull(userService.get(createdUserAccountFromDB.getId()));

    }
}