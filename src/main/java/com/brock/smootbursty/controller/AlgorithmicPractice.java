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

    public static int singleNumber(int[] nums) {
        int a=0;
        for(int i = 0; i<nums.length;i++){
            a = a^nums[i];
        }
        return a;
    }

    /**
     * 冒泡排序
     */
    public static int[] sort(int[] nums){
        for(int i=0; i<nums.length-1; i++){
            for(int j = i+1; j<nums.length;j++){
                if(nums[i] > nums[j]){
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                }
            }
        }
        return nums;
    }

    public static int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            digits[i]++;
            digits[i] = digits[i] % 10;
            if (digits[i] != 0){
                return digits;
            }
        }
        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }

    public static void moveZeroes(int[] nums) {
        int[] tempNums = new int[nums.length];
        int j = 0;
        for(int i=0; i<nums.length; i++){
            if(nums[i] != 0){
                tempNums[j]= nums[i];
                j++;
            }
        }
        nums = tempNums;
        System.out.println("结束");

    }



}
