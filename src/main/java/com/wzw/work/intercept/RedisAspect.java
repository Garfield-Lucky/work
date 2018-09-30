package com.wzw.work.intercept;

import com.wzw.work.util.redis.RedisHandle;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * AOP实现Redis缓存处理
 */
@Component
@Aspect
public class RedisAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisAspect.class);
    //设置默认缓存时间30分钟
    private static final long DEFAULT_EXPIRE_TIME = 1800L;
    @Resource
    RedisHandle rs;
    /**
     * 拦截所有元注解RedisCache注解的方法
     */
    @Pointcut("@annotation(com.wzw.work.intercept.RedisCache)")
    public void pointcutMethod(){

    }

    /**
     * 环绕处理，先从Redis里获取缓存,查询不到，就查询MySQL数据库，
     * 然后再保存到Redis缓存里
     * @param joinPoint
     * @return
     */
    @Around("pointcutMethod()")
    public Object around(ProceedingJoinPoint joinPoint){
        // 获取RedisCache注解
        RedisCache redisCache = ((MethodSignature)joinPoint.getSignature()).getMethod().getAnnotation(RedisCache.class);
        System.out.println("redisCache key:"+redisCache.key()+" expired:"+redisCache.expired());
        //前置：从Redis里获取缓存
        //先获取目标方法参数
        long startTime = System.currentTimeMillis();
        String applId = null;
        Object[] args = joinPoint.getArgs();
        if (args != null && args.length > 0) {
            applId = String.valueOf(args[0]);
        }

        //获取目标方法所在类
        String target = joinPoint.getTarget().toString();
        String className = target.split("@")[0];

        //获取目标方法的方法名称
        String methodName = joinPoint.getSignature().getName();

        //redis中key格式：    applId:方法名称
        String redisKey = redisCache.key().equals("") ? applId + ":" + className + "." + methodName : redisCache.key();

        Object obj = rs.get(redisKey);

        if(obj!=null){
            LOGGER.info("**********从Redis中查到了数据**********");
            LOGGER.info("Redis的KEY值:"+redisKey);
            LOGGER.info("REDIS的VALUE值:"+obj.toString());
            return obj;
        }
        long endTime = System.currentTimeMillis();
        LOGGER.info("Redis缓存AOP处理所用时间:"+(endTime-startTime));
        LOGGER.info("**********没有从Redis查到数据**********");
        try{
            obj = joinPoint.proceed();
        }catch(Throwable e){
            e.printStackTrace();
        }
        LOGGER.info("**********开始从MySQL查询数据**********");
        //后置：将数据库查到的数据保存到Redis
        //如果注解里设置了过期时间，则用注解的过期时间
        if(redisCache.expired()>0){
            rs.set(redisKey, obj, redisCache.expired());
        }else {
            rs.set(redisKey, obj, DEFAULT_EXPIRE_TIME);
        }
        LOGGER.info("**********数据成功保存到Redis缓存!!!**********");
        LOGGER.info("Redis的KEY值:"+redisKey);
        LOGGER.info("REDIS的VALUE值:"+obj.toString());
        return obj;
    }


}
