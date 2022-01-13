package com.turing.interview.meta;

/**
 * We keep track of the revenue Facebook makes every day, and we want to know on what days Facebook hits certain
 * revenue milestones. Given an array of the revenue on each day, and an array of milestones Facebook wants to reach,
 * return an array containing the days on which Facebook reached every milestone.
 */
public class RevenueMilestones {
    int[] getMilestoneDays(int[] revenues, int[] milestones) {
        // Write your code here
        int[] milestonesHit = new int[milestones.length];
        for (int i = 1; i < revenues.length; i++) {
            revenues[i] = revenues[i - 1] + revenues[i];
        }
        for (int j = 0; j < milestones.length; j++) {
            milestonesHit[j] = binarySearch(milestones[j], 0, revenues.length - 1, revenues) + 1;
        }
        return milestonesHit;
    }

    private int binarySearch(int milestone, int low, int high, int[] arr) {
        if (high >= low) {
            int middle = (high - low) / 2 + low;
            if (arr[middle] < milestone) {
                if (arr[middle + 1] >= milestone) return middle + 1;
                else return binarySearch(milestone, middle + 1, high, arr);
            } else {
                if (arr[middle] == milestone) return middle;
                else return binarySearch(milestone, low, middle, arr);
            }
        }
        return -1;
    }

    // These are the tests we use to determine if the solution is correct.
    // You can add your own at the bottom.
    int test_case_number = 1;

    void check(int[] expected, int[] output) {
        int expected_size = expected.length;
        int output_size = output.length;
        boolean result = true;
        if (expected_size != output_size) {
            result = false;
        }
        for (int i = 0; i < Math.min(expected_size, output_size); i++) {
            result &= (output[i] == expected[i]);
        }
        char rightTick = '\u2713';
        char wrongTick = '\u2717';
        if (result) {
            System.out.println(rightTick + " Test #" + test_case_number);
        } else {
            System.out.print(wrongTick + " Test #" + test_case_number + ": Expected ");
            printIntegerArray(expected);
            System.out.print(" Your output: ");
            printIntegerArray(output);
            System.out.println();
        }
        test_case_number++;
    }

    void printIntegerArray(int[] arr) {
        int len = arr.length;
        System.out.print("[");
        for (int i = 0; i < len; i++) {
            if (i != 0) {
                System.out.print(", ");
            }
            System.out.print(arr[i]);
        }
        System.out.print("]");
    }

    public void run() {
        int revenues_1[] = {100, 200, 300, 400, 500};
        int milestones_1[] = {300, 800, 1000, 1400};
        int expected_1[] = {2, 4, 4, 5};
        int[] output_1 = getMilestoneDays(revenues_1, milestones_1);
        check(expected_1, output_1);

        int revenues_2[] = {700, 800, 600, 400, 600, 700};
        int milestones_2[] = {3100, 2200, 800, 2100, 1000};
        int expected_2[] = {5, 4, 2, 3, 2};
        int[] output_2 = getMilestoneDays(revenues_2, milestones_2);
        check(expected_2, output_2);

        // Add your own test cases here

    }

    public static void main(String[] args) {
        new RevenueMilestones().run();
    }
}
