package stone.ast;

import stone.Token;

public class Name extends ASTLeaf{ // 变量名
    public Name(Token t){ super(t);}
    public String name(){ return token().getText(); }
}
