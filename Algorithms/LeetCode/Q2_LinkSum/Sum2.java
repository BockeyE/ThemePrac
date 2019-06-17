package LeetCode.Q2_LinkSum;

/**
 * @author bockey
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * <p>
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * <p>
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <p>
 * 示例：
 * <p>
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Sum2 {
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int tempValue = 0;
        int promote = 0;
        tempValue = l1.val + l2.val;
        if (tempValue > 9) {
            promote = 1;
            tempValue = tempValue % 10;
        }
        ListNode res = new ListNode(tempValue);
        ListNode org = res;
        while (true) {
            tempValue = 0;
            if (l1.next == null && l2.next == null) {
                if (promote == 1) {
                    res.next = new ListNode(1);
                }
                break;
            }
            res.next = new ListNode(0);
            res = res.next;
            if (promote == 1) {
                res.val = 1;
            }
            if (l2.next == null) {
                l1 = l1.next;
                tempValue = l1.val;
            } else if (l1.next == null) {
                l2 = l2.next;
                tempValue = l2.val;
            } else {
                l1 = l1.next;
                l2 = l2.next;
                tempValue = l1.val + l2.val;
            }
            int sum = res.val + tempValue;
            if (sum > 9) {
                promote = 1;
                sum = sum % 10;
            } else {
                promote = 0;
            }
            res.val = sum;
        }
        return org;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    public static void main(String[] args) {

        ListNode l1 = new ListNode(2);
        ListNode org1 = l1;
        l1.next = new ListNode(4);
        l1 = l1.next;
        l1.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
        ListNode org2 = l2;
        l2.next = new ListNode(6);
        l2 = l2.next;
        l2.next = new ListNode(4);


        ListNode listNode = Sum2.addTwoNumbers(org1, org2);

        System.out.println(listNode.val);
        System.out.println(listNode.next.val);
        System.out.println(listNode.next.next.val);
    }
}