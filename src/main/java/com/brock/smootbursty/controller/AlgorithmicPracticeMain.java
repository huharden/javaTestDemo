package com.brock.smootbursty.controller;

import com.brock.smootbursty.model.ListNode;

/**
 * Description:
 *
 * @author: hutao
 * Date: 2019-10-29-16:27
 */
public class AlgorithmicPracticeMain {

    public static void main(String[] args){
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2).next = new ListNode(3).next = new ListNode(4).next= new ListNode(5);
        AlgorithmicPractice.removeNthFromEnd(listNode,2);
    }
}
