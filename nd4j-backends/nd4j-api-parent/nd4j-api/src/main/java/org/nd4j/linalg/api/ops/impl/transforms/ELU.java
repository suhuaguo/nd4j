/*-
 *
 *  * Copyright 2015 Skymind,Inc.
 *  *
 *  *    Licensed under the Apache License, Version 2.0 (the "License");
 *  *    you may not use this file except in compliance with the License.
 *  *    You may obtain a copy of the License at
 *  *
 *  *        http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *    Unless required by applicable law or agreed to in writing, software
 *  *    distributed under the License is distributed on an "AS IS" BASIS,
 *  *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *    See the License for the specific language governing permissions and
 *  *    limitations under the License.
 *
 *
 */

package org.nd4j.linalg.api.ops.impl.transforms;

import org.nd4j.autodiff.functions.DifferentialFunction;
import org.nd4j.autodiff.samediff.SameDiff;
import org.nd4j.imports.NoOpNameFoundException;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.api.ops.BaseTransformOp;

import java.util.Collections;
import java.util.List;

/**
 * ELU: Exponential Linear Unit (alpha=1.0)<br>
 * Introduced in paper:<br>
 * Fast and Accurate Deep Network Learning by Exponential Linear Units (ELUs)<br>
 * Djork-Arné Clevert, Thomas Unterthiner, Sepp Hochreiter (2015)<br>
 * <a href="http://arxiv.org/abs/1511.07289">http://arxiv.org/abs/1511.07289</a>
 *
 * @author Alex Black
 */
public class ELU extends BaseTransformOp {
    public ELU(SameDiff sameDiff, DifferentialFunction i_v, boolean inPlace) {
        super(sameDiff, i_v, inPlace);
    }

    public ELU(SameDiff sameDiff, DifferentialFunction i_v, int[] shape, boolean inPlace, Object[] extraArgs) {
        super(sameDiff, i_v, shape, inPlace, extraArgs);
    }

    public ELU(SameDiff sameDiff, DifferentialFunction i_v, Object[] extraArgs) {
        super(sameDiff, i_v, extraArgs);
    }

    public ELU() {}

    public ELU(INDArray x, INDArray z) {
        super(x, z);
    }

    public ELU(INDArray x, INDArray z, long n) {
        super(x, z, n);
    }

    public ELU(INDArray x, INDArray y, INDArray z, long n) {
        super(x, y, z, n);
    }

    public ELU(INDArray x, INDArray y, INDArray z) {
        super(x, y, z, x.lengthLong());
    }

    public ELU(INDArray x) {
        super(x);
    }

    @Override
    public int opNum() {
        return 21;
    }

    @Override
    public String opName() {
        return "elu";
    }

    @Override
    public String onnxName() {
        throw new NoOpNameFoundException("No onnx op opName found for " +  opName());
    }

    @Override
    public String tensorflowName() {
        return "Elu";
    }

    @Override
    public List<DifferentialFunction> doDiff(List<DifferentialFunction> i_v) {
        DifferentialFunction ret = f().eluDerivative(arg());

        return Collections.singletonList(ret);
    }

}
