package com.framework.infrastructure.persistence.hibernate.listener;

import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.event.DeleteEvent;
import org.hibernate.event.DeleteEventListener;
import org.hibernate.event.LoadEvent;
import org.hibernate.event.LoadEventListener;
import org.hibernate.event.PostDeleteEvent;
import org.hibernate.event.PostDeleteEventListener;
import org.hibernate.event.PostUpdateEvent;
import org.hibernate.event.PostUpdateEventListener;
import org.hibernate.event.SaveOrUpdateEvent;
import org.hibernate.event.SaveOrUpdateEventListener;
import org.springframework.stereotype.Component;

/**
 * <pre>
 * 参考com.family168History.EventListener, 在对象的update,delete,post-update等事件中加入AOP特性.
 * 它类似于数据库中的trigger，可以拦截到对象的生命周期事件，而且能拿到诸如update前后的数据，传统的方式需要以方法执行前后为Point Cut使用AOP拦截，而数据库trigger则需要每个表写一次trigger，难于管理，
 * 达不到Event Listener批量处理的效果。
 * Event Listener在批量处理时，一般设计让处理方法相同的POJO实现同一个接口，然后就可以在Event Listener里就以if(pojo instance of MyEventInterface)来区分处理
 * @author chenwentao
 *
 * @version 0.9
 *
 * 修改版本: 0.9
 * 修改日期: Dec 4, 2010
 * 修改人 :  chenwentao
 * 修改说明: 初步完成
 * 复审人 ：
 * </pre>
 */
@Component
public class EventListener implements DeleteEventListener,
		SaveOrUpdateEventListener, PostUpdateEventListener,
		PostDeleteEventListener, LoadEventListener {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -7813489467101401364L;

	@Override
	public void onSaveOrUpdate(SaveOrUpdateEvent event)
			throws HibernateException {
		Object object = event.getObject();
		System.out.println(object);
	}

	@Override
	public void onDelete(DeleteEvent event) throws HibernateException {

	}

	@Override
	@SuppressWarnings("unchecked")
	public void onDelete(DeleteEvent event, Set transientEntities)
			throws HibernateException {
	}

	@Override
	public void onPostUpdate(PostUpdateEvent event) {
	}

	@Override
	public void onPostDelete(PostDeleteEvent event) {
	}

	@Override
	public void onLoad(LoadEvent event, LoadType loadType)
			throws HibernateException {

	}
}