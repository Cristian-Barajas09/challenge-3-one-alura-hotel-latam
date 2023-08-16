package test.usuario;

import org.mindrot.jbcrypt.BCrypt;

public class TestEncryptPassword {
    public static void main(String[] args) {
        String mypassword = "mypassword";
        String gensalt = BCrypt.gensalt(10);

        String newPassword = BCrypt.hashpw(mypassword,gensalt);
        System.out.println(newPassword);

        boolean esIgual =  BCrypt.checkpw(mypassword,newPassword);
        System.out.println(esIgual);
    }

}
