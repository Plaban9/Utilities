package com.DNA.utilities;

import com.datatorrent.api.Context;
import com.datatorrent.api.DefaultInputPort;
import com.datatorrent.api.DefaultOutputPort;
import com.datatorrent.api.Operator;
import com.datatorrent.common.util.BaseOperator;

public class SAMtoBAM extends BaseOperator implements Operator.ActivationListener
{
    private byte[] lineByteSAM = null;

    public final transient DefaultOutputPort<String> outputPort= new DefaultOutputPort<>();

    public final transient DefaultInputPort<String> inputPort = new DefaultInputPort<String>()
    {
        @Override
        public void process(String line)
        {
            lineByteSAM = line.getBytes();

            for(byte b : lineByteSAM)
                outputPort.emit(Integer.toBinaryString(b));
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
