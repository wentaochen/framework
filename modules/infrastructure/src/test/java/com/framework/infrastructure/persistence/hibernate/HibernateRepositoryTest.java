package com.framework.infrastructure.persistence.hibernate;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.hibernate.CacheMode;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Hibernate;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;

import com.framework.infrastructure.persistence.Page;
import com.framework.infrastructure.persistence.PropertyFilter;
import com.framework.infrastructure.persistence.hibernate.data.User;
import com.framework.infrastructure.test.spring.SpringTxTestCase;
import com.framework.infrastructure.test.utils.DbUnitUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@ContextConfiguration(locations = { "/applicationContext-core-test.xml" })
public class HibernateRepositoryTest extends SpringTxTestCase {

	private static final String DEFAULT_LOGIN_NAME = "admin";

	@Inject
	private HibernateRepository repository;

	final String NAME = "chen@mail.com";

	@Before
	public void setUp() throws Exception {
		DbUnitUtils.loadData((DataSource) applicationContext.getBean("dataSource"), "classpath:/test-data.xml");
	}

	@Test
	@Rollback(false)
	public void sqlInsert() {

		List<User> userList = repository.findBySQLSimple(User.class, "select * from SS_USER");
		for (User user : userList) {
			System.out.println(user);
		}

		/*	System.out.println(repository.executeSql("update SS_USER set name=?", NAME));
				repository.flush();

				userList = repository.findBySQLSimple(User.class, "select * from SS_USER");
				for (User user : userList) {
					assertEquals(user.getName(), NAME);
				}
		*/
	}

	@Test
	public void crud() {
		// save
		User user = new User();
		user.setName("foo");
		user.setLoginName("foo");
		repository.save(user);
		repository.flush();

		// update
		user.setName("boo");
		repository.update(user);
		repository.flush();

		// delete object
		repository.delete(user);
		repository.flush();

		// delete by id
		User user2 = new User();
		user2.setName("foo2");
		user2.setLoginName("foo2");
		repository.save(user2);
		repository.flush();

		repository.delete(User.class, user2.getId());
		repository.flush();
	}

	@Test
	public void getSome() {
		// get all
		List<User> users = repository.findAll(User.class);
		assertEquals(6, users.size());

		// get all with order
		users = repository.findAll(User.class, "id", true);
		assertEquals(6, users.size());
		assertEquals(DEFAULT_LOGIN_NAME, users.get(0).getLoginName());

		// get by id list
		users = repository.get(Lists.newArrayList(1L, 2L), User.class);
		assertEquals(2, users.size());
	}

	@Test
	public void findByProperty() {
		List<User> users = repository.findBy(User.class, "loginName", DEFAULT_LOGIN_NAME);
		assertEquals(1, users.size());
		assertEquals(DEFAULT_LOGIN_NAME, users.get(0).getLoginName());

		User user = repository.findUniqueBy(User.class, "loginName", DEFAULT_LOGIN_NAME);
		assertEquals(DEFAULT_LOGIN_NAME, user.getLoginName());
	}

	@Test
	public void findByHQL() {

		List<User> users = repository.find("from User u where loginName=?", DEFAULT_LOGIN_NAME);
		assertEquals(1, users.size());
		assertEquals(DEFAULT_LOGIN_NAME, users.get(0).getLoginName());

		User user = repository.findUnique("from User u where loginName=?", DEFAULT_LOGIN_NAME);
		assertEquals(DEFAULT_LOGIN_NAME, user.getLoginName());

		Map<String, Object> values = Maps.newHashMap();
		values.put("loginName", DEFAULT_LOGIN_NAME);
		users = repository.find("from User u where loginName=:loginName", values);
		assertEquals(1, users.size());
		assertEquals(DEFAULT_LOGIN_NAME, users.get(0).getLoginName());

		user = repository.findUnique("from User u where loginName=:loginName", values);
		assertEquals(DEFAULT_LOGIN_NAME, user.getLoginName());
	}

	@Test
	public void findByCriterion() {
		Criterion c = Restrictions.eq("loginName", DEFAULT_LOGIN_NAME);
		List<User> users = repository.find(User.class, c);
		assertEquals(1, users.size());
		assertEquals(DEFAULT_LOGIN_NAME, users.get(0).getLoginName());

		User user = repository.findUnique(User.class, c);
		assertEquals(DEFAULT_LOGIN_NAME, user.getLoginName());
	}

	@Test
	@SuppressWarnings("unchecked")
	public void batchUpdate() {
		Map map = new HashMap();
		map.put("ids", new Long[] { 1L, 23L });

		repository.batchExecute("update User u set u.status='disabled' where id in(:ids)", map);
		User u1 = repository.get(User.class, 1L);
		assertEquals("disabled", u1.getStatus());
		User u3 = repository.get(User.class, 3L);
		assertEquals("enabled", u3.getStatus());
	}

	@Test
	@SuppressWarnings("unchecked")
	public void eagerFetch() {
		String sql = "from User u left join fetch u.roleList order by u.id";

		Query query = repository.createQuery(sql);
		List<User> userList = repository.distinct(query).list();
		assertEquals(6, userList.size());
		assertTrue(Hibernate.isInitialized(userList.get(0).getRoleList()));

		Criteria criteria = repository.createCriteria(User.class).setFetchMode("roles", FetchMode.JOIN);
		userList = repository.distinct(criteria).list();
		assertEquals(6, userList.size());
		assertTrue(Hibernate.isInitialized(userList.get(0).getRoleList()));
	}

