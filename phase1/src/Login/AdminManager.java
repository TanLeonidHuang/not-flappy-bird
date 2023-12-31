package Login;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminManager extends AccountSystem {

    private final static Map<UserAccount, LocalDateTime> bannedAccounts = new HashMap<>();

    public void promoteAdminUser(String username) {
        AccountSystem system = new AccountSystem();
        for (UserAccount user: AccountSystem.allUsers) {
            if(user.getUsername().equals(username)) {
                String password = user.getPassword();
                deleteUser(username);
                system.createUser(username, password,true);
                return;
            }
        }
    }

    public void temporaryBan(String username, LocalDateTime banLength) {
        List<UserAccount> userToBan = new ArrayList<>();
        for (UserAccount user: AccountSystem.allUsers) {
            if (user.getUsername().equals(username)) {
                userToBan.add(user);
                bannedAccounts.put(user, banLength);
            }
        }
        if (userToBan.size() == 0) {
            return;
        }
        AccountSystem.allUsers.remove(userToBan.get(0));
    }

    public void unbanAccount(String username, LocalDateTime banLength) {
        List<UserAccount> userUnban = new ArrayList<>();
        for (UserAccount user: bannedAccounts.keySet()) {
            if (user.getUsername().equals(username) && LocalDateTime.now().isAfter(banLength)) {
                userUnban.add(user);
                AccountSystem.allUsers.add(user);
            }
        }
        if (userUnban.size() == 0) {
            return;
        }
        bannedAccounts.remove(userUnban.get(0));
    }

    public void deleteUser(String username){
        AccountSystem.allUsers.removeIf(user -> user.getUsername().equals(username));
        CreateUserAccount.allCreatedUsers.removeIf((user -> user.getUsername().equals(username)));
    }

    public void createNewAdmin(String username, String password){
        AccountSystem system = new AccountSystem();
        system.createUser(username, password, true);
    }

}
