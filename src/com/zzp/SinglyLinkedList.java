package com.zzp;

public class SinglyLinkedList {

    private Node head;

    private Node getTailNode(){
        if(head == null){
            return null;
        }

        Node node = head;
        while (node.next != null){
            node = node.next;
    }
        return node;
    }

    /**
     * 按值查询
     * @param value
     * @return
     */
    private Node findByData(Object value){
        if(head == null){
            return null;
        }

        Node node = head;
        while (node != null && !node.data.equals(value)){
            node = node.next;
        }
        return node;
    }

    /**
     * 查询目标值的前一个节点
     * @param value
     * @return
     */
    private Node findPreviousNodeByData(Object value){
        if(head == null || head.data.equals(value)){
            return null;
        }

        Node node = head;
        while (node != null && !node.next.data.equals(value)){
            node = node.next;
        }

        return node;

    }

    /**
     * 按索引位置查询，查询目标位置的前一个节点
     * @return
     */
    private Node findPreviousNodeByIndex(Integer index){
        if(head == null){
            return null;
        }

        Node node = head;
        int i = 1;
        while (node != null && i < index-1){
            node = node.next;
            i++;
        }

        return node;
    }

    /**
     * 按值插入(插入目标值后面)
     * @param value
     */
    private void insert(Object value, Object dest){
        Node node = new Node(value);
        Node destNode = findByData(dest);
        if(destNode == null){
            destNode = getTailNode();
            destNode.next = node;
        }else {
            node.next = destNode.next;
            destNode.next = node;
        }
    }

    /**
     * 按值插入(插入目标值前面)
     * @param value
     * @param dest
     */
    private void insert1(Object value, Object dest){
        Node node = new Node(value);
        Node destNode = findPreviousNodeByData(dest);
        if(destNode == head || destNode == null){
            node.next = head;
            head = node;
        }else {
            node.next = destNode.next;
            destNode.next = node;
        }
    }

    /**
     * 按指定位置插入元素
     * @param value
     * @param index
     */
    private void insertByIndex(Object value, Integer index){
        Node node = new Node(value);
        if(index.intValue() == 1){
            node.next = head;
            head = node;
        }

        Node destNode = findPreviousNodeByIndex(index);
        node.next = destNode.next;
        destNode.next = node;
    }

    private void deleteByValue(Object value){
        if(head.data.equals(value)){
            head = head.next;
            return;
        }
        Node node = findPreviousNodeByData(value);
        if(node != null){
            node.next = node.next.next;
        }
    }

    private void deleteAllByValue(Object value){
        if(head == null){
            return;
        }
        if(head.data.equals(value)){
            head = head.next;
            deleteAllByValue(value);
        }

        Node node = head;
        Node q = null;
        while (node != null){
            if(node.data.equals(value)){
                q.next = node.next;
                node = q.next;
                continue;
            }
            q = node;
            node = node.next;
        }
    }

    private void deleteByIndex(Integer index){
        if(index.intValue() == 1){
            head = head.next;
        }
        Node node = findPreviousNodeByIndex(index);
        if(node != null){
            node.next = node.next.next;
        }
    }

    /**
     * 判断字符串是不是回文字符串
     */
    private boolean isPalindrome(){
        //快指针
        Node fast = head;
        //慢指针
        Node slow = head;
        //前置节点
        Node prev = null;

        //慢指针走一步，快指针走两步
        //当跳出循环时，如果字符串长度为奇数，则慢指针指向正中间字符，如果长度为偶数，则慢指针指向字符的索引为偶数一半+1
        //慢指针遍历的过程中将链表前半段进行反转
        while(fast != null && fast.next != null){

            fast = fast.next.next;

            Node next = slow.next;
            slow.next = prev;
            prev = slow;
            slow = next;

        }

        if(fast != null){
            slow = slow.next;
        }

        while (prev != null && slow != null){
            if(!prev.data.equals(slow.data)){
                return false;
            }
            prev = prev.next;
            slow = slow.next;
        }
        return true;
    }

    private void print(Node head){
        Node node = head;
        while (node != null){
            System.out.print(node.data+" ");
            node = node.next;
        }
    }

    public static void main(String[] args) {
        Node prev = new Node(1);
        Node start = prev;
        for (int i = 2; i < 5; i++) {
            Node node = new Node(i);
            prev.next = node;
            prev = node;
        }

        for (int i = 6; i > 0; i--) {
            Node node = new Node(i);
            prev.next = node;
            prev = node;
        }
        prev.next = null;
        SinglyLinkedList list = new SinglyLinkedList();
        list.head = start;
//        System.out.println(list.isPalindrome());
        list.print(start);
        list.deleteAllByValue(4);
        System.out.println();
        list.print(start);
    }
}
