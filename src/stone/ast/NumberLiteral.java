package stone.ast;

import stone.Token;

public class NumberLiteral extends ASTLeaf{ // 整型字面量
    public NumberLiteral(Token t){ super(t);}
    public int value(){ return token().getNumber(); }
    // 该字段通过ASTLeaf类的token字段完成定义，并非由各个类直接定义，Name类中的name字段同理
}
