package com.camelot.pmt.project.util.interceptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NamedThreadLocal;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.camelot.pmt.project.util.IPUtils;




public class LogInterceptor implements HandlerInterceptor{

	private Logger logger = LoggerFactory.getLogger(LogInterceptor.class);

	private List<String> filters = new ArrayList<String>();

	private static final String startshortmsg = "[{}]开始请求[{}]";

	private static final String endshortmsg = "[{}]结束请求[{}]";

	private static final String timemsg = "[{}]请求处理时间[{}]";

	private NamedThreadLocal<Var> threadLocal = new NamedThreadLocal<Var>("var");

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object obj, Exception e) {
		Var var = threadLocal.get();
		String uri = var.getUri();
		boolean visitor = var.isFilter();
		if (visitor) {
			return;
		}
		long endTime = System.currentTimeMillis();
		long beginTime = var.getStarttime();
		long consumeTime = endTime - beginTime;
		logger.info(timemsg, uri, consumeTime);
		info(uri, var.getIp(), 2);
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object obj, ModelAndView mav)
			throws Exception {
		Var var = threadLocal.get();
		boolean visitor = var.isFilter();
		if (visitor) {
			return;
		}
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
		long beginTime = System.currentTimeMillis();
		String uri = request.getRequestURI();
		boolean visitor = filter(uri);
		String ip = IPUtils.getIpAddress(request);
		Var var = new Var(beginTime, visitor, uri, ip);
		threadLocal.set(var);
		if (visitor) {
			return true;
		}
		info(uri, ip, 1);
		return true;
	}

	private void info(String uri, String ip, int flag) {
		logger.info(flag == 1 ? startshortmsg : endshortmsg, ip, uri);
	}

	private boolean filter(String uri) {
		for (String filter : filters) {
			if (uri.endsWith(filter)) {
				return true;
			}
		}
		return false;
	}

	public void setUris(String uris) {
		if (!StringUtils.isEmpty(uris)) {
			String[] arrays = StringUtils.tokenizeToStringArray(uris, ",");
			filters = Arrays.asList(arrays);
		}
	}

	private class Var {

		private long starttime;
		private boolean filter;
		private String uri;
		private String ip;

		public Var(long starttime, boolean filter, String uri, String ip) {
			super();
			this.starttime = starttime;
			this.filter = filter;
			this.uri = uri;
			this.ip = ip;
		}

		public long getStarttime() {
			return starttime;
		}

		public boolean isFilter() {
			return filter;
		}

		public String getUri() {
			return uri;
		}

		public String getIp() {
			return ip;
		}
	}
}
