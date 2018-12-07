package stone.ast;

import Sixth.Environment;
import stone.StoneException;
import stone.Token;

public class Name extends ASTLeaf{ // 变量名
    public Name(Token t){ super(t);}
    public String name(){ return token().getText(); }
    public Object eval(Environment env){
        Object value = env.get(name());
        if( value == null)
            throw new StoneException("undefined name:" + name(), this);
        else
            return value;
    }
}
