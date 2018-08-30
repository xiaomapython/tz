package cn.tedu.ttms.common.exception;
/**借助此类实现控制层统一异常处理
 * 使用@ControllerAdvice注解标识要此类
 * 处理SpringMVC中控制层的异常
 * */

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.ttms.common.web.JsonResult;
import cn.tedu.ttms.common.web.ServiceException;
/*
 * @ControllerAdvice注解为@Controller注解的增强版，一般用于定义
 *  Controller对象的一些全局特性
*/
@ControllerAdvice
@Component
public class ControllerExceptionHandler {
	/**通过@ExceptionHandler
	 *注解声明要借助此方法处理的异常的具体类型
	 * */
	@ExceptionHandler(ServiceException.class)
	@ResponseBody
	public  JsonResult handleServiceException(ServiceException e){
		e.printStackTrace();
		//封装异常，并将异常中的信息返回到客户端
		return new JsonResult(e);
	}
}
