package com.brock.smootbursty.controller;

import com.brock.smootbursty.model.ListNode;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 *
 * @author: hutao
 * Date: 2019-10-29-15:57
 */
@RestController
@RequestMapping("/")
public class AlgorithmicPractice {

    public static ListNode reverseList(ListNode head) {
        System.out.println(head.val);
        //0或一个节点的时候直接返回
        if (head == null || head.next == null) {
            return head;
        }
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode init = new ListNode(0);
        init.next = head;
        ListNode first = init;
        ListNode second = init;
        for(int i = 0; i< n; i++ ){
            first = first.next;
        }

        //再两个指针同时出发
        while (first != null){
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return second;

    }

}
