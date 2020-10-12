package cn.tedu.straw.api;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
class StrawApiApplicationTests {

    @Autowired
    DataSource dataSource;

    @Test
    void contextLoads() {
    }

    @Test
    void getConnection() throws SQLException {
        System.err.println(dataSource.getConnection());
    }

    @Test
    void md5Tests() {
        String password = "1234";
        String encodePassword = DigestUtils.md5Hex(password);
        System.err.println("[md5] encode password=" + encodePassword);

        encodePassword = DigestUtils.sha256Hex(password);
        System.err.println("[sha-256] encode password=" + encodePassword);

        encodePassword = DigestUtils.sha384Hex(password);
        System.err.println("[sha-384] encode password=" + encodePassword);

        encodePassword = DigestUtils.sha512Hex(password);
        System.err.println("[sha-512] encode password=" + encodePassword);
    }
    @Test
    void bcryptTests(){
        String password = "1234";
        for (int i = 0; i < 10; i++) {
            // 即使对于同一个原文可以运算出N个不同的密文
            String encodePassword = new BCryptPasswordEncoder().encode(password);
            System.err.println("[bcrypt] encode password=" + encodePassword);
        }
    }
}
