package product;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.coyote.http11.filters.VoidInputFilter;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sun.corba.se.impl.oa.poa.ActiveObjectMap.Key;
import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;
import com.sun.tools.classfile.Opcode.Set;

import cn.tedu.ttms.product.entity.Team;
import cn.tedu.ttms.product.service.TeamService;

public class TestTeamService {
	ClassPathXmlApplicationContext ctx;
	@Before
	public void init(){
		ctx=new ClassPathXmlApplicationContext("spring-pool.xml",
				"spring-mybatis.xml","spring-mvc.xml"
				);
		
	}
/*----------------------------------------------------------------------------------*/
	@Test
	//测试查询所有Team信息
	public void test1(){
		TeamService ts=
				ctx.getBean("teamServiceImpl",TeamService.class);
		Map<String, Object> map=ts.findPageObjects(null,1);
		Assert.assertNotEquals(null, map);
		@SuppressWarnings("unchecked")
		List<Map<String,Object>> list=(List<Map<String,Object>>) map.get("list");
		int a=0;
		for(Map<String, Object> m:list){
			a++;
			System.out.println("第"+a+"个Map"+m);
		    Collection<String> key=m.keySet();
		    int b=0;
		    for(String k:key){
		    	b++;
		    	System.out.println("第"+a+"个Map的第"+b+"个Key为"+k);
		    }
		}
	}
/*-------------------------------------------------------------------------------*/
	@Test
	public void test2(){
		TeamService ts=
				ctx.getBean("teamServiceImpl",TeamService.class);
		Team entity = new Team();
		entity.setName("月球游");
		entity.setProjectId(3);
		entity.setValid(1);
		entity.setNote("月球游6天。。。。。。。。。");
		ts.saveObject(entity);
	}
	
	@After
	public void destory(){
		ctx.close();
	}
}
