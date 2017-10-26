package com.lick.service.impl;

import com.lick.dal.QaContentDao;
import com.lick.model.entity.QaContent;
import com.lick.service.QaContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description：
 * @Author: lick@hsyuntai.com
 * @Date: 2017年10月26日 17:10
 * @Copyright: 版权归hsyuntai 所有
 */
@Service
public class QaContentServiceImpl implements QaContentService {
    @Autowired
    private QaContentDao qaContentDao;

   @Override
    public QaContent getQaContentAll() {
        return qaContentDao.getQaContentAll();
    }
}
