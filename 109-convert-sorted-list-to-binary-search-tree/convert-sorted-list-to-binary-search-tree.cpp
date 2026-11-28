class Solution {
    ListNode* curr;
    TreeNode* build(int left, int right) {
        if (left > right) return nullptr;
        int mid = left + (right - left) / 2;
        TreeNode* leftChild = build(left, mid - 1);
        TreeNode* root = new TreeNode(curr->val);
        curr = curr->next;
        root->left = leftChild;
        root->right = build(mid + 1, right);
        return root;
    }
public:
    TreeNode* sortedListToBST(ListNode* head) {
        int n = 0;
        for (ListNode* p = head; p; p = p->next) ++n;
        curr = head;
        return build(0, n - 1);
    }
};
