package com.weixin.domain.service.shop;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weixin.domain.model.shop.Member;
import com.weixin.domain.service.common.AbstractService;

@Service("memberService")
@Transactional
public class MemberService extends AbstractService<Member> {

	public Member login(Member member) {
		return this.findUnique("from Member c where c.mobile=? and c.password=?",
				member.getMobile(),member.getPassword());
	}

	public Member findByOpenid(String fromUserName) {
		return this.findUnique("from Member c where c.openid=?", fromUserName);
	}

}
