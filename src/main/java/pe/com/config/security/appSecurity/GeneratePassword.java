package pe.com.config.security.appSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class GeneratePassword {

	 /**
     * Ejemplo para generar password.
     */
    public static void main(String[] args) {
            String password = "password";
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            System.out.println(passwordEncoder.encode(password));
    }
    
}
