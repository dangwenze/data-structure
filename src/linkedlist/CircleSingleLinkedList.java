package linkedlist;

import java.util.Objects;

/**
 * 单向环线链表
 */
public class CircleSingleLinkedList {

    public static void main(String[] args) {
        CircleSingleLinkedList linkedlist = new CircleSingleLinkedList();
        // 新增
        linkedlist.add(new CircleLinkNode(2,"2"));
        linkedlist.add(new CircleLinkNode(4,"4"));
        linkedlist.add(new CircleLinkNode(6,"6"));
        linkedlist.add(new CircleLinkNode(8,"8"));
        System.out.println("新增结果显示：");
        linkedlist.printLinked();


        linkedlist.delById(4);
        System.out.println("删除结果显示：");
        linkedlist.printLinked();

    }

    /** 第一个元素的指针,固定不变 */
    private CircleLinkNode first;
    /** 指向最后一个元素的指针,随着元素加入而变化 */
    private CircleLinkNode last;
    /** 当前队列一共有几个人 */
    private int size;

    /**
     * 单向环形链表添加
     * @param node
     */
    public void add(CircleLinkNode node){
        if(first == null){
            first = node;
            last = node;
        }else{
            last.setNext(node);
            node.setNext(first);
            last = node;
        }
        size++;
    }

    /**
     * 打印当前单向环形链表
     */
    public void printLinked(){
        /*
            从first打印到last即可
         */
        CircleLinkNode temp = first;
        while(temp != null && !temp.getNext().equals(first)){
            System.out.println(temp);
            temp = temp.getNext();
        }
        System.out.println(temp);
    }

    /**
     * 根据ID删除
     * @param delId
     */
    public void delById(int delId){
        if (first == null){
            System.out.println("当前队列为空！");
            return;
        }
        // 当前节点
        CircleLinkNode current = first;
        // 当前节点的上一个节点
        CircleLinkNode pre = last;
        boolean ifDel = false;
        // 开始遍历，遍历结束条件是当前节点的下一个节点指向第一个节点
        while(current.getNext() != first){
            // 如果发现相同，则进行删除操作
            if(current.getId() == delId){
                pre.setNext(current.getNext());
                current.setNext(null);
                // 这里如果是头部指针发生了变化，需要移动头部指针
                if (first == current){
                    first = pre.getNext();
                }
                ifDel = true;
                break;
            }
            // 如果不是删除节点，则将当前节点及前一个节点向后移动一个位置
            current = current.getNext();
            pre = pre.getNext();
        }
        // 如果遍历完成之后还没有进行删除操作，则有两种情况
        // 1、最后一个节点，没有比较
        // 2、确实没有找到删除节点
        if(!ifDel){
            // 这里如果是尾部指针发生了变化，要移动last指针位置
            if(current.getId() == delId){
                ifDel = true;
                pre.setNext(current.getNext());
                current.setNext(null);
                last = pre;
            }
        }
        if (!ifDel){
            System.out.println("队列中无当前节点");
        }
        size--;
    }

    public CircleLinkNode getFirst() {
        return first;
    }

    public void setFirst(CircleLinkNode first) {
        this.first = first;
    }

    public CircleLinkNode getLast() {
        return last;
    }

    public void setLast(CircleLinkNode last) {
        this.last = last;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
/**
 * 定义链表元素节点
 */
class CircleLinkNode{

    private int id;
    private String name;
    /** 注意，这里要存储单链表的下一个元素 */
    private CircleLinkNode next;

    public CircleLinkNode(int id,String name){
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CircleLinkNode getNext() {
        return next;
    }

    public void setNext(CircleLinkNode next) {
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

    @Override
    public boolean equals(Object o) {
        CircleLinkNode temp = (CircleLinkNode)o;
        if(this.id == temp.getId()){
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, next);
    }
}