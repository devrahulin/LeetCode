class Solution:
    def canAliceWin(self, n: int) -> bool:
        take = 10
        alice_turn = True
        while n >= take and take > 0:
            n -= take
            take -= 1
            alice_turn = not alice_turn
        return not alice_turn
