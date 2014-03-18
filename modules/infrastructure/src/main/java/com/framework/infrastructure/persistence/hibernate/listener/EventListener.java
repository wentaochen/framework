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
 * �ο�com.family168History.EventListener, �ڶ����update,delete,post-update���¼��м���AOP����.
 * �����������ݿ��е�trigger���������ص���������������¼����������õ�����updateǰ������ݣ���ͳ�ķ�ʽ��Ҫ�Է���ִ��ǰ��ΪPoint Cutʹ��AOP���أ������ݿ�trigger����Ҫÿ����дһ��trigger�����ڹ���
 * �ﲻ��Event Listener���������Ч����
 * Event Listener����������ʱ��һ������ô�������ͬ��POJOʵ��ͬһ���ӿڣ�Ȼ��Ϳ�����Event Listener�����if(pojo instance of MyEventInterface)�����ִ���
 * @author chenwentao
 *
 * @version 0.9
 *
 * �޸İ汾: 0.9
 * �޸�����: Dec 4, 2010
 * �޸��� :  chenwentao
 * �޸�˵��: �������
 * ������ ��
 * </pre>
 */
@Component
public class EventListener implements DeleteEventListener,
		SaveOrUpdateEventListener, PostUpdateEventListener,
		PostDeleteEventListener, LoadEventListener {

	/**
	 * ���к�
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