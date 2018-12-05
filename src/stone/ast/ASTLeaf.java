package stone.ast;

import stone.Token;

import java.util.ArrayList;
import java.util.Iterator;

public class ASTLeaf extends ASTree{ // 叶节点(不含树枝的节点)的父类
    private static ArrayList<ASTree> empty = new ArrayList<ASTree>();
    protected Token token;
    public ASTLeaf(Token t){ token = t; }
    public ASTree child(int i){ throw new IndexOutOfBoundsException(); }
    public int numChildren(){ return 0; } // 由于叶节点对象没有子节点，该方法始终返回0
    public Iterator<ASTree> children() { return empty.iterator(); } // 返回一个与空集合关联的Iterator对象
    public String toString(){ return token.getText(); }
    public String location(){ return "at line" + token.getLineNumber(); }
    public Token token(){ return token; }
}
