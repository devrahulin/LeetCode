class Solution(object):
    def intToRoman(self, num):
        """
        :type num: int
        :rtype: str
        """
        # Predefined values and their corresponding Roman symbols in descending order
        value_symbols = [
            (1000, "M"),
            (900, "CM"),
            (500, "D"),
            (400, "CD"),
            (100, "C"),
            (90, "XC"),
            (50, "L"),
            (40, "XL"),
            (10, "X"),
            (9, "IX"),
            (5, "V"),
            (4, "IV"),
            (1, "I"),
        ]

        roman_digits = []

        for value, symbol in value_symbols:
            if num == 0:
                break
            
            count = num // value
            if count:
                roman_digits.append(symbol * count)
                num %= value

        return "".join(roman_digits)