package symbol;

import lexer.Tag;
import lexer.Word;

/**
 * 111
 */
public class Type extends Word {
    public int width = 0;//存储空间大小

    public Type(String s, int tag, int w) {
        super(s, tag);
        width = w;
    }

    public static final Type
            Int = new Type("int", Tag.BASIC, 4),
            Float = new Type("float", Tag.BASIC, 8),
            Char = new Type("char",Tag.BASIC,1),
            Bool = new Type("bool",Tag.BASIC,1);

    //下面两个函数可以用于类型转换
    public static boolean numeric(Type p) {
        if(p==Type.Char || p==Type.Int || p==Type.Float)
            return true;
        else
            return false;
    }

    //数字类型之间进行运算，所得类型要按照优先级
    public static Type max(Type p1,Type p2){
        if(!numeric(p1)||!numeric(p2))
            return null;
        else if(p1==Type.Float || p2==Type.Float)
            return Type.Float;
        else if(p1==Type.Int || p2==Type.Int)
            return Type.Int;
        else
            return Type.Char;
    }
}
