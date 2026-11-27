var reverseBetween = function(head, left, right) {
    if (!head || left === right) return head;

    const dummy = new ListNode(0, head);
    let prev = dummy;

    for (let i = 1; i < left; i++) {
        prev = prev.next;
    }

    let curr = prev.next;
    for (let i = 0; i < right - left; i++) {
        const nextNode = curr.next;
        curr.next = nextNode.next;
        nextNode.next = prev.next;
        prev.next = nextNode;
    }

    return dummy.next;
};