	@Test
	public void misc() {
		getIdName();
		isPropertyUnique();
	}

	@Test
	public void findPageByHqlAutoCount() {
		Page<User> page = new Page<User>(5);
		repository.findPage(page, "from User user");
		assertEquals(6L, page.getTotalCount());

		repository.findPage(page, "select user from User user");
		assertEquals(6L, page.getTotalCount());

		repository.findPage(page, "select user from User user order by id");
		assertEquals(6L, page.getTotalCount());
	}

	public void getIdName() {
		assertEquals("id", repository.getIdName(User.class));
	}

	public void isPropertyUnique() {
		assertEquals(true, repository.isPropertyUnique(User.class, "loginName", "admin", "admin"));
		assertEquals(true, repository.isPropertyUnique(User.class, "loginName", "user6", "admin"));
		assertEquals(false, repository.isPropertyUnique(User.class, "loginName", "user2", "admin"));
	}

	// ################################################################################

	@Test
	public void findByHQLPage() {
		// 初始化数据中共有6个email为@springside.org.cn的用户
		Page<User> page = new Page<User>(5);
		repository.findPage(page, "from User u where email like ?", "%springside.org.cn%");
		assertEquals(5, page.getResult().size());

		// 自动统计总数
		assertEquals(6L, page.getTotalCount());

		// 翻页
		page.setPageNo(2);
		repository.findPage(page, "from User u where email like ?", "%springside.org.cn%");
		assertEquals(1, page.getResult().size());

		// 命名参数版本
		Map<String, String> paraMap = Collections.singletonMap("email", "%springside.org.cn%");
		page = new Page<User>(5);
		repository.findPage(page, "from User u where email like :email", paraMap);
		assertEquals(5, page.getResult().size());

		// 自动统计总数
		assertEquals(6L, page.getTotalCount());

		// 翻页
		page.setPageNo(2);
		repository.findPage(page, "from User u where email like :email", paraMap);
		assertEquals(1, page.getResult().size());
	}

	@Test
	public void findByCriterionPage() {
		// 初始化数据中共有6个email为@springside.org.cn的用户
		Page<User> page = new Page<User>(5);
		Criterion c = Restrictions.like("email", "springside.org.cn", MatchMode.ANYWHERE);
		repository.findPage(User.class, page, c);
		assertEquals(5, page.getResult().size());

		// 自动统计总数
		assertEquals(6L, page.getTotalCount());

		// 翻页
		page.setPageNo(2);
		repository.findPage(User.class, page, c);
		assertEquals(1, page.getResult().size());
	}

	@Test
	public void findByCriterionWithOrder() {
		// 初始化数据中共有6个email为@springside.org.cn的用户
		Page<User> page = new Page<User>(5);
		page.setOrderBy("name,loginName");
		page.setOrder(Page.DESC + "," + Page.ASC);

		Criterion c = Restrictions.like("email", "springside.org.cn", MatchMode.ANYWHERE);
		repository.findPage(User.class, page, c);

		assertEquals("Sawyer", page.getResult().get(0).getName());
	}

	@Test
	public void findByFilters() {
		List<PropertyFilter> filters;
		// EQ filter
		PropertyFilter eqFilter = new PropertyFilter("EQS_loginName", "admin");
		filters = Lists.newArrayList(eqFilter);

		List<User> users = repository.find(User.class, filters);
		assertEquals(1, users.size());
		assertEquals("admin", users.get(0).getLoginName());

		// LIKE filter and OR
		PropertyFilter likeAndOrFilter = new PropertyFilter("LIKES_email_OR_loginName", "springside.org.cn");
		filters = Lists.newArrayList(likeAndOrFilter);

		users = repository.find(User.class, filters);
		assertEquals(6, users.size());
		assertTrue(StringUtils.contains(users.get(0).getEmail(), "springside.org.cn"));

		// Filter with Page
		Page<User> page = new Page<User>(5);
		repository.findPage(User.class, page, filters);
		assertEquals(5, page.getResult().size());
		assertEquals(6L, page.getTotalCount());

		page.setPageNo(2);
		repository.findPage(User.class, page, filters);
		assertEquals(1, page.getResult().size());

		// Date and LT/GT filter
		PropertyFilter dateLtFilter = new PropertyFilter("LTD_createTime", "2046-01-01");
		filters = Lists.newArrayList(dateLtFilter);
		users = repository.find(User.class, filters);
		assertEquals(6, users.size());

		PropertyFilter dateGtFilter = new PropertyFilter("GTD_createTime", "2046-01-01 10:00:22");
		filters = Lists.newArrayList(dateGtFilter);
		users = repository.find(User.class, filters);
		assertEquals(0, users.size());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void buildQuery() {
		List<User> users = repository.buildQuery("from User u where loginName=?", DEFAULT_LOGIN_NAME)
				.lock(LockOptions.UPGRADE).setQueryCache(false).setCacheRegion(null).setCahceMode(CacheMode.NORMAL)
				.build().list();

		assertEquals(1, users.size());
		assertEquals(DEFAULT_LOGIN_NAME, users.get(0).getLoginName());
	}

}
