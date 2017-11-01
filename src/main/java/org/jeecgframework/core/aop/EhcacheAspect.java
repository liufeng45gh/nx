//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.core.aop;

import com.alibaba.fastjson.JSON;
import java.io.Serializable;
import java.lang.reflect.Method;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.jeecgframework.core.annotation.Ehcache;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class EhcacheAspect {
    private static Cache dictCache;
    private static Cache eternalCache;

    static {
        if(eternalCache == null) {
            eternalCache = CacheManager.getInstance().getCache("eternalCache");
        }

        if(dictCache == null) {
            dictCache = CacheManager.getInstance().getCache("dictCache");
        }

    }

    public EhcacheAspect() {
    }

    @Pointcut("@annotation(org.jeecgframework.core.annotation.Ehcache)")
    public void simplePointcut() {
    }

    @AfterReturning(
            pointcut = "simplePointcut()"
    )
    public void simpleAdvice() {
    }

    @Around("simplePointcut()")
    public Object aroundLogCalls(ProceedingJoinPoint joinPoint) throws Throwable {
        String targetName = joinPoint.getTarget().getClass().toString();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Method[] methods = joinPoint.getTarget().getClass().getMethods();
        Ehcache flag = null;
        Method[] var10 = methods;
        int var9 = methods.length;

        for(int var8 = 0; var8 < var9; ++var8) {
            Method m = var10[var8];
            if(m.getName().equals(methodName)) {
                Class[] tmpCs = m.getParameterTypes();
                if(tmpCs.length == arguments.length) {
                    flag = (Ehcache)m.getAnnotation(Ehcache.class);
                    break;
                }
            }
        }

        if(flag == null) {
            return null;
        } else {
            String cacheKey = this.getCacheKey(targetName, methodName, arguments);
            Element element = null;
            if(flag.eternal()) {
                element = dictCache.get(cacheKey);
            } else {
                element = eternalCache.get(cacheKey);
            }

            if(element == null) {
                Object result;
                if(arguments != null && arguments.length != 0) {
                    result = joinPoint.proceed(arguments);
                } else {
                    result = joinPoint.proceed();
                }

                element = new Element(cacheKey, (Serializable)result);
                if(flag.eternal()) {
                    dictCache.put(element);
                } else {
                    eternalCache.put(element);
                }
            }

            return element.getValue();
        }
    }

    private String getCacheKey(String targetName, String methodName, Object[] arguments) {
        StringBuffer sb = new StringBuffer();
        sb.append(targetName).append(".").append(methodName);
        if(arguments != null && arguments.length != 0) {
            for(int i = 0; i < arguments.length; ++i) {
                sb.append(".").append(JSON.toJSONString(arguments[i]));
            }
        }

        return sb.toString();
    }
}
