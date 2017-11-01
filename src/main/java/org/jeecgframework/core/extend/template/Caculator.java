//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.core.extend.template;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Stack;
import java.util.Vector;
import org.jeecgframework.core.util.LogUtil;

public class Caculator {
    public static NumberFormat nfd = NumberFormat.getNumberInstance();
    public static NumberFormat nfi = NumberFormat.getNumberInstance();
    public static DecimalFormat dfd;
    public static DecimalFormat dfi;
    Stack<Caculator.Item> opStack = new Stack();
    Vector<Caculator.Item> calcStack = new Vector();

    static {
        dfd = (DecimalFormat)nfd;
        dfi = (DecimalFormat)nfi;
        dfd.applyPattern("######.00");
        dfi.applyPattern("######");
    }

    public Caculator() {
    }

    public String calc() {
        Stack tmp = new Stack();

        while(true) {
            while(!this.calcStack.isEmpty()) {
                Caculator.Item it = (Caculator.Item)this.calcStack.remove(0);
                if(!it.ops) {
                    tmp.push(it);
                } else {
                    double op2 = ((Caculator.Item)tmp.pop()).value;
                    double op1 = 0.0D;
                    if(!tmp.isEmpty()) {
                        op1 = ((Caculator.Item)tmp.pop()).value;
                    }

                    Caculator.Item newItem = new Caculator.Item();
                    newItem.ops = true;
                    switch(it.opVal.charValue()) {
                        case '*':
                            newItem.value = op1 * op2;
                            break;
                        case '+':
                            newItem.value = op1 + op2;
                        case ',':
                        case '.':
                        default:
                            break;
                        case '-':
                            newItem.value = op1 - op2;
                            break;
                        case '/':
                            newItem.value = op1 / op2;
                            if(newItem.value == -1.0D / 0.0 || newItem.value == 1.0D / 0.0 || (new Double(newItem.value)).toString().equals("NaN")) {
                                newItem.value = 0.0D;
                            }
                    }

                    tmp.push(newItem);
                }
            }

            Double result = Double.valueOf(((Caculator.Item)tmp.pop()).value);
            return getDtoI(result);
        }
    }

    public static String getDtoI(Double d) {
        double c = 0.0D;
        long b = Math.round(d.doubleValue() * 100.0D);
        if(b % 100L != 0L) {
            c = (double)b / 100.0D;
            return dfd.format(c);
        } else {
            c = (double)b / 100.0D;
            return dfi.format(c);
        }
    }

    public String transInfixToPosfix(String in) {
        char[] cin = in.toCharArray();
        StringBuffer buffer = new StringBuffer();

        for(int i = 0; i < cin.length; ++i) {
            Caculator.Item newItem = new Caculator.Item();
            newItem.opPriority = 1;
            newItem.ops = false;
            switch(cin[i]) {
                case '(':
                    newItem.ops = true;
                    newItem.opVal = Character.valueOf('(');
                    this.opStack.push(newItem);
                    break;
                case ')':
                    boolean meetClose = false;

                    while(!this.opStack.isEmpty()) {
                        Caculator.Item item = (Caculator.Item)this.opStack.peek();
                        if(item.ops && item.opVal.charValue() != 40) {
                            this.calcStack.add(item);
                            this.opStack.pop();
                            buffer.append(item.opVal);
                        } else if(item.ops) {
                            this.opStack.pop();
                            meetClose = true;
                            break;
                        }
                    }

                    if(!meetClose) {
                        LogUtil.info(in);
                        throw new RuntimeException();
                    }
                    break;
                case '*':
                    newItem.opPriority = 2;
                    newItem.ops = true;
                    newItem.opVal = Character.valueOf('*');
                    this.doOps(buffer, newItem);
                    break;
                case '+':
                    newItem.opPriority = 1;
                    newItem.ops = true;
                    newItem.opVal = Character.valueOf('+');
                    this.doOps(buffer, newItem);
                    break;
                case ',':
                case '.':
                default:
                    int j;
                    for(j = i; j < cin.length && (cin[j] >= 48 && cin[j] <= 57 || cin[j] == 46 || cin[j] == 69); ++j) {
                        ;
                    }

                    if(j == i) {
                        throw new RuntimeException("wrong input.");
                    }

                    newItem.ops = false;

                    try {
                        newItem.value = Double.parseDouble(new String(cin, i, j - i));
                    } catch (NumberFormatException var9) {
                        var9.printStackTrace();
                        LogUtil.info("cal数字生成错误！！");
                    }

                    buffer.append(newItem.value);
                    this.calcStack.add(newItem);
                    i = j - 1;
                    break;
                case '-':
                    newItem.opPriority = 1;
                    newItem.ops = true;
                    newItem.opVal = Character.valueOf('-');
                    this.doOps(buffer, newItem);
                    break;
                case '/':
                    newItem.opPriority = 2;
                    newItem.ops = true;
                    newItem.opVal = Character.valueOf('/');
                    this.doOps(buffer, newItem);
            }
        }

        while(!this.opStack.isEmpty()) {
            Caculator.Item item = (Caculator.Item)this.opStack.pop();
            this.calcStack.add(item);
            buffer.append(item.opVal);
        }

        return buffer.toString();
    }

    private void doOps(StringBuffer buffer, Caculator.Item newItem) {
        while(true) {
            if(!this.opStack.isEmpty()) {
                Caculator.Item item = (Caculator.Item)this.opStack.peek();
                if(item.opVal.charValue() != 40 && item.opPriority >= newItem.opPriority) {
                    this.calcStack.add(item);
                    this.opStack.pop();
                    buffer.append(item.opVal);
                    continue;
                }
            }

            this.opStack.push(newItem);
            return;
        }
    }

    public static void main(String[] args) {
        Caculator calc = new Caculator();
        LogUtil.info(calc.transInfixToPosfix("1.3E7+0.5"));
        LogUtil.info("value is:" + calc.calc());
        Double dd = Double.valueOf(Double.parseDouble("1.35378957E7"));
        LogUtil.info(getDtoI(dd));
        LogUtil.info(dd);
    }

    class Item {
        boolean ops;
        double value;
        Character opVal;
        int opPriority;

        Item() {
        }
    }
}
