package stone.ast;

import java.util.Iterator;
import java.util.List;

public class ASTList extends ASTree{ // 含有树枝的节点对象的父类
    protected List<ASTree> children; // 该字段用于保存子节点的集合
    public ASTList(List<ASTree> list){ children = list; }
    public ASTree child(int i){ return children.get(i); }
    public int numChildren(){ return children.size(); }
    public Iterator<ASTree> children(){ return children.iterator(); }
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append('(');
        String sep = "";
        for(ASTree t: children){
            builder.append(sep);
            sep = " ";
            builder.append(t.toString());
        }
        return builder.append(')').toString();
    }
    public String location(){
        for(ASTree t: children){
            String s = t.location();
            if (s != null)
                return s;
        }
        return null;
    }
}
