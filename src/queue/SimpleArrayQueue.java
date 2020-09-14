package queue;

import java.util.Arrays;

/**
 * 简单一次性队列
 * 数组实现
 */
public class SimpleArrayQueue {

    public static void main(String[] args){
        ArrayQueue queue = new ArrayQueue(3);
//        System.out.println(queue.reduce());
        System.out.println(queue);
        queue.add(10);
        System.out.println(queue);
        queue.add(20);
        System.out.println(queue);
        queue.add(30);
        System.out.println(queue);
//        queue.add(40);
//        System.out.println(queue);
        System.out.println(queue.reduce());
        System.out.println(queue);
        System.out.println(queue.reduce());
        System.out.println(queue);
        System.out.println(queue.reduce());
        System.out.println(queue);
    }
}

/**
 * 数组实现的队列
 * 简单一次性数组
 */
class ArrayQueue{
    // 最大长度
    private int maxSize;
    // 头部指针
    private int headPointer;
    // 尾部指针
    private int endPointer;
    // 数组容器
    private int[] array;

    /**
     * 构造方法
     * @param maxSize
     */
    public ArrayQueue(int maxSize){

        this.maxSize = maxSize;
        // 头部指针指向头部元素的前一个位置
        headPointer = -1;
        // 尾部指针指向尾部元素
        endPointer = -1;
        // 初始化队列容器
        array = new int[maxSize];
    }

    /**
     * 入队操作
     * 入队时，队尾指针加一
     * @param value
     * @return
     * @throws RuntimeException
     */
    public int add(int value) throws RuntimeException{
        // 先判断队列是否已满
        if (isFull()){
            throw new RuntimeException("the queue is full!");
        }
        // 添加操作时，尾部指针加一，头部指针不变
        array[++endPointer] = value;
        // 返回当前队列长度
        return array.length;
    }

    /**
     * 队列是否已满
     * @return
     */
    private boolean isFull() {
        // 当队尾指针指向数组的最后一个位置时，表示长度已满
        if (endPointer == maxSize -1){
            return true;
        }
        return false;
    }

    public int reduce() throws RuntimeException{
        // 先判断队列是否已空
        if(isEmpty()){
            throw new RuntimeException("the queue is empty!");
        }
        // 出队时，头部指针加一，尾部指针不变
        int result = 0;
        result = array[++headPointer];
        // 清空当前队列元素
        array[headPointer] = 0;
        return result;
    }

    /**
     * 判断队列是否为空
     * @return
     */
    private boolean isEmpty() {
        // 当队头和队尾指针相同时，则队列元素为空
        if (headPointer == endPointer){
            return true;
        }
        return false;
    }

    @Override
    public String toString(){
        return Arrays.toString(array);
    }
}