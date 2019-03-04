package com.dg.aop;

import com.dg.annotion.Permission;
import com.dg.shiro.service.PermissionCheckService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.naming.NoPermissionException;
import java.lang.reflect.Method;

/**
 * 权限检查的aop
 */
// @Aspect:定义切面
@Aspect
/**
 * @Component：把普通pojo实例化到spring容器中，相当于配置文件中的<bean id="" class=""/>
 */
@Component
@Order(200)
public class PermissionAop {

    @Autowired
    private PermissionCheckService checkService;

    // @Pointcut:定义切点
    // 使用@annotation标志符会检查系统中所有对象的所有方法级别Joinpoint，
    // 如果被检测的方法标注有@annotation标志符所指定的注解类型，那么当前方法所在的Joinpoint将被Pointcut表达式匹配。
    // 连接点：注解类-com.dg.annotion.Permission
    @Pointcut(value = "@annotation(com.dg.annotion.Permission)")
    private void cutPermission() {
    }

    /**
     * @Around：可以同时在所拦截方法的前后执行一段逻辑
     */
    @Around("cutPermission()")
    public Object dpPermission(ProceedingJoinPoint point) throws Throwable {
        // 获取方法
        MethodSignature ms = (MethodSignature) point.getSignature();
        Method method = ms.getMethod();
        // 获取注解
        Permission permission = method.getAnnotation(Permission.class);
        // 获取这个注解的value，获取到的是角色
        Object[] permissions = permission.value();
        if (permissions.length == 0) {
            // 检查全部角色
            boolean result = checkService.checkAll();
            if (result) {
                // 调用目标方法,获取原方法的返回值
                return point.proceed();
            } else {
                throw new NoPermissionException();
            }
        } else {
            // 检查指定角色
            boolean result = checkService.check(permissions);
            if (result) {
                return point.proceed();
            } else {
                throw new NoPermissionException();
            }
        }
    }
}
