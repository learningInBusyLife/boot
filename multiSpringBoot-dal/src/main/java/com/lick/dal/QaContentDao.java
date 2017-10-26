package com.lick.dal;

import com.lick.model.entity.QaContent;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @Description：
 * @Author: lick@hsyuntai.com
 * @Date: 2017年10月26日 14:07
 * @Copyright: 版权归hsyuntai 所有
 */
@Repository
public interface QaContentDao {
    /**
     * @Description: 获取数据
     * @Method:getQaContentAll
     * @params:[]
     * @returnType:QaContent
     * @Author:lick@hsyuntai.com
     * @Date: 2017/10/26 14:07
     * @Copyright: 版权归hsyuntai 所有
     * @return
     */
    public QaContent getQaContentAll();
}
