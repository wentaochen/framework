package com.weixin.domain.service.shop;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weixin.domain.model.shop.Comment;
import com.weixin.domain.model.shop.Member;
import com.weixin.domain.service.common.AbstractService;

@Service("commentService")
@Transactional
public class CommentService extends AbstractService<Comment> {

}
