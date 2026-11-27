class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        int n = words.length;
        int index = 0;

        while (index < n) {
            int lineChars = words[index].length();
            int last = index + 1;
            while (last < n && lineChars + 1 + words[last].length() <= maxWidth) {
                lineChars += 1 + words[last].length();
                last++;
            }

            StringBuilder sb = new StringBuilder();
            int numWords = last - index;

            if (last == n || numWords == 1) {
                for (int i = index; i < last; i++) {
                    sb.append(words[i]);
                    if (i < last - 1) {
                        sb.append(' ');
                    }
                }
                while (sb.length() < maxWidth) {
                    sb.append(' ');
                }
            } else {
                int totalWordChars = lineChars - (numWords - 1);
                int totalSpaces = maxWidth - totalWordChars;
                int spacesBetween = totalSpaces / (numWords - 1);
                int extraSpaces = totalSpaces % (numWords - 1);

                for (int i = index; i < last; i++) {
                    sb.append(words[i]);
                    if (i < last - 1) {
                        int spacesToApply = spacesBetween + ((i - index) < extraSpaces ? 1 : 0);
                        for (int s = 0; s < spacesToApply; s++) {
                            sb.append(' ');
                        }
                    }
                }
            }

            result.add(sb.toString());
            index = last;
        }

        return result;
    }
}
