//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ssb.warmline.business.commons.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class OrderNumber {
    public OrderNumber() {
    }

    public static String generateNumber() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("ddHHmmss");
        String str = sdf.format(date);
        return str;
    }

    public static String generateNumber1() {
        Random random = new Random();
        String fourRandom = String.valueOf(random.nextInt(1000000));
        int randLength = fourRandom.length();
        if(randLength < 6) {
            for(int i = 1; i <= 6 - randLength; ++i) {
                fourRandom = "0" + fourRandom;
            }
        }

        return fourRandom;
    }

    public static String generateNumber3() {
        String generateNumber3 = generateNumber() + generateNumber1();
        return generateNumber3;
    }

    public static void main(String[] args) {
        System.out.println(generateNumber3());
    }
}
