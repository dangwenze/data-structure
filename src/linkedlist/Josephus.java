package linkedlist;

/**
 * 约瑟夫问题（丢手绢问题）
 */
public class Josephus {

    public static void main(String[] args) {
        JosephusExec(5,2,5);
    }

    /**
     * 执行约瑟夫问题
     * 使用单向循环队列
     * @param total 人数
     * @param jump 数几下出一个人
     * @param start 从第几个人开始,从1开始计数
     */
    public static void JosephusExec(int total,int jump,int start){
        // 先校验参数
        if(total <= 0){
            System.out.println("人数不能为空");
            return;
        }
        if(jump <= 0){
            System.out.println("步数需要大于0");
            return;
        }
        if(start > total || start <=0){
            System.out.println("请输入合法的起始位置");
            return;
        }
        // 创建一个空的循环单链表做为容器
        CircleSingleLinkedList linkedlist = new CircleSingleLinkedList();
        // 先创建一个循环队列，模型一圈小朋友
        for(int i = 0;i<total;i++){
            linkedlist.add(new CircleLinkNode(i+1,Integer.toString(i+1)));
        }
        // 定义一个指针，先找到开始位置
        CircleLinkNode startNode = linkedlist.getFirst();
        for(int i=1;i<start;i++){
            startNode = startNode.getNext();
        }
        // 进行遍历
        while (linkedlist.getSize() > 0){
            for(int i=1;i<jump;i++){
                startNode = startNode.getNext();
            }
            CircleLinkNode cacheNode = startNode.getNext();
            System.out.println(startNode);
            linkedlist.delById(startNode.getId());
            startNode = cacheNode;
        }
    }

}
