class Solution(object):
    def twoSum(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: List[int]
        """
        dicts = {}
        for i in range(len(nums)):
            a = nums[i]
            if dicts.get(a) != None:
                if target - a == a:
                    return [dicts.get(a), i]
            dicts[a] = i
        print(dicts)
        for i in range(len(nums)):

            a = nums[i]
            print(a)
            if (dicts.get(target - a) != None):
                print(dicts.get(target - a))
                if (target - a == a):
                    continue
                return [i, dicts.get(target - a)]


if __name__ == '__main__':
    s = Solution().twoSum([3, 2, 4], 6)
    print(s)
    # print(0==None)
