package com.hf.util;

import org.jboss.logging.Logger;

public class LogUtil {
	public static <T> void info(String info,Class<T> clazz) {
		Logger.getLogger(clazz).info(info);
	}
}
