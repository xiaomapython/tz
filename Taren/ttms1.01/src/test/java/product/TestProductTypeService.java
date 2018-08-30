package product;

import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.ttms.product.service.ProductTypeService;

public class TestProductTypeService {
	private ClassPathXmlApplicationContext ctx;
	private ProductTypeService pts;
	@Before
	public void init(){
		ctx=
				new ClassPathXmlApplicationContext(
						"spring-pool.xml",
						"spring-mybatis.xml",
						"spring-mvc.xml"
						);
		pts=ctx.getBean("productTypeServiceImpl",
				ProductTypeService.class);
	}
	@Test
	public void test1(){
		List<Map<String, Object>> list=pts.findGridTreeNodes();
		Assert.assertNotEquals(null, list);
		System.out.println(list);
	}
	@After
	public void destory(){
		ctx.close();
	}
}
