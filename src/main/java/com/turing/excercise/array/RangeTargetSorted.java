package com.turing.excercise.array;

import com.turing.excercise.TestResultsHelper;

//Find Middle, Search in left for first occurrence, Search in right for last occurance.
class MySolution {
    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) return new int[]{-1, -1};
        if (nums.length == 1 && nums[0] == target) return new int[]{0, 0};
        if (nums[0] == target && nums[nums.length - 1] == target) return new int[]{0, nums.length - 1};

        int bsIndex = binarySearch(nums, 0, nums.length - 1, target);
        if (bsIndex == -1) return new int[]{-1, -1};

        int start = bsIndex, end = bsIndex;
        if ((bsIndex - 1 >= 0 && nums[bsIndex - 1] < target)
                && (bsIndex + 1 < nums.length && nums[bsIndex + 1] > target)) return new int[]{bsIndex, bsIndex};
        start = findSmallestIndex(nums, bsIndex, 0, target);
        end = findLargestIndex(nums, bsIndex, nums.length - 1, target);
        return new int[]{start, end};
    }

    int binarySearch(int[] nums, int low, int high, int target) {
        if (high >= low) {
            int mid = (high - low) / 2 + low;
            if (nums[mid] == target) return mid;

            if (nums[mid] > target) return binarySearch(nums, low, mid - 1, target);
            else return binarySearch(nums, mid + 1, high, target);
        }
        return -1;
    }

    int findSmallestIndex(int[] nums, int start, int end, int target) {
        int base = start;
        int i = 0;
        while (base >= 0) {
            if (nums[base] != target) break;
            base = base - (int) Math.pow(2, i);
            i++;
        }
        if (base <= 0) {
            base = 0;
            while (nums[base] != target) {
                base++;
            }
            return base;
        }
        if (nums[base + 1] == target) return base + 1;
        return findSmallestIndex(nums, start - base, end, target);
    }

    int findLargestIndex(int[] nums, int start, int end, int target) {
        int base = start;
        int i = 0;
        while (base < nums.length - 1) {
            if (nums[base] != target) break;
            base = base + (int) Math.pow(2, i);
            i++;
        }
        if (base >= nums.length - 1) {
            base = nums.length - 1;
            while (nums[base] != target) {
                base--;
            }
            return base;
        }
        if (nums[base - 1] == target) return base - 1;
        return findSmallestIndex(nums, start + base, end, target);
    }
}

public class RangeTargetSorted {
    public int[] searchRange(int[] nums, int target) {

        int firstOccurrence = this.findBound(nums, target, true);

        if (firstOccurrence == -1) {
            return new int[]{-1, -1};
        }

        int lastOccurrence = this.findBound(nums, target, false);

        return new int[]{firstOccurrence, lastOccurrence};
    }

    private int findBound(int[] nums, int target, boolean isFirst) {
        int N = nums.length;
        int begin = 0, end = N - 1;

        while (begin <= end) {

            int mid = (begin + end) / 2;

            if (nums[mid] == target) {

                if (isFirst) {

                    // This means we found our lower bound.
                    if (mid == begin || nums[mid - 1] != target) {
                        return mid;
                    }

                    // Search on the left side for the bound.
                    end = mid - 1;

                } else {

                    // This means we found our upper bound.
                    if (mid == end || nums[mid + 1] != target) {
                        return mid;
                    }

                    // Search on the right side for the bound.
                    begin = mid + 1;
                }

            } else if (nums[mid] > target) {
                end = mid - 1;
            } else {
                begin = mid + 1;
            }
        }

        return -1;
    }


    public void run() {
        int[] testcase1 = new int[]{5, 7, 7, 8, 8, 10};
        int target1 = 8;
        int[] testcase2 = new int[]{5, 7, 7, 8, 8, 10};
        int target2 = 6;
        int[] testcase3 = new int[]{};
        int target3 = 0;
        int[] testcase4 = new int[]{8, 8, 8, 8, 8, 8};
        int target4 = 8;
        int[] testcase5 = new int[]{1, 2, 2};
        int target5 = 2;
        TestResultsHelper.verify("1", new int[]{3, 4}, this.searchRange(testcase1, target1));
        TestResultsHelper.verify("2", new int[]{-1, -1}, this.searchRange(testcase2, target2));
        TestResultsHelper.verify("3", new int[]{-1, -1}, this.searchRange(testcase3, target3));
        TestResultsHelper.verify("4", new int[]{0, 5}, this.searchRange(testcase4, target4));
        TestResultsHelper.verify("5", new int[]{1, 2}, this.searchRange(testcase5, target5));
    }

    public static void main(String[] args) {
        new RangeTargetSorted().run();
    }
}
