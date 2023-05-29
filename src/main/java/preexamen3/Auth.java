package preexamen3;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Auth {

    public Auth() {

    }

    public String auth(String user, String pwd) {
        if(!ValidacionAuth.isAuthValid(user, pwd)) {
            return "INCORRECT " + user + " AND " + pwd;
        } else {
            return "PERMISSION ROLE : "+ RolesAuth.getRoles(user, pwd) + " - " + new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(new Date());
        }
    }

}
