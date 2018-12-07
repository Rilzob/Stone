package Sixth;

import java.util.HashMap;

public class BasicEnv implements Environment{
    protected HashMap<String, Object> values; //存储变量的名称和值
    public BasicEnv(){ values = new HashMap<String,Object>(); }
    public void put(String name, Object value){ values.put(name, value); }
    // 添加新的名值对
    public Object get(String name){ return values.get(name); }
    // 以名称为键搜索哈希表
}
