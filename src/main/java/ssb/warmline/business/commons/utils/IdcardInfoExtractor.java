//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ssb.warmline.business.commons.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class IdcardInfoExtractor {
    private String province;
    private String city;
    private String region;
    private int year;
    private int month;
    private int day;
    private String gender;
    private Date birthday;
    private Map<String, String> cityCodeMap = new HashMap<String, String>() {
        {
            this.put("11", "北京");
            this.put("12", "天津");
            this.put("13", "河北");
            this.put("14", "山西");
            this.put("15", "内蒙古");
            this.put("21", "辽宁");
            this.put("22", "吉林");
            this.put("23", "黑龙江");
            this.put("31", "上海");
            this.put("32", "江苏");
            this.put("33", "浙江");
            this.put("34", "安徽");
            this.put("35", "福建");
            this.put("36", "江西");
            this.put("37", "山东");
            this.put("41", "河南");
            this.put("42", "湖北");
            this.put("43", "湖南");
            this.put("44", "广东");
            this.put("45", "广西");
            this.put("46", "海南");
            this.put("50", "重庆");
            this.put("51", "四川");
            this.put("52", "贵州");
            this.put("53", "云南");
            this.put("54", "西藏");
            this.put("61", "陕西");
            this.put("62", "甘肃");
            this.put("63", "青海");
            this.put("64", "宁夏");
            this.put("65", "新疆");
            this.put("71", "台湾");
            this.put("81", "香港");
            this.put("82", "澳门");
            this.put("91", "国外");
        }
    };
    private IdcardValidator validator = null;

    public IdcardInfoExtractor(String idcard) {
        try {
            this.validator = new IdcardValidator();
            if(this.validator.isValidatedAllIdcard(idcard)) {
                if(idcard.length() == 15) {
                    idcard = this.validator.convertIdcarBy15bit(idcard);
                }

                String provinceId = idcard.substring(0, 2);
                Set<String> key = this.cityCodeMap.keySet();
                Iterator var5 = key.iterator();

                String id;
                while(var5.hasNext()) {
                    id = (String)var5.next();
                    if(id.equals(provinceId)) {
                        this.province = (String)this.cityCodeMap.get(id);
                        break;
                    }
                }

                id = idcard.substring(16, 17);
                if(Integer.parseInt(id) % 2 != 0) {
                    this.gender = "男";
                } else {
                    this.gender = "女";
                }

                String birthday = idcard.substring(6, 14);
                Date birthdate = (new SimpleDateFormat("yyyyMMdd")).parse(birthday);
                this.birthday = birthdate;
                GregorianCalendar currentDay = new GregorianCalendar();
                currentDay.setTime(birthdate);
                this.year = currentDay.get(1);
                this.month = currentDay.get(2) + 1;
                this.day = currentDay.get(5);
            }
        } catch (Exception var8) {
            var8.printStackTrace();
        }

    }

    public String getProvince() {
        return this.province;
    }

    public String getCity() {
        return this.city;
    }

    public String getRegion() {
        return this.region;
    }

    public int getYear() {
        return this.year;
    }

    public int getMonth() {
        return this.month;
    }

    public int getDay() {
        return this.day;
    }

    public String getGender() {
        return this.gender;
    }

    public Date getBirthday() {
        return this.birthday;
    }

    public String toString() {
        return "省份：" + this.province + ",性别：" + this.gender + ",出生日期：" + this.birthday;
    }

    public int getAge(Date birthDate) {
        if(birthDate == null) {
            throw new RuntimeException("出生日期不能为null");
        } else {
            //int age = 0;
            Date now = new Date();
            SimpleDateFormat format_y = new SimpleDateFormat("yyyy");
            SimpleDateFormat format_M = new SimpleDateFormat("MM");
            String birth_year = format_y.format(birthDate);
            String this_year = format_y.format(now);
            String birth_month = format_M.format(birthDate);
            String this_month = format_M.format(now);
            int age = Integer.parseInt(this_year) - Integer.parseInt(birth_year);
            if(this_month.compareTo(birth_month) < 0) {
                --age;
            }

            if(age < 0) {
                age = 0;
            }

            return age;
        }
    }

    public static void main(String[] args) {
        String idcard = "43062419891017801X";
        IdcardInfoExtractor ie = new IdcardInfoExtractor(idcard);
        System.out.println(ie.toString());
        System.out.println(ie.getAge(ie.getBirthday()));
    }
}
