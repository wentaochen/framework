package com.weixin.domain.service.admin;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weixin.domain.model.shop.Member;
import com.weixin.domain.service.common.AbstractService;

@Service("memberAdminService")
@Transactional
public class MemberAdminService extends AbstractService<Member> {

	public List<Member> findAll() {
		return this.find("from Member");
	}

}
