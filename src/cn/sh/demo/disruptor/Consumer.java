package cn.sh.demo.disruptor;

import com.lmax.disruptor.WorkHandler;

/**
 * Created by sh on 2017/7/15.
 * 消费者要实现WorkHandler接口
 */
public class Consumer implements WorkHandler<PCData>{

    @Override
    public void onEvent(PCData pcData) throws Exception {
        System.out.println(Thread.currentThread().getId() + ":Event:--"
                + pcData.getValue() * pcData.getValue() + "--");
    }
}
