package cn.tedu.ttms.common.web;
/**
 * 值对象：VO
 * 作用:
 * 1）封装控制层相关方法返回的数据
 * 2）统一服务端相关方法返回的数据格式
 * @author Administrator
 *
 */
public class JsonResult {
			public static final int SUCCESS=1;
			public static final int ERROR=0;
			/**状态:(SUCCESS, ERROR)*/
			private int state;
			/**状态信息*/
			private String msg;
			/**具体数据*/
			private Object data;
			
			public JsonResult(){
				state=SUCCESS;
			}
			public JsonResult(String msg){
				this();
				this.msg=msg;//例如insert ok
			}
			public JsonResult(Object data){
				this();
				this.data=data;
			}
			public JsonResult(Throwable e){
				state=ERROR;
				this.msg=e.getMessage();
			}
			public int getState() {
				return state;
			}
			public String getMsg() {
				return msg;
			}
			public Object getData() {
				return data;
			}
	
}
