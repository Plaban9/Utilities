package com.DNA.utilities;


public class Utilities
{
    String complementGenome(String genome)
    {
        char nucleotide;
        String reverseGenome = "";

        genome = genome.trim();  //Remove leading and trailing white spaces

        for (int i = 0; i < genome.length(); ++i)
        {
            nucleotide = Character.toUpperCase(genome.charAt(i));

            if (nucleotide == 'A')
                reverseGenome = reverseGenome + "T";

            else if (nucleotide == 'T')
                reverseGenome = reverseGenome + "A";

            else if (nucleotide == 'G')
                reverseGenome = reverseGenome + "C";

            else if (nucleotide == 'C')
                reverseGenome = reverseGenome + "G";

        }

        return reverseGenome.trim(); //Remove leading and trailing white spaces before sending back

        /**Decrepated Logic
         * char genomeArray[] = genome.toCharArray();

        for (int i = 0; i < genomeArray.length; ++i)
        {
            if (( genomeArray[i] = Character.toUpperCase(genomeArray[i])) == 'A' )
                genomeArray[i] = 'T';

            else if (( genomeArray[i] = Character.toUpperCase(genomeArray[i])) == 'T' )
                genomeArray[i] = 'A';

            else if (( genomeArray[i] = Character.toUpperCase(genomeArray[i])) == 'G' )
                genomeArray[i] = 'C';

            else if (( genomeArray[i] = Character.toUpperCase(genomeArray[i])) == 'C' )
                genomeArray[i] = 'G';
        }


        return genomeArray.toString();*/
    }
}
