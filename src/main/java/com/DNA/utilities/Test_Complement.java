package com.DNA.utilities;


import com.datatorrent.api.Context;
import com.datatorrent.api.DefaultInputPort;
import com.datatorrent.api.DefaultOutputPort;
import com.datatorrent.api.Operator;
import com.datatorrent.common.util.BaseOperator;

public class Test_Complement extends BaseOperator implements Operator.ActivationListener
{
    private static int counter = 0;
    private String revGenSeq;

    Utilities util = new Utilities();

    public final transient DefaultOutputPort<String> outputPort= new DefaultOutputPort<>();

    public final transient DefaultInputPort<String> inputPort = new DefaultInputPort<String>()
    {
        @Override
        public void process(String line)
        {
            switch (counter % 4)
            {
                case 0: System.out.println("Seq_id :" + line);
                        ++counter;
                        break;

                case 1: revGenSeq = util.complementGenome(line);
                        ++counter;
                        outputPort.emit(revGenSeq);
                        break;

                case 2: System.out.println("Quality Score_id :" + line);
                        ++counter;
                        break;

                case 3: System.out.println("Quality Score :" + line);
                        ++counter;
                        break;
            }
        }
    };

    @Override
    public void activate(Context context)
    {

    }

    @Override
    public void deactivate()
    {

    }
}
