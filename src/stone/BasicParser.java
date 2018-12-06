package stone;

import stone.ast.*;
import static stone.Parser.rule;
import stone.Parser.Operators;
import java.util.HashSet;

public class BasicParser {
    HashSet<String> reserved = new HashSet<String>();
    Operators operators = new Operators();
    Parser expr0 = rule(); // 由于语法规则的定义是递归的，所以定义一个Parser对象，后面statement0同理。
    Parser primary = rule(PrimaryExpr.class)
            .or(rule().sep("(").ast(expr0).sep(")"),
                rule().number(NumberLiteral.class),
                    rule().identifier(Name.class, reserved),
                    rule().string(StringLiteral.class));
    Parser factor = rule().or(rule(NegativeExpr.class).sep("-").ast(primary), primary);
    Parser expr = expr0.expression(BinaryExpr.class, factor, operators);
    // 经过一系列处理后，将最终获得的对象(实际上为expr0)赋值给expr。
    Parser statement0 = rule();
    Parser block = rule(BlockStmnt.class)
            .sep("{").option(statement0).repeat(rule().sep(";", Token.EOL).option(statement0))
            .sep("}");
    Parser simple = rule(PrimaryExpr.class).ast(expr);
    Parser statement = statement0.or(
            rule(IfStmnt.class).sep("if").ast(expr).ast(block).option(rule().sep("else").ast(block)),
            rule(WhileStmnt.class).sep("while").ast(expr).ast(block), simple);

    Parser program = rule().or(statement, rule(NullStmnt.class)).sep(";", Token.EOL);

    public BasicParser(){
        reserved.add(";");
        reserved.add("}");
        reserved.add(Token.EOL);

        operators.add("=", 1, Operators.RIGHT);
        // 第二个参数是优先级，值越大优先级越高
        // 第三个参数是左右结合顺序，LEFT代表左结合，指的是两个相同运算符接连出现时左侧的那个优先级较高
        operators.add("==", 2, Operators.LEFT);
        operators.add(">", 2, Operators.LEFT);
        operators.add("<", 2, Operators.LEFT);
        operators.add("+", 3, Operators.LEFT);
        operators.add("-", 3, Operators.LEFT);
        operators.add("*", 4, Operators.LEFT);
        operators.add("/", 4, Operators.LEFT);
        operators.add("%", 4, Operators.LEFT);
    }
    public ASTree parse(Lexer lexer) throws ParseException{
        return program.parse(lexer);
    }
}
