package com.DNA.utilities;

import com.datatorrent.api.Context;
import com.datatorrent.api.DefaultInputPort;
import com.datatorrent.api.DefaultOutputPort;
import com.datatorrent.api.Operator;
import com.datatorrent.common.util.BaseOperator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class Test_BoyerMooreAlgorithm extends BaseOperator implements Operator.ActivationListener
{
    private String smallRefGen; //Use only for small Reference Genome (set path in properties.xml)
    private String refGenPath;
    private String refName;
    private long totalElapsed = 0;
    private static int counter = 0;

    public final transient DefaultOutputPort<String> matchedOut= new DefaultOutputPort<>();

    public final transient DefaultOutputPort<String> unmatchedOut= new DefaultOutputPort<>();

    public final transient DefaultInputPort<String> inputPort = new DefaultInputPort<String>()
    {
        @Override
        public void process(String line)
        {
            if (counter % 4 == 1)
            {
                BoyerMooreAlgorithm bma = new BoyerMooreAlgorithm();

                long startSearch = System.currentTimeMillis();
                long pos = bma.findPattern(smallRefGen, line);
                long endSearch = System.currentTimeMillis();
                long elapsed = endSearch - startSearch;

                totalElapsed += elapsed;

                if (pos == -1)
                    unmatchedOut.emit("Genome: " + line + " | Position: Not Found |" + " Time elapsed: " + elapsed + "ms");

                else
                    matchedOut.emit("Genome: " + line + " | Position: " + pos + " | Time elapsed: " + elapsed + "ms");
            }

            ++counter;
        }
    };

    public String getRefGenPath()
    {
        return this.refGenPath;
    }

    public void setRefGenPath(String refGenPath)
    {
        this.refGenPath = refGenPath;
    }

    @Override
    public void activate(Context context)
    {
        File refGenFile = new File(getRefGenPath());

        try (BufferedReader br = new BufferedReader(new FileReader(refGenFile)))
        {
            String line;

            while ((line = br.readLine()) != null)
            {
                if (line.indexOf(">") == 0)  //Alternatively line.charAt(0) == '>'
                {
                    System.out.println(line);

                    refName = line.substring( 1, line.length());
                }

                else
                    smallRefGen += line;
            }

            System.out.println("Beginning search in " + refName);
        }

        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void deactivate()
    {
        System.out.println("Operation finished. Duration " + totalElapsed);
    }
}
