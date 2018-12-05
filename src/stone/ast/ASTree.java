package stone.ast;
import java.util.Iterator;

public abstract class ASTree implements Iterable<ASTree>{
    public abstract ASTree child(int i); // 返回第i个子节点
    public abstract int numChildren();// 返回子节点的个数(如果没有子节点则返回0
    public abstract Iterator<ASTree> children();
    // 返回一个用于遍历子节点的iterator
    public abstract String location();
    // 返回一个用于表示抽象语法树节点在程序内所处位置的字符串
    public Iterator<ASTree> iterator(){ return children(); }
    // 和children方法功能相同是个适配器，在将ASTree类转为Iterable类型时会使用该方法
}
