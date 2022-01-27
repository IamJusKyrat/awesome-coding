package com.turing.excercise.array;

import com.turing.excercise.TestResultsHelper;

import java.util.PriorityQueue;

public class MedianStream {
    int[] findMedian(int[] arr) {
        PriorityQueue<Integer> large = new PriorityQueue<>((n1, n2) -> n2 - n1);
        PriorityQueue<Integer> small = new PriorityQueue<>();
        int[] output = new int[arr.length];
        for(int i =0;i<arr.length;i++){
            if((i+1)%2 ==0){
                large.add(arr[i]);
                small.add(large.poll());
                output[i] = (small.peek()+ large.peek())/2;
                //System.out.println(i+" "+output[i]);
            } else{
                small.add(arr[i]);
                large.add(small.poll());
                output[i] = large.peek();
                //System.out.println(i+" "+ arr[i]+"  "+output[i]);
            }

        }
        return output;
    }

    public void run() {
        int[] arr_1 = {5, 15, 1, 3};
        int[] expected_1 = {5, 10, 5, 4};
        int[] output_1 = findMedian(arr_1);
        TestResultsHelper.verify("1", expected_1, output_1);

        int[] arr_2 = {2, 4, 7, 1, 5, 3};
        int[] expected_2 = {2, 3, 4, 3, 4, 3};
        int[] output_2 = findMedian(arr_2);
        TestResultsHelper.verify("2", expected_2, output_2);

        // Add your own test cases here
        int[] arr_3 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] expected_3 = {1,1,2,2,3,3,4,4,5,5};
        int[] output_3 = findMedian(arr_3);
        TestResultsHelper.verify("3", expected_3, output_3);

    }
    public static void main(String[] args) {
        new MedianStream().run();
    }
}
