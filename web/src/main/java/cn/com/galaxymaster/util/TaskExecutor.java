package cn.com.galaxymaster.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 任务执行器类
 * 
 * @author John
 *
 */
public class TaskExecutor {

	private static ExecutorService executorService = Executors.newFixedThreadPool(30);

	/**
	 * 添加需要执行的任务对象
	 * 
	 * @param task
	 */
	public static void addTask(Runnable task) {
		executorService.execute(task);
	}

}
