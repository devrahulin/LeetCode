function deleteDuplicates(head: ListNode | null): ListNode | null {
    const dummy = new ListNode(0, head);
    let prev = dummy;

    while (head !== null) {
        if (head.next !== null && head.val === head.next.val) {
            while (head.next !== null && head.val === head.next.val) {
                head = head.next;
            }
            prev.next = head.next;
        } else {
            prev = prev.next!;
        }
        head = head.next;
    }

    return dummy.next;
}
