/**
 * Put your copyright and license info here.
 */
package com.DNA.utilities;

import org.apache.apex.malhar.lib.fs.GenericFileOutputOperator;
import org.apache.apex.malhar.lib.fs.LineByLineFileInputOperator;
import org.apache.hadoop.conf.Configuration;

import com.datatorrent.api.annotation.ApplicationAnnotation;
import com.datatorrent.api.StreamingApplication;
import com.datatorrent.api.DAG;
import com.datatorrent.api.DAG.Locality;
import com.datatorrent.lib.io.ConsoleOutputOperator;

//@ApplicationAnnotation(name="BoyerMooreAlgortihm")
@ApplicationAnnotation(name="Complement") //for Test_Complement tool
public class Application implements StreamingApplication
{

  @Override
  public void populateDAG(DAG dag, Configuration conf)
  {


    /**For Test_BoyerMooreAlgorithm
    //Operators
    LineByLineFileInputOperator genomeInput = dag.addOperator("Genome_Input", LineByLineFileInputOperator.class);
    Test_BoyerMooreAlgorithm boyerMooreAlgorithm = dag.addOperator("Boyer_Moore_Algortihm", Test_BoyerMooreAlgorithm.class);
    GenericFileOutputOperator.StringFileOutputOperator resultWriterMatched = dag.addOperator("Result_Writer_Matched", GenericFileOutputOperator.StringFileOutputOperator.class);
    GenericFileOutputOperator.StringFileOutputOperator resultWriterUnmatched = dag.addOperator("Result_Writer_Unmatched", GenericFileOutputOperator.StringFileOutputOperator.class);
    //Stream(s)
    dag.addStream("to_Boyer_Moore_Algo", genomeInput.output, boyerMooreAlgorithm.inputPort);
    dag.addStream("to_Result_Writer_Unmatched", boyerMooreAlgorithm.unmatchedOut, resultWriterUnmatched.input);
    dag.addStream("to_Result_Writer_Matched", boyerMooreAlgorithm.matchedOut, resultWriterMatched.input);
     */



    // For Test_Complement
    //Operators
    LineByLineFileInputOperator fastqFile = dag.addOperator("FASTQ_File", LineByLineFileInputOperator.class);
    //SAMtoBAM samtoBAM = dag.addOperator("SAM_to_BAM", SAMtoBAM.class);
    Test_Complement test = dag.addOperator("Complement_Test", Test_Complement.class);
    GenericFileOutputOperator.StringFileOutputOperator complementWriter = dag.addOperator("Complement_Writer", GenericFileOutputOperator.StringFileOutputOperator.class);

    //Stream(s)
    dag.addStream("to_Complement_Test", fastqFile.output, test.inputPort);
    dag.addStream("to_Complement_Writer", test.outputPort, complementWriter.input);


  }
}
