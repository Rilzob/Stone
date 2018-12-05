package stone.ast;
import java.util.List;

public class BinaryExpr extends ASTList{ // 该对象用于表达双目运算表达式
    public BinaryExpr(List<ASTree> c){ super(c); }
    public ASTree left(){ return child(0); }
    public String operator(){
        return ((ASTLeaf) child(1)).token().getText();
        // 把运算符算作独立的节点(ASTLeaf对象)
        // 该方法将从与运算符对应的ASTLeaf对象中获取单词，并返回其中的字符串
    }
    public ASTree right(){ return child(2); }
}
