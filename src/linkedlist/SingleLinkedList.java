package linkedlist;

import java.util.Stack;

public class SingleLinkedList {

    public static void main(String[] args) {
        Linkedlist linkedlist = new Linkedlist();
        // 新增
        linkedlist.add(new LinkNode(2,"2",null));
        linkedlist.add(new LinkNode(4,"4",null));
        linkedlist.add(new LinkNode(6,"6",null));
        linkedlist.add(new LinkNode(8,"8",null));
        linkedlist.add(new LinkNode(10,"10",null));
        System.out.println("新增结果显示：");
        linkedlist.printLinked();

//        System.out.println("反转打印：");
//        linkedlist.reversePrint();

//        System.out.println("反转结果显示：");
//        linkedlist.reverseByNode();
//        linkedlist.printLinked();

        // 删除
        linkedlist.del(10);
        System.out.println("删除结果显示：");
        linkedlist.printLinked();
//
//        // 修改
//        linkedlist.editNode(3,"这个是修改后的值");
//        System.out.println("修改结果显示：");
//        linkedlist.printLinked();
//
//
//        // 插入指定位置
//        System.out.println("插入指定位置结果显示：");
//        linkedlist.insertById(new LinkNode(1,"1",null) );
//        linkedlist.printLinked();
//        System.out.println();
//        linkedlist.insertById(new LinkNode(7,"7",null) );
//        linkedlist.printLinked();
//        System.out.println();
//        linkedlist.insertById(new LinkNode(11,"11",null) );
//        linkedlist.printLinked();
//        System.out.println();
    }

}

/**
 * 单链表类
 * 包含方法：
 *      新增
 *      插入
 *      删除
 *      修改
 *      打印
 */
class Linkedlist{

    /** 这里需要初始化一个头节点 */
    private LinkNode head = new LinkNode(0,"",null);

    /**
     * 打印当前数组
     */
    public void printLinked(){
        LinkNode nextNode = head.getNext();
        if (nextNode == null){
            System.out.println("当前链表为空！");
            return;
        }
        while (true){
            if (nextNode == null){
                break;
            }
            System.out.println(nextNode.toString());
            nextNode = nextNode.getNext();
        }
    }

    /**
     * 新增节点
     * @param node
     */
    public void add(LinkNode node){
        LinkNode nextNode = head;
        // 这里遍历整个链表，直至到链表的最后一个节点
        while(true){
            // next 指针为空，则表示当前节点为最后一个节点
            if (nextNode.getNext() == null){
                break;
            }
            nextNode = nextNode.getNext();
        }
        // 找到最后一个节点后，将其指针指向要添加的节点
        nextNode.setNext(node);
    }

    /**
     * 根据id删除链表元素
     * @param nodeId
     */
    public void del(int nodeId){

        /*
            1、找到该id元素
            2、将该元素的上一个节点，next节点执行该元素下一个节点
         */

        LinkNode currentNode = head.getNext();
        LinkNode preNode = head;
        boolean ifFind = false;
        while (currentNode != null){
            if(currentNode.getId() == nodeId){
                ifFind = true;
                break;
            }
            preNode = currentNode;
            currentNode = currentNode.getNext();
        }
        if (ifFind){
            preNode.setNext(currentNode.getNext());
        }else{
            System.out.println("没有该节点");
        }

//        LinkNode nextNode = head.getNext();
//
//        if (nodeId == 0){
//            System.out.println("头节点元素不能删除！");
//            return;
//        }
//
//        if (nextNode == null){
//            System.out.println("当前链表为空！");
//            return;
//        }
//        // 先判断第一个节点是不是目标删除节点
//        if (nextNode.getId() == nodeId){
//            head.setNext(nextNode.getNext());
//            return;
//        }
//        // 从第二个节点开始，判断是否是目标删除节点
//        while(true){
//            if (nextNode.getNext() == null){
//                System.out.println("当前链表无对应元素！");
//                return;
//            }
//            if (nextNode.getNext().getId() == nodeId){
//                nextNode.setNext(nextNode.getNext().getNext());
//                return;
//            }
//            nextNode = nextNode.getNext();
//        }
    }

