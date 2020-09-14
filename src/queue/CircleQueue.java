package queue;

import java.util.Arrays;
import java.util.Scanner;

public class CircleQueue {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        CircleArrayQueue circleArrayQueue = new CircleArrayQueue(10000);
        circleArrayQueue.add(10);
        circleArrayQueue.add(20);
        circleArrayQueue.add(30);
        circleArrayQueue.add(40);
        System.out.println("1:添加");
        System.out.println("2:删除");
        System.out.println("3:打印当前队列");
        System.out.println("4:打印数组");
        System.out.println("5:退出");
        boolean flag = true;
        while(flag){
            System.out.println("请输入操作:");
            int orea = scanner.nextInt();
            switch (orea){
                case 1 :
                    System.out.println("请输入入队数字：");
                    int val = scanner.nextInt();
                    try {
                        circleArrayQueue.add(val);
                    }catch (Exception e){
                        System.out.println("添加失败，当前队列已满！");
                    }
                    break;
                case 2 :
                    try {
                        int reduce = circleArrayQueue.reduce();
                        System.out.println("出队数字：" + reduce);
                    }catch (Exception e){
                        System.out.println("出队失败，当前队列为空！");
                    }
                    break;
                case 3 :
                    circleArrayQueue.printCurrentQueue();
                    System.out.println();
                    break;
                case 4 :
                    System.out.println(circleArrayQueue.toString());
                    break;
                case 5 :
                    flag = false;
                    System.out.println("BYE!");
                    break;
                default:
                    System.out.println("请输入指令：1、2、3、4");
                    break;
            }
        }
    }

}
/**
 * 数组实现的队列
 * 简单一次性数组
 */
class CircleArrayQueue{
    // 最大长度
    private int maxSize;
    // 头部指针
    // 头部指针指向头部元素，并且初始化为0
    private int front;
    // 尾部指针
    // 尾部指针指向尾部元素的后一个位置，并且初始化为0
    private int rear;
    // 数组容器
    private int[] array;

    /**
     * 构造方法
     * @param maxSize
     */
    public CircleArrayQueue(int maxSize){

        this.maxSize = maxSize;
        front = 0;
        rear = 0;
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

        // array[++rear] = value;
        array[rear] = value;
        // 循环队列，这里需要取余，否则数组越界
        rear = (rear + 1) % maxSize;
        // 返回当前队列长度
        return currentQueueSize();
    }

    /**
     * 计算当前队列的长度
     * @return
     */
    private int currentQueueSize() {
        /*
            这里计算数组元素，需要考虑两种情况
                1、rear >= front
                2、rear < front （循环数组）
            情况一，元素个数为 rear - front
            情况二，元素个数为 rear + maxsize -front
            为了满足上述两种情况，故产出如下式子:
              queueLength = (rear + maxSize - front) % maxSize;
         */
        return (rear + maxSize - front) % maxSize;
    }

    /**
     * 队列是否已满
     * 当front == rear时，存在两种情况：队列为空，或者队列已满
     * 有三种情况可以解决这个情况
     * 1）记录当前数组长度num,当num == maxsize，并且front == rear时，表示数组已满
     * 2）记录一个标识位 flag，当flag == true，并且front == rear时，表示数组已满
     * 3）数组空一个元素，当front == rear时，表示数组已空，当 rear + 1 == front表示数组已满
     * 下面将用第三种方式
     * @return
     */
    private boolean isFull() {
        // 这里必须取余，因为时循环队列
        if ((rear + 1) % maxSize == front) {
            return true;
        }
        return false;
    }

    public int reduce() throws RuntimeException{
        // 先判断队列是否已空
        if(isEmpty()){
            throw new RuntimeException("the queue is empty!");
        }
        int result = 0;
        result = array[front];
        // 清空当前队列元素
        array[front] = 0;
        front = (front + 1) % maxSize;
        return result;
    }

    /**
     * 判断队列是否为空
     * @return
     */
    private boolean isEmpty() {
        // 当队头和队尾指针相同时，则队列元素为空
        if (front == rear){
            return true;
        }
        return false;
    }

    /**
     * 打印当前队列
     */
    public void printCurrentQueue(){
        if (isEmpty()){
            System.out.println("当前队列为空！");
            return;
        }
        // 思路：
        // 从front开始向后取，取size个元素
        // 每回取值时i++
        // 但是，由于时循环队列，故，i对应数组下标不为i，而为 (i % maxSize)
        for(int i = front ; i < front + currentQueueSize() ; i++){
            System.out.print(array[(i % maxSize)] + " ");
        }
    }

    @Override
    public String toString(){
        return Arrays.toString(array);
    }
}