package test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution {
    //String1 : "jaskiratsinghuppal" String2: "uat"
    //Set [u,a,t] int count = 1;
    //Map[a = 1,t = ,u] i,j = 0;
    private String solve(String base, String find) {
        if(base == null || base.length() == 0) return null;
        if(find == null || find.length() == 0) return null;
        if(find.length() > base.length()) return  null;
        Set<Character> findSet = new HashSet<>(find.length()); //ASCII max 255
        for(int i = 0; i < find.length(); i++) {
            findSet.add(find.charAt(i));
        }
        int i = 0, j=0;
        String result = base;
        Map<Character, Integer> found = new HashMap<>(); //ASCII max 255
        while(j <= base.length()) {
            while(found.size() == findSet.size() && i < j) {
                String curr = base.substring(i, j);
                if(result.length() > curr.length()) {result = curr;}
                if(found.containsKey(base.charAt(i)) && found.get(base.charAt(i)) == i) {
                    found.remove(base.charAt(i));
                }
                i++;
            }

            if(j < base.length() && findSet.contains(base.charAt(j))) {
                found.put(base.charAt(j), j);
            }
            j++;
        }

        if(result.equals(base) && found.size() != findSet.size()) {
            return null;
        }

        return result;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        String solve = s.solve("jaskirutasinghuptpalt", "uat");
        System.out.println(solve+ " : " + solve.length() );
    }
}
