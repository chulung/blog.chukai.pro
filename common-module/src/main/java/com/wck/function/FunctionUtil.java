package com.wck.function;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FunctionUtil {

	/**
	 * 根据集合中某个字段分组并每组某个字段的和
	 * @param list 集合
	 * @param classifier 分组字段，如对象中用户id
	 * @param mapper 求和字段 如对象中用户积分
	 * @return
	 */
	public static <T> Map<Integer, Integer> groupingAndSum(List<T> list,
			Function<? super T, ? extends Integer> classifier, Function<? super T, ? extends Integer> mapper) {
		return list.stream().collect(Collectors.groupingBy(classifier, Collectors.reducing(0, mapper, Integer::sum)));
	}

}
