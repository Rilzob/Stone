package Seventh;

import Sixth.Environment;
import stone.ast.BlockStmnt;
import stone.ast.ParameterList;

public class Function{
    protected ParameterList parameters;
    protected BlockStmnt body;
    protected Environment env;
    public Function(ParameterList parameters, BlockStmnt body, Environment env){
        this.parameters = parameters;
        this.body = body;
        this.env = env;
    }
    public ParameterList parameters(){ return parameters; }
    // 返回形参序列
    public BlockStmnt body(){ return body; }
    public Environment makeEnv(){ return new NestedEnv(env); }
    @Override
    public String toString(){ return "<fun:" + hashCode() + ">"; }
}
