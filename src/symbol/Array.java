package symbol;

import lexer.Tag;

/**
 * 111
 */
public class Array extends Type {
    public Type of;//数组的元素类型
    public int size = 1;//数组中元素的个数
    public Array(int sz,Type p) {
        super("[]", Tag.INDEX,sz*p.width);
    }

    @Override
    public String toString() {
        return "[" + size + "]" + of.toString();
    }
}
