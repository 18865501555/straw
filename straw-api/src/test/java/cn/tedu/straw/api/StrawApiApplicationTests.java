package cn.tedu.straw.api;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
}
