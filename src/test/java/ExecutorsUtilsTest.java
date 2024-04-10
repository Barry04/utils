import com.suncy.utils.ExecutorsUtils;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadPoolExecutor;

import static org.junit.jupiter.api.Assertions.*;

public class ExecutorsUtilsTest {

    @Test
    void testGetThreadPoolExecutor() {
        ThreadPoolExecutor executor = ExecutorsUtils.getThreadPoolExecutor();

        assertNotNull(executor, "The executor should not be null");
        assertEquals(8, executor.getCorePoolSize(), "Core pool size should be set to 8");
        assertEquals(20, executor.getMaximumPoolSize(), "Maximum pool size should be set to 20");
        assertTrue(executor.getQueue().remainingCapacity() > 0, "The queue should have remaining capacity");
        assertEquals(ThreadPoolExecutor.AbortPolicy.class, executor.getRejectedExecutionHandler().getClass(), "Rejected execution handler should be of type AbortPolicy");

        Runnable runnable = () -> System.out.println("ExecutorsUtilsTest.testGetThreadPoolExecutor");
        Runnable sdrun = () -> System.out.println("test");
        executor.execute(runnable);
        executor.execute(sdrun);
        // Cleanup
        executor.shutdown();
    }


}
