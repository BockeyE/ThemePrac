# Definition for singly-linked list.
class ListNode(object):
    def __init__(self, x):
        self.val = x
        self.next = None


class Solution(object):
    def addTwoNumbers(self, l1, l2):
        """
        :type l1: ListNode
        :type l2: ListNode
        :rtype: ListNode
        """
        tem = 0
        promote = 0
        tem = l1.val + l2.val
        if tem > 9:
            promote = 1
            tem = tem % 10
        res = ListNode(tem)
        org = res
        while (True):
            tem = 0
            if (not l1.next) and (not l2.next):
                if promote == 1:
                    res.next = ListNode(1)
                break
            res.next = ListNode(0)
            res = res.next
            if promote == 1:
                res.val = 1
            if not l2.next:
                l1 = l1.next
                tem = l1.val
            elif not l1.next:
                l2 = l2.next
                tem = l2.val
            else:
                l1 = l1.next
                l2 = l2.next
                tem = l1.val + l2.val
            sums = res.val + tem
            if sums > 9:
                promote = 1
                sums = sums % 10
            else:
                promote = 0
            res.val = sums
        return org
