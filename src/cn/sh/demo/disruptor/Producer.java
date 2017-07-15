package cn.sh.demo.disruptor;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * Created by sh on 2017/7/15.
 * 生产者
 */
public class Producer {

    private final RingBuffer<PCData> ringBuffer;

    public Producer(RingBuffer<PCData> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void pushData(ByteBuffer byteBuffer) {
        long sequence = ringBuffer.next();
        PCData pcData = ringBuffer.get(sequence);
        pcData.setValue(byteBuffer.getLong(0));
        ringBuffer.publish(sequence);
    }
}
