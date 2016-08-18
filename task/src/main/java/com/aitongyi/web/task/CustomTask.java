package com.aitongyi.web.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *  自定义定时任务
 * Created by admin on 16/8/15.
 */
@Component
public class CustomTask {
    private static final Logger logger = LoggerFactory.getLogger(CustomTask.class);


    /**
     * 调度任务执行
     * <pre>
     *     <table>
     *      <th>
     *          <tr> <td>名称</td>  <td>类型</td> <td>单位</td><td>说明</td> </tr>
     *      </th>
     *
     *          <tr> <td>cron</td>  <td>String</td> <td> - </td> <td>cron表达式</td></tr>
     *          <tr> <td>zone</td> <td>String</td> <td> - </td> <td>时区字符串（一般不需要设置）</td> </tr>
     *          <tr> <td>fixedDelay</td> <td>long</td> <td>毫秒</td> <td>调度间隔，下一个任务开始时间与上一个任务结束时间间隔[F-S]</td> </tr>
     *          <tr> <td>fixedDelayString</td> <td>String</td> <td>毫秒</td> <td>调度间隔，下一个任务开始时间与上一个任务结束时间间隔，字符串表示[F-S]</td> </tr>
     *          <tr> <td>fixedRate</td> <td>long</td> <td>毫秒</td> <td>调度间隔，下一个任务开始时间与上一个任务开始时间间隔[S-S]</td> </tr>
     *          <tr> <td>fixedRateString</td> <td>String</td> <td>毫秒</td> <td>调度间隔，下一个任务开始时间与上一个任务开始时间间隔，字符串表示[S-S]</td> </tr>
     *          <tr> <td>initialDelay</td> <td>long</td> <td>毫秒</td> <td>调度器启动延迟时间</td> </tr>
     *          <tr> <td>initialDelayString</td> <td>String</td> <td>毫秒</td> <td>调度器启动延迟时间，字符串表示</td> </tr>
     *
     *     </table>
     * </pre>
     */
    @Scheduled(fixedRate = 1000 * 10,initialDelay = 1000 * 5)
    private void taskRun(){
        logger.info("CustomTask run ...");
    }
}
