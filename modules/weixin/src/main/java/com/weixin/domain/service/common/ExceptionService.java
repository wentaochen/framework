package com.weixin.domain.service.common;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weixin.domain.model.shop.Member;

@Service("exceptionService")
@Transactional
public class ExceptionService extends AbstractService<Member> {

	public void doSome(String str) {
		throw new RuntimeException("ExceptionService");
	}
}
