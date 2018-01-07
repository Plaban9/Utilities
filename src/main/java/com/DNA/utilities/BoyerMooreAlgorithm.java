package com.DNA.utilities;


public class BoyerMooreAlgorithm
{
    public int findPattern(String txt, String prn)  //Finding Best Matching Pattern
    {
        char[] text = txt.toCharArray();
        String pattern = prn.toString();
        int pos = indexOf(text, pattern);

        /*if (pos == -1)
            System.out.println("\n*--No Match--*\n");

        else
            System.out.println("Pattern found at position: " + pos);*/

        return pos;

    }

    public int indexOf(char[] text, String pattern)  //Function to calculate index of pattern substring
    {
        if (pattern.length() == 0)
            return 0;

        int charTable[] = makeCharTable(pattern);
        int offsettable[] = makeOffsetTable(pattern);

        for (int i = pattern.length() - 1, j ; i < text.length; )
        {
            for (j = pattern.length() - 1; pattern.charAt(j) == text[i]; --i, --j)
                if (j == 0)
                    return i;

            //i+=pattern.length - j; // For naive method
            i += Math.max(offsettable[pattern.length() - 1 - j], charTable[text[i]]);
        }

        return -1;
    }

    private int[] makeCharTable(String pattern) //Function to make the jump table based on the mismatched character information
    {
        final int ALPHABET_SIZE = 256;

        int[] table = new int[ALPHABET_SIZE];

        for (int i = 0; i < table.length; ++i)
            table[i] = pattern.length();

        for (int i=0; i < pattern.length() - 1; ++i)
            table[pattern.charAt(i)] = pattern.length() - 1 - i;

        return table;
    }

    private static int[] makeOffsetTable(String pattern)
    {
        int[] table = new int[pattern.length()];
        int lastPrefixPosition = pattern.length();

        for (int i = pattern.length() - 1; i >= 0; --i)
        {
            if (isPrefix(pattern, i + 1))
                lastPrefixPosition = i + 1;

            table[pattern.length() - 1 - i] = lastPrefixPosition - i + pattern.length() - 1;
        }

        for (int i = 0; i <pattern.length() -1; ++i)
        {
            int slen = suffixLength(pattern, i);
            table[slen] = pattern.length() - 1 - i +slen;
        }

        return table;
    }

    private static boolean isPrefix(String pattern, int p) //function to check if needle [p:end] a prefix of pattern
    {
        for (int i = p, j = 0;i < pattern.length(); ++i, ++j)
            if (pattern.charAt(i) != pattern.charAt(j))
                return false;

        return true;
    }

    private static int suffixLength(String pattern, int p) //function to return max. length of the substring ends at p and is a suffix
    {
        int len = 0;

        for (int i = p, j = pattern.length() - 1; i >= 0 && pattern.charAt(i) == pattern.charAt(j); --i, --j)
            len += 1;

        return len;
    }

    /*public static void main(String args[]) throws IOException
    {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("*--Boyer-Moore Algorithm Test--*");
        //System.out.print("\nEnter Text: ");

        //String  text = buffer.readLine();
        String text = null;

        //File file = new File("/home/plaban/Downloads/sequence.fasta");
        File file = new File("/home/plaban/Test_Boyer_Moore.fasta");
        //File file = new File("/home/plaban/Downloads/Fasta_sequence.fasta");

        long startRead = System.currentTimeMillis();
        try (BufferedReader br = new BufferedReader(new FileReader(file)))
        {
            String line;

            while ((line = br.readLine()) != null)
            {
                if (line.indexOf(">") == 0)
                    System.out.println(line);

                else
                {
                    //System.out.println(line);
                    text += line;
                    //System.out.println(text);
                }
            }
        }

        long endRead = System.currentTimeMillis();

        long elapsedRead = endRead - startRead;
        System.out.println("Time for Reading = " + elapsedRead +"ms");

        //System.out.print("\nEnter Pattern: ");

        //String pattern = br.readLine();
        String pattern = "AAAAACCAGACAGAATCATTCTCAGAAAATTCTTTGTGATGTGTGCGTT";

        BoyerMooreAlgorithm bm = new BoyerMooreAlgorithm();

        long startSearch = System.currentTimeMillis();
        bm.findPattern(text, pattern);
        long endSearch = System.currentTimeMillis();

        long elapsedSearch = endSearch - startSearch;

        System.out.println("Time for Searching = " + elapsedSearch +"ms");
    }*/
}
