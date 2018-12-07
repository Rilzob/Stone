package stone.ast;

import Seventh.Function;
import Sixth.Environment;
import stone.StoneException;

import java.util.List;

public class Arguments extends Postfix{
    public Arguments(List<ASTree> c){ super(c); }
    public int size(){ return numChildren(); }
    public Object eval(Environment callerEnv, Object value) {
        if (!(value instanceof Function))
            throw new StoneException("bad function", this);
        Function func = (Function)value;
        ParameterList params = func.parameters();
        if (size() != params.size())
            throw new StoneException("bad number of arguments", this);
        Environment newEnv = func.makeEnv(); // 用于记录函数的参数及函数内部使用的局部变量
        int num = 0;
        for (ASTree a: this)
            params.eval(newEnv, num++, a.eval(callerEnv));
        return func.body().eval(newEnv);
    }
}
