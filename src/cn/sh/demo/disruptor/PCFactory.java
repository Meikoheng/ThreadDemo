package cn.sh.demo.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * Created by sh on 2017/7/15.
 * 产生PCData工厂类，构造所有缓冲区的对象实例
 */
public class PCFactory implements EventFactory<PCData> {

    @Override
    public PCData newInstance() {
        return new PCData();
    }
}
