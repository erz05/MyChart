package com.erz.mychart.listeners;

public interface BackgroundTaskListener<ResultType> {

	void progress(String message);
	void error(int code, Throwable throwable, String message);
	void complete(ResultType result);
	void begin();
}
