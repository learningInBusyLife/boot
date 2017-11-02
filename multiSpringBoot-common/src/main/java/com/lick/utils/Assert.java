package com.lick.utils;

import com.lick.exception.BizRuntimeException;

/**
 * @Description: 断言类，校验基本数据及表达式
 * @ClassName: Assert
 * @Package: com.lick.utils
 * @Author: lick
 * @Date: 2016年11月6日 下午3:15:01
 * @Copyright: 版权归 lick 所有
 * <ModifyLog>
 * @ModifyContent:
 * @Author:
 * @Date:	
 * </ModifyLog>
 */
public class Assert {

	public static void isTrue(boolean expression, String errorNo, String message) {
		if (!expression) {
			throw new BizRuntimeException(errorNo, message);
		}
	}

	public static void isNull(Object object, String errorNo, String message) {
		if (object != null) {
			throw new BizRuntimeException(errorNo, message);
		}
	}

	public static void notNull(Object object, String errorNo, String message) {
		if (object == null) {
			throw new BizRuntimeException(errorNo, message);
		}
	}

	public static void notEmpty(Object object, String errorNo, String message) {
		if (ValueUtil.isEmpty(object)) {
			throw new BizRuntimeException(errorNo, message);
		}
	}

}
