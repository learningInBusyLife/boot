package com.lick.utils;

import com.alibaba.fastjson.JSON;
import com.lick.exception.BizRuntimeException;
import com.lick.log.LoggerUtil;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: bean相关工具类
 * @ClassName: BeanConvertUtils
 * @Author: lick
 * @Date: 2016年11月11日 下午1:03:30
 * @Copyright: 版权归 lick 所有
 * <ModifyLog>
 * @ModifyContent:
 * @Author:
 * @Date:	
 * </ModifyLog>
 */
public class BeanConvertUtil {

	private static final Logger logger = LoggerFactory.getLogger(BeanConvertUtil.class);

	/**
	 * @Description: 将 T 类型的对象数据转换成 V 类型数据
	 * @Param:
	 * @Return:
	 * @Author: qiufeng@hsyuntai.com
	 * @Date: 2017/3/27 16:18
	 */
	public static <T, V> V convert(T t, Class<V> clazz) {
		if (t == null) {
			return null;
		}
		try {
			V v = clazz.newInstance();
			BeanUtils.copyProperties(t, v);
			return v;
		} catch (Exception e) {
			LoggerUtil.info(logger, "【core-BeanConvertUtil-convert】, {0}, t:{1}",
					MessageFormat.format("类型{0}转换成类型{1}异常", t.getClass(), clazz), JSON.toJSONString(t));
			throw new BizRuntimeException("110001",
					MessageFormat.format("类型{0}转换成类型{1}异常", t.getClass(), clazz), e);
		}
	}

	/**
	 * @Description: 将 T 类型的数据对象数组转换成 V 类型对象的数据数组
	 * @Param:
	 * @Return:
	 * @Author: qiufeng@hsyuntai.com
	 * @Date: 2017/3/27 16:19
	 */
	public static <T, V> List<V> convertList(List<T> tList, Class<V> clazz) {
		if(CollectionUtils.isEmpty(tList)) {
			return new ArrayList<V>(0);
		}
		List<V> vList = new ArrayList<V>(tList.size());
		T t = null;
		for(int i = 0; i < tList.size(); i ++) {
			t = tList.get(i);
			vList.add(convert(t, clazz));
		}
		return vList;
	}
}
