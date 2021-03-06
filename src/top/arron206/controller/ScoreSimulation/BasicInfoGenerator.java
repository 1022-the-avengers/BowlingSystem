package top.arron206.controller.ScoreSimulation;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BasicInfoGenerator {
    public static LinkedList<String> getNameList(int amount) {
        LinkedList<String> nameList = new LinkedList<>();
        for (int i = 0; i < amount; ++i)
            nameList.add(getRandomName());
        return nameList;
    }

    public static LinkedList<String> getProvinceList(int amount) {
        LinkedList<String> provinceList = new LinkedList<>();
        for (int i = 0; i < amount; ++i)
            provinceList.add(getRandomProvince());
        return provinceList;
    }

    public static String getRandomName() {
        List<String> familyName = Arrays.asList(
                "赵", "钱", "孙", "李", "周", "吴", "郑", "王", "冯", "陈",
                "刘", "卫", "蒋", "沈", "韩", "杨", "朱", "秦", "尤", "许",
                "何", "吕", "施", "张", "孔", "曹", "严", "华", "金", "魏",
                "陶", "姜", "戚", "谢", "邹", "喻", "柏", "水", "窦", "章",
                "云", "苏", "潘", "葛", "奚", "范", "彭", "郎", "鲁", "韦",
                "昌", "马", "苗", "凤", "花", "方", "俞", "任", "袁", "柳",
                "酆", "鲍", "史", "唐", "费", "廉", "岑", "薛", "雷", "贺",
                "倪", "汤", "滕", "殷", "罗", "毕", "郝", "邬", "安", "常",
                "乐", "于", "时", "傅", "皮", "卞", "齐", "康", "伍", "余",
                "元", "卜", "顾", "孟", "平", "黄", "和", "穆", "萧", "尹");
        List<String> firstName = Arrays.asList(
                "诗云", "飞风", "寄灵", "书芹", "幼蓉", "以蓝", "笑寒", "忆寒", "秋烟", "芷巧",
                "水香", "映之", "醉波", "幻莲", "夜山", "芷卉", "向彤", "小玉", "幼南", "凡梦",
                "尔曼", "念波", "迎松", "青寒", "笑天", "涵蕾", "碧菡", "映秋", "盼烟", "忆山",
                "以寒", "寒香", "小凡", "代亦", "梦露", "映波", "友蕊", "寄凡", "怜蕾", "雁枫",
                "水绿", "曼荷", "笑珊", "寒珊", "谷南", "慕儿", "夏岚", "友儿", "小萱", "紫青",
                "鹏举", "濮存", "溥心", "璞瑜", "浦泽", "奇邃", "祺祥", "荣轩", "锐达", "睿慈",
                "绍祺", "圣杰", "晟睿", "思源", "斯年", "泰宁", "天佑", "同巍", "奕伟", "祺温",
                "文虹", "向笛", "心远", "欣德", "新翰", "兴言", "星阑", "修为", "旭尧", "炫明",
                "学真", "雪风", "雅昶", "阳曦", "烨熠", "英韶", "永贞", "咏德", "宇寰", "雨泽",
                "玉韵", "越彬", "蕴和", "哲彦", "振海", "正志", "子晋", "自怡", "德赫", "君平");
        return familyName.get(RandInteger.uniformRand(0, familyName.size() - 1)) + firstName.get(RandInteger.uniformRand(0, firstName.size() - 1));
    }

    public static String getRandomProvince() {
        List<String> province = Arrays.asList(
                "北京市", "天津市", "上海市", "重庆市", "河北省", "山西省",
                "辽宁省", "吉林省", "黑龙江省", "江苏省", "浙江省", "安徽省",
                "福建省", "江西省", "山东省", "河南省", "湖北省", "湖南省",
                "广东省", "海南省", "四川省", "贵州省", "云南省", "陕西省",
                "甘肃省", "青海省", "台湾省", "内蒙古自治区", "广西壮族自治区",
                "西藏自治区", "宁夏回族自治区", "新疆维吾尔自治区", "香港特别行政区", "澳门特别行政区");
        return province.get(RandInteger.uniformRand(0, province.size() - 1));
    }
}