    /**
     * 根据目标ID修改节点
     * @param nodeId
     * @param editName
     */
    public void editNode(int nodeId,String editName){
        LinkNode nextNode = head.getNext();

        if (nodeId == 0){
            System.out.println("头节点元素不能修改！");
            return;
        }

        if (nextNode == null){
            System.out.println("当前链表为空！");
            return;
        }

        while (true){
            if (nextNode == null){
                System.out.println("当前链表无对应元素！");
                return;
            }
            if (nextNode.getId() == nodeId){
                nextNode.setName(editName);
                return;
            }
            nextNode = nextNode.getNext();
        }
    }

    /**
     * 将目标节点插入
     * 按照ID从小到大的规章，插入适当位置
     * @param insertNode
     */
    public void insertById(LinkNode insertNode){

        if(insertNode == null){
            System.out.println("插入节点为null");
            return;
        }

        LinkNode nextNode = head.getNext();
        // 如果头节点的下一个元素是null,表示链表为空，直接插入即可
        if (nextNode == null){
            head.setNext(insertNode);
            return;
        }
        // 先判断第一个节点
        // 插入的条件是，下一个节点的元素的id比传入节点的id大
        if (nextNode.getId() > insertNode.getId()){
            head.setNext(insertNode);
            insertNode.setNext(nextNode);
            return;
        }
        while(true){
            // 遍历到最后一个节点若还是null，说明传入节点元素id是最大的，插入最后即可
            if (nextNode.getNext() == null){
                nextNode.setNext(insertNode);
                return;
            }

            if (nextNode.getNext().getId() > insertNode.getId()){
                insertNode.setNext(nextNode.getNext());
                nextNode.setNext(insertNode);
                return;
            }
            nextNode = nextNode.getNext();
        }
    }

    /**
     * 判断单链表是否为空
     * @return
     */
    public boolean ifEmpty(){
        if (this.head.getNext() == null){
            return true;
        }
        return false;
    }

    /**
     * 反转单链表
     * 通过新起一个节点的方式
     */
    public void reverseByNode(){
        /*
            1、先进行基础判断，判断当前队列是否需要反转
                1）是否为空；
                2）是否只有一个节点
            2、new 一个新的头节点作为队列反转的头节点
            3、进行遍历
                1）取一个临时节点，将头节点指向临时节点，临时节点的下一个节点指向头节点的下一个节点
                2）临时节点后移
            4、将旧的头节点替换为新的头节点
         */
        if (this.ifEmpty()){
            System.out.println("当前链表为空！");
            return;
        }
        if (this.head.getNext().getNext() == null){
            System.out.println("当前链表只有一个节点，无需反转！");
            return;
        }
        LinkNode reverseHead = new LinkNode(0,"0",null);
        LinkNode temp = this.head.getNext();
        while (temp != null){
            // 需要将临时节点的下一个节点先存储起来，之后进行交换赋值
            LinkNode cacheNode = temp.getNext();
            temp.setNext(reverseHead.getNext());
            reverseHead.setNext(temp);
            temp = cacheNode;
        }
        this.head = reverseHead;
    }

    /**
     * 单链表的反向打印
     * 两种思路：
     *      1、先反转，后遍历，不推荐，这样会导致链表的原始结构破坏
     *      2、借用栈数据结构，将单链表的数据压入栈中，后出栈即可
     */
    public void reversePrint(){
        Stack<LinkNode> stack = new Stack<>();
        LinkNode temp = this.head;
        while(temp.getNext() != null){
            stack.push(temp.getNext());
            temp = temp.getNext();
        }
        while (!stack.empty()){
            System.out.println(stack.pop());
        }
    }

}


/**
 * 定义链表元素节点
 */
class LinkNode{

    private int id;
    private String name;
    /** 注意，这里要存储单链表的下一个元素 */
    private LinkNode next;

    public LinkNode(int id,String name,LinkNode next){
        this.id = id;
        this.name = name;
        this.next = next;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LinkNode getNext() {
        return next;
    }

    public void setNext(LinkNode next) {
        this.next = next;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "LinkNode{" +
                "id=" + id +
                ", name='" + name +"'"+
                '}';
    }
}