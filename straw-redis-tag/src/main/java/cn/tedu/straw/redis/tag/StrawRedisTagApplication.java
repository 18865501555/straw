package cn.tedu.straw.redis.tag;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.tedu.straw.redis.tag.mapper")
public class StrawRedisTagApplication {

	public static void main(String[] args) {
		SpringApplication.run(StrawRedisTagApplication.class, args);
	}

}
