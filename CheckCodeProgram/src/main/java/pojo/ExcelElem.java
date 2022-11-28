package pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.data.WriteCellData;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class ExcelElem {
    @ExcelProperty("代码")
    private WriteCellData<String> code;
    @ExcelProperty("匹配库")
    private WriteCellData<String> match;
    @ExcelProperty("最大连续公共子串")
    private int continuousSub;
    @ExcelProperty("最大公共子串")
    private int maxSub;
    @ExcelProperty("连续子串占提交比")
    private double conCompareSubmit;
    @ExcelProperty("连续子串占匹配比")
    private double conCompareCheck;
    @ExcelProperty("最大子串占提交比")
    private double maxCompareSubmit;
    @ExcelProperty("最大子串占匹配比")
    private double maxCompareCheck;
    @ExcelProperty("最大子串乘积")
    private double mul;
    @ExcelProperty("比对HTML")
    private WriteCellData<String> HTMLPath;
}
