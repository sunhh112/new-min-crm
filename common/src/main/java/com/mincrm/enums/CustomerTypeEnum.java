package com.mincrm.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import org.apache.groovy.util.Maps;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.mincrm.util.StrConstant.*;


/***
 metasource enum
 */

@Getter
@AllArgsConstructor
public enum CustomerTypeEnum {
    vip("vip", "VIP"),
    silver("silver", "白银"),
    gold("gold", "黄金"),
    diamond("diamond", "钻石"),
    ;

    /**
     * 获取原数据编码
     */
    private final String code;
    /**
     * 描述
     */
    private final String name;

    /**
     * 根据code查找
     * @param code 枚举code
     * @return 枚举对象
     */
    public static CustomerTypeEnum findEnumByCode(String code) {
        for (CustomerTypeEnum statusEnum : CustomerTypeEnum.values()) {
            if (statusEnum.getCode().equals(code)) {
                //如果需要直接返回name则更改返回类型为String,return statusEnum.name;
                return statusEnum;
            }
        }
        return null;
    }

    public static boolean isExists(String ruleCode) {
        return Objects.nonNull(findEnumByCode(ruleCode));
    }

    /**
     * 转为数据
     * @return 枚举对象数组
     */
    public static List<Map<String, String>> getKV() {
        List<Map<String, String>> list = new ArrayList<>();
        for (CustomerTypeEnum item : CustomerTypeEnum.values()) {
            list.add(Maps.of(VAL,item.getCode(),TEXT,item.getName()));
        }
        return list;
    }

    public static List<String> listCode() {
        List<String> list = new ArrayList<>();
        for (CustomerTypeEnum item : CustomerTypeEnum.values()) {
            list.add(item.getCode());
        }
        return list;
    }

}
