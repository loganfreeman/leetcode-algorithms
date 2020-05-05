package com.leetcode.priorityqueue;

import java.util.*;

/**
 *
 * Given an array of integers, write a method to return the k most frequent elements.
 */
public class TopKFrequentElements {
    public List<Integer> topKFrequent(int[] nums, int k) {
        //count the frequency for each element
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // create a min heap
        PriorityQueue<Map.Entry<Integer, Integer>> queue
                = new PriorityQueue<>(Comparator.comparing(e -> e.getValue()));

        //maintain a heap of size k.
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            queue.offer(entry);
            if (queue.size() > k) {
                queue.poll();
            }
        }

        //get all elements from the heap
        List<Integer> result = new ArrayList<>();
        while (queue.size() > 0) {
            result.add(queue.poll().getKey());
        }

        //reverse the order
        Collections.reverse(result);

        return result;
    }


    public List<Integer> topKFrequentBucketSort(int[] nums, int k) {
        //count the frequency for each element
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int num: nums){
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        //get the max frequency
        int max = 0;
        for(Map.Entry<Integer, Integer> entry: map.entrySet()){
            max = Math.max(max, entry.getValue());
        }

        //initialize an array of ArrayList. index is frequency, value is list of numbers
        ArrayList<Integer>[] arr = (ArrayList<Integer>[]) new ArrayList[max+1];
        for(int i=1; i<=max; i++){
            arr[i]=new ArrayList<Integer>();
        }

        for(Map.Entry<Integer, Integer> entry: map.entrySet()){
            int count = entry.getValue();
            int number = entry.getKey();
            arr[count].add(number);
        }

        List<Integer> result = new ArrayList<Integer>();

        //add most frequent numbers to result
        for(int j=max; j>=1; j--){
            if(arr[j].size()>0){
                for(int a: arr[j]){
                    result.add(a);
                    //if size==k, stop
                    if(result.size()==k){
                        return result;
                    }
                }
            }
        }

        return result;
    }
}
