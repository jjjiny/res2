package ssm.controller;

import entity.Syslog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import ssm.service.SyslogService;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/*失效*/
public class Logsys2 {
    @Autowired
    private SyslogService syslogService;
    private Date startTime;//开始时间
    private Class clazz;//访问的类
    private Method method;//访问的方法
    private String url;//访问的url
    @Autowired
    private HttpServletRequest request;//需在web.xml配置request监听;

    //前置通知
    public void doBefore(JoinPoint joinPoint) throws NoSuchMethodException {
        startTime = new Date();//开始访问时间
        clazz = joinPoint.getTarget().getClass();//获取访问的类

        //获得具体的method方法对象
        String methodName = joinPoint.getSignature().getName();//获取方法名字
        Object[] methodParameter = joinPoint.getArgs();//获取方法参数
        if (methodParameter == null || methodParameter.length == 0) {
            clazz.getMethod(methodName);//获取无参方法
        } else {
            Class[] classes = new Class[methodParameter.length];
            for (int i = 0; i < methodParameter.length; i++) {
                classes[i] = methodParameter[i].getClass();
            }
            clazz.getMethod(methodName, classes);//传入有参数的方法
        }

    }

    //后置通知
    public void doAfter(JoinPoint joinPoint) throws NoSuchMethodException {
        long endTime = new Date().getTime() - startTime.getTime();//计算时长

        //获得url(利用反射)
        if (clazz != null && method != null && clazz != Logsys2.class) {
            //1.获取类上的@RequestMapping (url)
            RequestMapping clazzAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if (clazzAnnotation != null) {
                String[] clazzPatah = clazzAnnotation.value();
                url = clazzPatah[0];
            }
            //2.获取方法上的@RequestMapping (url)
            RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
            if (methodAnnotation != null) {
                String[] methodPath = methodAnnotation.value();
                url += methodPath[0];

                String ip = request.getRemoteAddr();//获取ip
                String userName = request.getRemoteUser();//获取用户名
                //封装Syslog类
                Syslog syslog = new Syslog();
                syslog.setExecutionTime(endTime);//时长
                syslog.setIp(ip);//ip
                syslog.setMethod("【类名:】" + clazz.getName() + "【方法名:】" + method.getName());//方法
                syslog.setUrl(url);//url
                syslog.setUsername(userName);
                syslog.setVisitTime(startTime);//访问开始时间

                syslogService.save(syslog);
            }
        }
    }
}
