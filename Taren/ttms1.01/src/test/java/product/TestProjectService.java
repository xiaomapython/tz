package product;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;

import cn.tedu.ttms.product.entity.Project;
import cn.tedu.ttms.product.service.ProjectService;

public class TestProjectService {
	ClassPathXmlApplicationContext ctx;
	@Before
	public void init(){
		ctx=new ClassPathXmlApplicationContext("spring-pool.xml",
				"spring-mybatis.xml","spring-mvc.xml");
	}
	
	@Test
    public void test1(){
		//获得Service对象(此对象由spring创建)
		ProjectService ps=
				ctx.getBean("projectServiceImpl",ProjectService.class);
		//访问业务层方法
		Map<String,Object> map=ps.findPageObjects("驻马店游",23,0);
		//测试list集合是否不等于null；
		Assert.assertNotEquals(null, map);
	   System.out.println(map);
	}
/*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
	@Test
	//测试启用禁用
	public void test2(){
		ProjectService ps=
				ctx.getBean("projectServiceImpl",ProjectService.class);
		ps.getValidById("1", 0);
	}
/*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
	@Test
	public void test3(){
		ProjectService ps=
				ctx.getBean("projectServiceImpl",ProjectService.class);
		Project p=new Project();
		p.setName("驻马店游");
		p.setCode("2017-09-07");
		Date d=new Date();
		p.setBeginDate(d);
		p.setEndDate(d);
		p.setValid(0);
		p.setCreatedTime(d);
		p.setModifiedTime(d);
		p.setCreatedUser("河马");
		p.setModifiedUser("王五");
		ps.saveObject(p);
	}
	@Test
	public void testFindObjectById(){
		ProjectService ps=
				ctx.getBean("projectServiceImpl",ProjectService.class);
		Project p=ps.findObjectById(1);
		System.out.println(p);
	}
/*------------------------------------------------------------------------------*/
	@Test
	public void test4(){
		ProjectService ps=
				ctx.getBean("projectServiceImpl",ProjectService.class);
		Project entity=ps.findObjectById(8);
		entity.setId(8);
		entity.setName("山海-苏州-昆山游");
		ps.updateObjectById(entity);
	}
	@After
	public void destory(){
		ctx.close();
	}
}
/**
 * 排错：
 * 1）四个W
 *     a)When(何时出的错)
 *     b)What(什么错)
 *     c)where(哪里的错)
 *     d)Why（为什么）
 *  2）How (怎么解决)
 */
