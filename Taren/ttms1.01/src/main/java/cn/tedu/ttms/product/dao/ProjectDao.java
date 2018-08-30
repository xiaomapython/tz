package cn.tedu.ttms.product.dao;
/*
 * 项目模块的数据访问对象
 * 1)负责数据的持久化
 * 2)负责数据的查询然后执行映射操作
 */

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.ttms.common.dao.BaseDao;
import cn.tedu.ttms.common.vo.IdName;
import cn.tedu.ttms.product.entity.Project;

public interface ProjectDao extends BaseDao<Project>{
	/**
	 * 低昂一此方法用于实现分页查询操作
	 * 1）先查询所有数据
	 * 2）重构方法实现分页查询
	 * 3）重构方法实现条件查询
	 * @return
	 */
	/*
	 * startIndex :从startIndex+1的位置开始
	 * pageSize :每页显示多少条记录
	 * 当一个方法中有多个参数时,请使用@Param注解
	 * 进行声明,在mapper文件中使用参数时需要与
	 * @Param注解定义的名字保持一致。
	 */
	List<Project> findPageObjects(
			@Param("name") String name,
			@Param("valid") Integer valid,
			@Param("startIndex") Integer  startIndex,
			@Param("pageSize") Integer pageSize);
	//通过此方法获取记录总数
	/**通过此方法获取记录总数，根据条件进行统计*/
	int getRowCount(
			@Param("name") String name,
			@Param("valid") Integer valid);
	/**通过此方法实现禁用启用操作
	 * @Param("checkedIds")表示要禁用的对象的id
	 * @Param("valid") valid 表示状态（1,0）
	 * */
	int validById(@Param("ids") String[] ids,
			                      @Param("valid") Integer valid);
/*4........................................................................................*/
	/**通过此方法实现数据的插入操作
	 * @param entity 通过此对象封装了要写入的行数
	 * @return 表示写入数据的函数
	 * */
	//int insertObject(Project entity);
/*------------------------------------------------------------------------------*/
	Project findObjectById(@Param("id") Integer id);
/*-----------------------------------------------------------------------------*/
	int updateObjectById(Project entity);
/*-----------------------------------------------------------------------------*/
	/**获得项目的id和name*/
	List<IdName> findIdAndNames();
	//....
}
/**
 * FAQ?
 * 1.ssm架构中这个Dao对象的实现方式有哪些方式？
 *     1）自己实现此dao实现类
 *     2）在配置文件中通过配置由底层自动创建dao的实现类。
 *     例如借助MapperScannerConfigurer对象扫描对应中的实现类
 * 2.ssm架构中这个dao对象与具体mapper是如何绑定的？
 *      1）命名空间的绑定
 *      2）方法与元素id的绑定
 *      3）方法参数与具体SQL的绑定
 *      4）方法的返回值的匹配
 */
