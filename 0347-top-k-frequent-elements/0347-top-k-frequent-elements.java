class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }

        List<Integer>[] freq = new List[nums.length + 1];
        for (int i = 0; i <= nums.length; i++) {
            freq[i] = new ArrayList<>();
        }
        
        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            int num = entry.getKey();
            int c = entry.getValue();
            freq[c].add(num);
        }
        
        int[] res = new int[k];
        int index = 0;
        for (int i = freq.length - 1; i >= 0; i--) {
            for (int num : freq[i]) {
                res[index++] = num;
                if (index == k) {
                    return res;
                }
            }
        }
        
        return res;
    }
}