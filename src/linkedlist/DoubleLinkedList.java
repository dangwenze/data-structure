package linkedlist;

/**
 * 带头节点的双向链表
 */
public class DoubleLinkedList {

    public static void main(String[] args) {
        DoubleLinkedList linkedlist = new DoubleLinkedList();
        // 新增
        linkedlist.add(new DoubleLinkNode(2,"2"));
        linkedlist.add(new DoubleLinkNode(4,"4"));
        linkedlist.add(new DoubleLinkNode(6,"6"));
        linkedlist.add(new DoubleLinkNode(8,"8"));
        linkedlist.add(new DoubleLinkNode(10,"10"));
        System.out.println("新增结果显示：");
        linkedlist.printLinked();

//        System.out.println("插入结果显示：");
//        linkedlist.insertNodeByIdAsc(new DoubleLinkNode(7,"7"));
//        linkedlist.insertNodeByIdAsc(new DoubleLinkNode(1,"1"));
//        linkedlist.insertNodeByIdAsc(new DoubleLinkNode(11,"11"));
//        linkedlist.insertNodeByIdAsc(new DoubleLinkNode(7,"7"));
//        linkedlist.printLinked();

        System.out.println("删除结果显示：");
        linkedlist.del(10);
        linkedlist.printLinked();

    }

    /** 头节点*/
    private DoubleLinkNode head = new DoubleLinkNode(0,"");

    /**
     * 新增方法
     * @param node
     */
    public void add(DoubleLinkNode node){
        /*
            1、遍历，找到最后一个节点即可
            2、设置最后节点的下一个节点为新节点
            3、新节点的上一个节点设置为最后一个节点
         */
        DoubleLinkNode lastNode = head;
        while (lastNode.getNext() != null){
            lastNode = lastNode.getNext();
        }
        lastNode.setNext(node);
        node.setPre(lastNode);
    }

    /**
     * 按照ID升序将节点插入链表
     * @param node
     */
    public void insertNodeByIdAsc(DoubleLinkNode node){
        /*
            1、寻找位置
                1）遍历节点，若下一个节点大于插入节点id，则停止
            2、插入
                1）将上一个节点的next指向node
                2）将node的pre指向上一个节点，将next指向下一个节点
                3）将下一个节点的pre指向node
         */
        DoubleLinkNode currentNode = head;
        while (currentNode.getNext() != null){
            if (currentNode.getNext().getId() > node.getId()){
                break;
            }
            currentNode = currentNode.getNext();
        }
        // 判断是否遍历到最后一个节点
        if (currentNode.getNext() == null){
            currentNode.setNext(node);
            node.setPre(currentNode);
        }else{
            DoubleLinkNode nextNode = currentNode.getNext();
            currentNode.setNext(node);
            node.setPre(currentNode);
            node.setNext(nextNode);
            nextNode.setPre(currentNode);
        }
    }

    /**
     * 根据ID删除节点
     * @param nodeId
     */
    public void del(int nodeId){

        DoubleLinkNode currentNode = head.getNext();
        boolean ifFind = false;
        while (currentNode != null){
            if(currentNode.getId() == nodeId){
                ifFind = true;
                break;
            }
            currentNode = currentNode.getNext();
        }
        if (ifFind){
            DoubleLinkNode next = currentNode.getNext();
            DoubleLinkNode pre = currentNode.getPre();
            pre.setNext(next);
            if (next != null){
                next.setPre(pre);
            }
        }else{
            System.out.println("没有该节点");
        }
    }

    /**
     * 打印节点
     */
    private void printLinked() {
        DoubleLinkNode lastNode = head.getNext();
        while (lastNode != null){
            System.out.println(lastNode.toString());
            lastNode = lastNode.getNext();
        }
    }

}

class DoubleLinkNode{

    private int id;
    private String name;
    /** 下一个元素 */
    private DoubleLinkNode next;
    /** 上一个元素 */
    private DoubleLinkNode pre;

    public DoubleLinkNode(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DoubleLinkNode getNext() {
        return next;
    }

    public void setNext(DoubleLinkNode next) {
        this.next = next;
    }

    public DoubleLinkNode getPre() {
        return pre;
    }

    public void setPre(DoubleLinkNode pre) {
        this.pre = pre;
    }

    @Override
    public String toString() {
        return "DoubleLinkNode{" +
                "id=" + id +
                ", name='" + name + "'" +
                '}';
    }
}