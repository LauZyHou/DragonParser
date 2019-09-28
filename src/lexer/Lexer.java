package lexer;

import java.io.IOException;
import java.util.Hashtable;

/**
 * 词法分析器
 */
public class Lexer {
    public static int line = 1;
    char peek = ' ';
    Hashtable<String, Word> words = new Hashtable<String, Word>();//保留字

    void reserve(Word w) {
        words.put(w.lexme, w);
    }

    //在构造器中,将保留字存入哈希表
    public Lexer() {
        reserve(new Word("if", Tag.IF));
        reserve(new Word("else", Tag.ELSE));
        reserve(new Word("while", Tag.WHILE));
        reserve(new Word("do", Tag.DO));
        reserve(new Word("break", Tag.BREAK));
        reserve(Word.True);
        reserve(Word.False);
        reserve(Type.Int);
        reserve(Type.Char);
        reserve(Type.Bool);
        reserve(Type.Float);
    }

    //读入一个字符到peek
    void readch() throws IOException {
        peek = (char) System.in.read();
    }

    //重载
    boolean readch(char c) throws IOException {
        readch();
        if (peek != c)
            return false;
        peek = ' ';
        return true;
    }

    //读取内容
    public Token scan() throws IOException {
        //先跳过所有的空白符
        for (; ; readch()) {
            if (peek == ' ' || peek == '\t')
                continue;
            else if (peek == '\n')
                line = line + 1;
            else
                break;
        }
        //试图匹配两个字符的关系运算
        switch (peek) {
            case '&':
                if (readch('&'))
                    return Word.and;
                else
                    return new Token('&');
            case '|':
                if (readch('|'))
                    return Word.or;
                else
                    return new Token('|');
            case '=':
                if (readch('='))
                    return Word.eq;
                else
                    return new Token('=');
            case '!':
                if (readch('='))
                    return Word.ne;
                else
                    return new Token('!');
            case '<':
                if (readch('='))
                    return Word.le;
                else
                    return new Token('<');
            case '>':
                if (readch('='))
                    return Word.ge;
                else
                    return new Token('>');
        }
        //尝试读入一个数
        if (Character.isDigit(peek)) {
            int v = 0;
            //读取小数点前的部分(整数部分)
            do {
                v = v * 10 + Character.digit(peek, 10);
                readch();
            } while (Character.isDigit(peek));
            //如果是因为遇到小数点而结束,那么接下来要读小数部分,否则直接返回这个数
            if (peek != '.')
                return new Num(peek);//返回整数
            float x = v;//累加的部分
            float d = 10;//10表示得到的小数部分数字乘以0.1，下一位就是乘以0.01如此类推
            for (; ; ) {
                readch();
                if (!Character.isDigit(peek))
                    break;
                x = x + Character.digit(peek, 10) / d;
                d *= 10;
            }
            return new Real(peek);//返回实数
        }
        //尝试读入一个字母开头的字符串(可能是变量/标识符)
        if (Character.isLetter(peek)) {
            StringBuffer b = new StringBuffer();
            do {
                b.append(peek);
                readch();
            } while (Character.isLetterOrDigit(peek));
            String s = b.toString();
            Word w = words.get(s);
            if (w != null)
                return w;
            //如果words没有，说明是还没遇到的变量
            w = new Word(s, Tag.ID);
            words.put(s, w);
            return w;
        }
        //如果都不满足
        Token tok = new Token(peek);
        peek = ' ';
        return tok;
    }
}
