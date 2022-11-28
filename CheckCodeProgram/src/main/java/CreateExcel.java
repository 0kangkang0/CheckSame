import com.alibaba.excel.metadata.data.HyperlinkData;
import com.alibaba.excel.metadata.data.WriteCellData;
import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellType;
import pojo.ExcelElem;

import javax.swing.text.Element;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.http.HttpClient;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

public class CreateExcel {
    private String code;
    private String match;
    private String continuousSub;
    private String maxSub;
    private String conCompareSubmit;
    private String conCompareCheck;
    private String maxCompareSubmit;
    private String maxCompareCheck;
    private String HTMLPath;
    HSSFSheet sheet;
    int row;
    String url;
    String file;
    public ExcelElem create() throws IOException {
        ExcelElem excelElem = new ExcelElem();
        WriteCellData<String> hyperlink = new WriteCellData<>(code);
        excelElem.setCode(hyperlink);
        HyperlinkData hyperlinkData = new HyperlinkData();
        hyperlink.setHyperlinkData(hyperlinkData);
        hyperlinkData.setAddress(file);
        hyperlinkData.setHyperlinkType(HyperlinkData.HyperlinkType.FILE);

        URL urll = new URL(url);
        WriteCellData<String> hyperlink1 = new WriteCellData<>(match);
        excelElem.setMatch(hyperlink1);
        HyperlinkData hyperlinkData1 = new HyperlinkData();
        hyperlink1.setHyperlinkData(hyperlinkData1);
        hyperlinkData1.setAddress(String.valueOf(urll));
        hyperlinkData1.setHyperlinkType(HyperlinkData.HyperlinkType.URL);
        
        excelElem.setContinuousSub(Integer.parseInt(continuousSub));
        excelElem.setMaxSub(Integer.parseInt(maxSub));
        excelElem.setMaxCompareSubmit(Double.parseDouble(maxCompareSubmit));
        excelElem.setMaxCompareCheck(Double.parseDouble(maxCompareCheck));
        excelElem.setConCompareCheck(Double.parseDouble(conCompareCheck));
        excelElem.setConCompareSubmit(Double.parseDouble(conCompareSubmit));
        excelElem.setMul(Double.parseDouble(maxCompareCheck)*Double.parseDouble(maxCompareSubmit));

        code = code.substring(0, code.length() - 4);
        match = match.substring(0, match.length() - 4);
        WriteCellData<String> hyperlink2 = new WriteCellData<>(code+"_"+match);
        excelElem.setHTMLPath(hyperlink2);
        HyperlinkData hyperlinkData2 = new HyperlinkData();
        hyperlink2.setHyperlinkData(hyperlinkData2);
        hyperlinkData2.setAddress(HTMLPath+code+"_"+match+".html");
        hyperlinkData2.setHyperlinkType(HyperlinkData.HyperlinkType.FILE);

        return excelElem;
    }

    public CreateExcel() {
    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMatch() {
        return match;
    }

    public void setMatch(String match) {
        this.match = match;
    }

    public String getContinuousSub() {
        return continuousSub;
    }

    public void setContinuousSub(String continuousSub) {
        this.continuousSub = continuousSub;
    }

    public String getMaxSub() {
        return maxSub;
    }

    public void setMaxSub(String maxSub) {
        this.maxSub = maxSub;
    }

    public String getConCompareSubmit() {
        return conCompareSubmit;
    }

    public void setConCompareSubmit(String conCompareSubmit) {
        this.conCompareSubmit = conCompareSubmit;
    }

    public String getConCompareCheck() {
        return conCompareCheck;
    }

    public void setConCompareCheck(String conCompareCheck) {
        this.conCompareCheck = conCompareCheck;
    }

    public String getMaxCompareSubmit() {
        return maxCompareSubmit;
    }

    public void setMaxCompareSubmit(String maxCompareSubmit) {
        this.maxCompareSubmit = maxCompareSubmit;
    }

    public String getMaxCompareCheck() {
        return maxCompareCheck;
    }

    public void setMaxCompareCheck(String maxCompareCheck) {
        this.maxCompareCheck = maxCompareCheck;
    }

    public HSSFSheet getSheet() {
        return sheet;
    }

    public void setSheet(HSSFSheet sheet) {
        this.sheet = sheet;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setAll(String code, String match, String continuousSub, String maxSub, String conCompareSubmit, String conCompareCheck, String maxCompareSubmit, String maxCompareCheck,String HTMLPath){
        this.code = code;
        this.match = match;
        this.continuousSub = continuousSub;
        this.maxSub = maxSub;
        this.conCompareSubmit = conCompareSubmit;
        this.conCompareCheck = conCompareCheck;
        this.maxCompareSubmit = maxCompareSubmit;
        this.maxCompareCheck = maxCompareCheck;
        this.HTMLPath=HTMLPath;
    }

    public CreateExcel(String code, String match, String continuousSub, String maxSub, String conCompareSubmit, String conCompareCheck, String maxCompareSubmit, String maxCompareCheck) {
        this.code = code;
        this.match = match;
        this.continuousSub = continuousSub;
        this.maxSub = maxSub;
        this.conCompareSubmit = conCompareSubmit;
        this.conCompareCheck = conCompareCheck;
        this.maxCompareSubmit = maxCompareSubmit;
        this.maxCompareCheck = maxCompareCheck;
    }
}
