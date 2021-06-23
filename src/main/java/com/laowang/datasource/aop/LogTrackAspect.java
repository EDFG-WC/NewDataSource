package com.laowang.datasource.aop;

import com.alibaba.fastjson.JSON;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @Auther: wangc
 * @Date: 2021/6/2 13:29
 * @Description:
 */
@Component
@Aspect
public class LogTrackAspect  {
	private static final Logger log = LoggerFactory.getLogger(LogTrackAspect.class);



	//这里需要注意了，这个是将自己自定义注解作为切点的根据，路径一定要写正确了
	@Pointcut(value = "@annotation(com.laowang.datasource.aop.LogTrack)")
	public void access() {

	}

	//进来切点世界，先经过的第一个站
	@Before("access()")
	public void doBefore(JoinPoint joinPoint) throws Throwable {


		System.out.println("-aop 日志记录启动-" + new Date());


	}


	//环绕增强，是在before前就会触发
	@Around("@annotation(logTrack)")
	public Object around(ProceedingJoinPoint pjp, LogTrack logTrack) throws Throwable {
		System.out.println("-aop 日志环绕阶段-" + new Date());
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();

//        GET 请求其实可以从request里获取出参数
//       Map<String,String[]> map=request.getParameterMap();
//        System.out.println("获取参数："+map.get("username")[0])

		String url = request.getRequestURL().toString();
		String ip = IpUtil.getIpAddr(request);
		String logTrackValue = logTrack.value();
		Object[] pipArray = pjp.getArgs();
		if (pipArray.length>1){ //多参,不是Map/JsonObject方式
			List<Object> argList = new ArrayList<>();
			for (Object arg : pjp.getArgs()) {
				// request/response无法使用toJSON
				if (arg instanceof HttpServletRequest) {
					argList.add("request");
				} else if (arg instanceof HttpServletResponse) {
					argList.add("response");
				} else {
					argList.add(JSON.toJSON(arg));
				}
			}
			Signature signature = pjp.getSignature();
			MethodSignature methodSignature = (MethodSignature) signature;
			// 参数名数组
			String[] parameterNames = ((MethodSignature) signature).getParameterNames();
			System.out.println("参数名数组："+new ArrayList(Arrays.asList(parameterNames)));
			System.out.println("参数是："+argList.toString());
			System.out.println("logTrackValue:"+logTrackValue);
			System.out.println("url:"+url);
			System.out.println("ip:"+ip);
			return pjp.proceed();

		}

		Object param =  pipArray[0];
		System.out.println("logTrackValue:"+logTrackValue);
		System.out.println("url:"+url);
		System.out.println("ip:"+ip);
		System.out.println("param:"+param.toString());
		return pjp.proceed();

	}


	//进来切点这，最后经过的一个站，也是方法正常运行结束后
	@After("access()")
	public void after(JoinPoint joinPoint) {
		System.out.println("-aop 日志记录结束-" + new Date());
	}

}
