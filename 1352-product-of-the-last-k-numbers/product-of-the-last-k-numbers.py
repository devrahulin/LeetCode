class ProductOfNumbers:
    def __init__(self):
        # Start with 1 as a neutral base for multiplication
        self.prefix = [1]

    def add(self, num: int) -> None:
        if num == 0:
            # Reset on zero; anything including this element has a product of 0
            self.prefix = [1]
        else:
            self.prefix.append(self.prefix[-1] * num)

    def getProduct(self, k: int) -> int:
        # If k is >= len(self.prefix), a 0 was added within the last k numbers
        if k >= len(self.prefix):
            return 0
        return self.prefix[-1] // self.prefix[-1 - k]
        