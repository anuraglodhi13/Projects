package com.nagarro.utilities;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class DifferenceUtil {
	
	public static void difference(Object s1, Object s2, List<String> changedProperties, String parent)
			throws IllegalAccessException {
		for (Field field : s1.getClass().getDeclaredFields()) {
			if (parent == null) {
				parent = s1.getClass().getSimpleName();
			}
			field.setAccessible(true);
			Object value1 = field.get(s1);
			Object value2 = field.get(s2);
			if (value1 == null && value2 == null) {
				continue;
			}
			if (value1 == null || value2 == null) {
				changedProperties.add(parent + "." + field.getName());
			} else {
				if (isBaseType(value1.getClass())) {
					if (!Objects.equals(value1, value2)) {
						changedProperties.add(parent + "." + field.getName());
					}
				} else {
					difference(value1, value2, changedProperties, parent + "." + field.getName());
				}
			}
		}
	}

	private static final Set<Class> BASE_TYPES = new HashSet(Arrays.asList(String.class, Boolean.class, Character.class,
			Byte.class, Short.class, Integer.class, Long.class, Float.class, Double.class, Void.class));

	public static boolean isBaseType(Class clazz) {
		return BASE_TYPES.contains(clazz);
	}
}
