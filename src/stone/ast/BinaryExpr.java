package stone.ast;
import Sixth.Environment;
import stone.StoneException;

import java.util.List;

public class BinaryExpr extends ASTList{ // 该对象用于表达双目运算表达式
    public static final int TRUE = 1;
    public static final int FALSE = 0;
    public BinaryExpr(List<ASTree> c){ super(c); }
    public ASTree left(){ return child(0); }
    public String operator(){
        return ((ASTLeaf) child(1)).token().getText();
        // 把运算符算作独立的节点(ASTLeaf对象)
        // 该方法将从与运算符对应的ASTLeaf对象中获取单词，并返回其中的字符串
    }
    public ASTree right(){ return child(2); }
    public Object eval(Environment env){
        String op = operator();
        if ("=".equals(op)){
            Object right = right().eval(env);
            return computeAssign(env, right);
        }
        else{
            Object left = left().eval(env);
            Object right = right().eval(env);
            return computeOp(left, op, right);
        }
    }
    protected Object computeAssign(Environment env, Object rvalue){
        ASTree l = left();
        if(l instanceof Name){
            env.put(((Name)l).name(), rvalue);
            return rvalue;
        }
        else
            throw new StoneException("bad assignment", this);
    }
    protected Object computeOp(Object left, String op, Object right) {
        if (left instanceof Integer && right instanceof Integer) {
            return computeNumber((Integer) left, op, (Integer) right);
        } else
        if (op.equals("+"))
            return String.valueOf(left) + String.valueOf(right);
        else if (op.equals("==")) {
            if (left == null)
                return right == null ? TRUE : FALSE;
            else
                return left.equals(right) ? TRUE : FALSE;
        }
        else
            throw new StoneException("bad type", this);
    }
    protected Object computeNumber(Integer left, String op, Integer right){
        int a = left.intValue();
        int b = right.intValue();
        if (op.equals("+"))
            return a + b;
        else if (op.equals("-"))
            return a - b;
        else if (op.equals("*"))
            return a * b;
        else if (op.equals("/"))
            return a / b;
        else if (op.equals("%"))
            return a % b;
        else if (op.equals("=="))
            return a == b ? TRUE : FALSE;
        else if (op.equals(">"))
            return a > b ? TRUE : FALSE;
        else if (op.equals("<"))
            return a < b ? TRUE : FALSE;
        else
            throw new StoneException("bad operator", this);
    }
}
