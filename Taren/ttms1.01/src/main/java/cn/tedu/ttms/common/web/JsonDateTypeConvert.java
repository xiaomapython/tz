package cn.tedu.ttms.common.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
/*
 * Spring-mvc默认会将日期类型转换为长整形，当我们
 * 需要按照一种规定格式显示日期时，需要定义这样一个
 * 类,让其继承JsonSerializer然后对日期进行格式化输出
 * 
 */
public class JsonDateTypeConvert extends JsonSerializer<Date>{
    /**
     * 此方法用于将具体的Date对象序列化为一种特定的日期格式
     * arg0 表示要转化的具体日期对象
     * arg1 表示一个转换器对象
     */
	@Override
	public void serialize(Date arg0, 
			JsonGenerator arg1, 
			SerializerProvider arg2)
			throws IOException, JsonProcessingException {
		//1.定义日期转换器对象
		SimpleDateFormat sdf=
				new SimpleDateFormat("yyyy/MM/dd");
		//2.转换日期为指定格式的字符串
		String dateStr=sdf.format(arg0);
		//3.以JSON格式输出字符串
		arg1.writeString(dateStr);
		
	}

}
