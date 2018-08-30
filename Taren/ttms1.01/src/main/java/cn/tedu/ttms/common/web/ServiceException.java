package cn.tedu.ttms.common.web;
/**为什么要自己写异常
 * 对系统中的一些业务进行更加明确，详细的定位
 * */
public class ServiceException  extends RuntimeException{

	private static final long serialVersionUID = 1714619499482057222L;

	public ServiceException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ServiceException(String message, 
			Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
   /*
    * 
    * */
	public ServiceException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public ServiceException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
}
