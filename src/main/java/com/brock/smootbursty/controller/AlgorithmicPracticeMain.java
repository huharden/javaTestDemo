package com.brock.smootbursty.controller;

import com.brock.smootbursty.model.ListNode;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Description:
 *
 * @author: hutao
 * Date: 2019-10-29-16:27
 */
public class AlgorithmicPracticeMain {
    @Autowired
    public AlgorithmicPractice algorithmicPractice;

    public static void main(String[] args){
        /*ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2).next = new ListNode(3).next = new ListNode(4).next= new ListNode(5);
        AlgorithmicPractice.removeNthFromEnd(listNode,2);*/

        int[] nums = {1,0,9};
         AlgorithmicPractice.moveZeroes(nums);


    }
}
