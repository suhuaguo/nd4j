package org.nd4j.linalg.api.ops.impl.broadcast;

import lombok.val;
import org.nd4j.autodiff.functions.DifferentialFunction;
import org.nd4j.autodiff.samediff.SameDiff;
import org.nd4j.linalg.api.ops.DynamicCustomOp;
import org.nd4j.linalg.exception.ND4JIllegalStateException;
import org.nd4j.linalg.util.ArrayUtil;
import org.tensorflow.framework.AttrValue;
import org.tensorflow.framework.GraphDef;
import org.tensorflow.framework.NodeDef;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class BiasAdd extends DynamicCustomOp {


    public BiasAdd() {}

    @Override
    public String opName() {
        return "biasadd";
    }

    @Override
    public void initFromTensorFlow(NodeDef nodeDef, SameDiff initWith, Map<String, AttrValue> attributesForNode, GraphDef graph) {
        super.initFromTensorFlow(nodeDef, initWith, attributesForNode, graph);

    }

    @Override
    public List<int[]> calculateOutputShape() {
        val args = args();
        for(int i = 0; i < args.length; i++)
            if(args[i].getResultShape() == null)
                throw new ND4JIllegalStateException("No  shape found for arg " + i + " !");
        val firstShape = ArrayUtil.prod(args[0].getResultShape());
        val secondShape = ArrayUtil.prod(args[1].getResultShape());

        if(firstShape > secondShape)
            return Arrays.asList(args[0].getResultShape());
        else
            return Arrays.asList(args[1].getResultShape());
    }

    @Override
    public List<DifferentialFunction> doDiff(List<DifferentialFunction> f1) {
        return null;
    }

    @Override
    public String onnxName() {
        return "BiasAdd";
    }

    @Override
    public String tensorflowName() {
        return "BiasAdd";
    }
}
