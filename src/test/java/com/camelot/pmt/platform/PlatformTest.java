package com.camelot.pmt.platform;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.plugins.Page;
import com.camelot.pmt.platform.model.Menu;
import com.camelot.pmt.platform.model.User;
import com.camelot.pmt.platform.service.MenuService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlatformTest {
	
	@Autowired
	MenuService menuService;
	

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    
	@Autowired
	private RedisTemplate redisTemplate;

    @Test
    public void test() throws Exception {
        stringRedisTemplate.opsForValue().set("aaa", "111");
        Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));
    }
    
    @Test
    public void testObj() throws Exception {
        User user=new User(null, "aa", "aa", "aa", "aa","", null, null, null, null, null, null, null, null, null);
        ValueOperations<String, User> operations=redisTemplate.opsForValue();
        operations.set("com.camelot", user);
        operations.set("com.camelot.p", user,1,TimeUnit.SECONDS);
        Thread.sleep(1000);
        //redisTemplate.delete("com.neo.f");
        boolean exists=redisTemplate.hasKey("com.neo.p");
        if(exists){
        	System.out.println("exists is true");
        }else{
        	System.out.println("exists is false");
        }
       // Assert.assertEquals("aa", operations.get("com.neo.f").getUserName());
    }
	
	
	@Test
	public void MenuTest() {
		Page<Menu> page = new Page<Menu>(0, 3);
		Page<Menu> selectMenuPage = menuService.selectMenuPage(page);
		System.out.println(selectMenuPage);
	}
}
