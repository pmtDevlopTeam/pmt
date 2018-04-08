package com.camelot.pmt.platform.redis;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;


/**
 * redis实现共享session
 * date 2017/3/22 0022 下午 15:32
 */
@Repository("redisSessionDAO")
public class RedisSessionDAO extends EnterpriseCacheSessionDAO {
	
	
	private static Logger logger = LoggerFactory.getLogger(RedisSessionDAO.class);

    // session 在redis过期时间是30分钟30*60
    private static int expireTime = 30;

    private static String prefix = "dmp-shiro-session:";
    
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    
    private String getKey(String originalKey) {
        return prefix + originalKey;
    }

    // 创建session，保存到数据库
    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = super.doCreate(session);
        logger.debug("createSession:{}", session.getId().toString());
        redisTemplate.opsForValue().set(getKey(session.getId().toString()), session,expireTime,TimeUnit.MINUTES);
        return sessionId;
    }

    // 获取session
    @Override
    protected Session doReadSession(Serializable sessionId) {
        logger.debug("readSession:{}", sessionId.toString());
        // 先从缓存中获取session，如果没有再去数据库中获取
        Session session = super.doReadSession(sessionId);
        if(session == null){
            session = (Session) redisTemplate.opsForValue().get(getKey(sessionId.toString()));
        }
        return session;
    }

    // 更新session的最后一次访问时间
    @Override
    public void update(Session session) {
        logger.debug("updateSession:{}", session.getId().toString());
        super.doUpdate(session);
        String key = getKey(session.getId().toString());
        redisTemplate.opsForValue().set(key, session,expireTime, TimeUnit.MINUTES);
    }

    // 删除session
    @Override
    public void delete(Session session) {
        logger.debug("delSession:{}", session.getId());
        redisTemplate.delete(getKey(session.getId().toString()));
        super.doDelete(session);
    }

    @Override
    public Collection<Session> getActiveSessions() {
        logger.debug("activeSession");
        return Collections.emptySet();
    }
}
