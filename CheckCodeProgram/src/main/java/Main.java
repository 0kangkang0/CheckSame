import com.alibaba.excel.EasyExcel;
import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import pojo.ExcelElem;

import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        long st = System.currentTimeMillis(), usePair = 0;

        /**
         * 配置文件信息
         * */
        String problemName = "幸运彩票";
        String htmlPath = "D:\\算法集训队\\第四周周测\\HTML\\" + problemName + "\\";
        File submit = new File("D:\\算法集训队\\第四周周测\\提交列表\\" + problemName);
        File check = new File("D:\\算法集训队\\第四周周测\\查重库\\" + problemName);
        String checkUrl = "D:\\算法集训队\\第四周周测\\Url\\" + problemName + "\\";
        String submitUrl = "../../提交列表/" + problemName + "/";
        String HTMLPath = "../../HTML/" + problemName + "/";
        File file = new File("D:\\算法集训队\\第四周周测\\查重结果\\" + problemName + "\\");
        File file1 = new File(htmlPath);
        boolean mkdirs = file1.mkdirs();
        boolean mkdirs1 = file.mkdirs();
        FileOutputStream excelOut = new FileOutputStream("D:\\算法集训队\\第四周周测\\查重结果\\" + problemName + "\\匹配度.xlsx");
        String excelss="D:\\算法集训队\\第四周周测\\查重结果\\" + problemName + "\\匹配度.xlsx";
        //获取所有提交和匹配文件
        File[] checks = check.listFiles();
        File[] submits = submit.listFiles();

        SXSSFWorkbook sheets = new SXSSFWorkbook();


        ArrayList<ExcelElem> excelElems = new ArrayList<>();
        CreateExcel excel = new CreateExcel();
        int ii = 1;
        //选择提交代码
        assert submits != null;
        for (File i : submits) {
            StringBuilder sSubmit = getFileString(i.getAbsolutePath());
            excel.file = submitUrl + i.getName();

            //选择匹配库
            assert checks != null;
            for (File j : checks) {
                StringBuilder sCheck = getFileString(j.getAbsolutePath());
                StringBuilder checkUrl1 = getFileString(checkUrl + j.getName());

                usePair += sCheck.length() + sSubmit.length();

                Match match = new Match(sSubmit.toString(), sCheck.toString());
                match.LCS2();
                double maxCompareToSubmit = 1.0 * match.lcs2 / sSubmit.length(),
                        maxCompareToCheck = 1.0 * match.lcs2 / sCheck.length(),
                        conCompareToSubmit = 1.0 * match.lcs / sSubmit.length(),
                        conCompareToCheck = 1.0 * match.lcs / sCheck.length();
                //写excel
                excel.setAll(i.getName(), j.getName(), String.format("%d", match.lcs), String.format("%d", match.lcs2),
                        String.format("%.2f", conCompareToSubmit), String.format("%.2f", conCompareToCheck),
                        String.format("%.2f", maxCompareToSubmit), String.format("%.2f", maxCompareToCheck),HTMLPath);
                excel.url= String.valueOf(checkUrl1);
                ExcelElem excelElem = excel.create();
                excelElems.add(excelElem);

                //写HTML文件

                match.getSubmitAndCheck();
                WriteHML writeHML = new WriteHML(htmlPath + i.getName().replace(".txt", "") + "_" +
                        j.getName().replace(".txt", "") + ".html", sSubmit.toString(), sCheck.toString(), match);
                writeHML.write();

                ii++;
            }
            EasyExcel.write(excelss, ExcelElem.class).inMemory(true).sheet("模板").doWrite(excelElems);

            System.out.println(ii);
        }

        excelOut.close();
        long en = System.currentTimeMillis();
        System.out.println(en - st);
        assert checks != null;
        System.out.println(usePair / (checks.length + submits.length));
    }

    public static StringBuilder getFileString(String s) throws IOException {
        BufferedReader brs = new BufferedReader(new FileReader(s));
        StringBuilder sSubmit = new StringBuilder();
        String m;
        while ((m = brs.readLine()) != null) {
            sSubmit.append(m).append("\n");
        }
        brs.close();
        return sSubmit;
    }
}
