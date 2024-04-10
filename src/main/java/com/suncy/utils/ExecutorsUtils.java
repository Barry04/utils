package com.suncy.utils;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ExecutorsUtils {

    /*
    在 Java 中，ThreadPoolExecutor 提供了以下五种内置的拒绝执行策略（Rejecting Execution Handler），用于处理当线程池无法接受新提交的任务时的情况：
     AbortPolicy (默认策略)
     行为：直接抛出 RejectedExecutionException 异常，阻止任务提交。
     说明：这是最直接的拒绝策略，当任务无法被调度执行时，立即抛出异常通知调用者。
     CallerRunsPolicy
     行为：将任务回退到调用者所在的线程中直接执行。
     说明：这种策略会降低系统吞吐量，因为提交任务的线程必须亲自执行被拒绝的任务，从而阻塞了后续任务的提交。但它保证了任务最终会被执行，并且不会丢失任务。
     DiscardPolicy
     行为：默默地丢弃无法执行的任务，不做任何处理。
     说明：这种策略适用于那些即使丢失个别任务也不会对系统造成严重影响，或者能够容忍任务偶尔丢失的应用场景。
     DiscardOldestPolicy
     行为：抛弃线程池队列中最旧的一个未开始执行的任务（通常是最先入队的那个），然后尝试重新提交当前被拒绝的任务。
     说明：这种策略试图通过牺牲一个旧任务来为新任务腾出空间，有可能导致任务执行顺序的混乱。它适用于对任务执行顺序要求不高，但希望尽可能处理新任务的场景。
     Custom Policy
     行为：用户可以自定义拒绝策略，实现 RejectedExecutionHandler 接口并提供自己的处理逻辑。
     说明：对于有特殊需求的应用，可以编写自定义拒绝策略类，例如记录日志、发送报警、将任务存储到备用队列等待后续处理等。
     在实际使用中，应根据系统的具体需求和容错能力选择合适的拒绝策略。上述代码示例中使用的是 AbortPolicy，即遇到拒绝情况时抛出异常。
    */

    /**
     * 获取一个配置好的固定大小线程池。
     * <p>此线程池的核心线程数和最大线程数都被设置为8，超出任务将被拒绝执行，并抛出异常。</p>
     *
     * @return ThreadPoolExecutor 固定大小的线程池实例
     */
    public static ThreadPoolExecutor getThreadPoolExecutor() {
        // 创建一个固定大小的线程池，初始线程数、最大线程数和队列容量自动配置
        ThreadPoolExecutor fixedThreadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(8);
        fixedThreadPool.setCorePoolSize(8); // 设置核心线程数为8
        fixedThreadPool.setMaximumPoolSize(20); // 设置最大线程数为20
        // 设置拒绝执行策略为AbortPolicy，即当线程池无法执行任务时抛出异常
        fixedThreadPool.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        return fixedThreadPool;
    }
}
