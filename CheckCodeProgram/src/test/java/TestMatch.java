import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellType;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.NoSuchElementException;

public class TestMatch {
    @Test
    public void testDPNum() throws IOException {
        File submit = new File("D:\\算法集训队\\第二周周测\\所有提交\\all");
        File[] submits = submit.listFiles();
        File check = new File("D:\\算法集训队\\第二周周测\\查重库");
        BufferedWriter bufferedWriter = new BufferedWriter(
                new FileWriter("D:\\算法集训队\\第二周周测\\所有提交\\新建文本文档3.txt"));
        File[] checks = check.listFiles();
        String htmlPath="D:\\算法集训队\\第二周周测\\HTML\\";
        assert submits != null;
        for (File i : submits) {
            BufferedReader brs = new BufferedReader(new FileReader(i.getAbsolutePath()));
            StringBuilder sSubmit = new StringBuilder();
            String m;
            while ((m = brs.readLine()) != null) {
                sSubmit.append(m).append("\n");
            }
            assert checks != null;
            for (File j : checks) {
                BufferedReader brc = new BufferedReader(new FileReader(j.getAbsolutePath()));
                StringBuilder sCheck = new StringBuilder();
                String n;
                while ((n = brc.readLine()) != null) {
                    sCheck.append(n).append("\n");
                }
                Match match = new Match(sSubmit.toString(), sCheck.toString());
                match.LCS2();
                match.getSubmitAndCheck();
                WriteHML writeHML = new WriteHML(htmlPath + i.getName().replace(".txt", "") + "_" +
                        j.getName().replace(".txt", "") + ".html", sSubmit.toString(), sCheck.toString(), match);
                writeHML.write();
            }
            System.out.println(i.getName());
        }
    }
    @Test
    public void test2() throws IOException {
        String htmlPath="D:\\算法集训队\\第二周周测\\HTML\\1589181303541489664\\";
        File submit = new File("D:\\算法集训队\\第二周周测\\所有提交\\1589181303541489664");
        File[] submits = submit.listFiles();
        File check = new File("D:\\算法集训队\\第二周周测\\查重库\\1589181303541489664");
        File[] checks = check.listFiles();
        BufferedWriter bufferedWriter = new BufferedWriter(
                new FileWriter("D:\\算法集训队\\第二周周测\\所有提交\\1589181303541489664\\"+"匹配度.txt"));
        assert submits != null;
        for (File i:submits){
            BufferedReader brs = new BufferedReader(new FileReader(i.getAbsolutePath()));
            StringBuilder sSubmit= new StringBuilder();
            String m;
            while ((m= brs.readLine())!=null){
                sSubmit.append(m).append("\n");
            }
            assert checks != null;
            for (File j:checks){
                BufferedReader brc = new BufferedReader(new FileReader(j.getAbsolutePath()));
                StringBuilder sCheck= new StringBuilder();
                String n;
                while ((n= brc.readLine())!=null){
                    sCheck.append(n).append("\n");
                }
                Match match = new Match(sSubmit.toString(), sCheck.toString());
                match.LCS2();
                double compareToSubmit=100.0*match.lcs2/sSubmit.length(),
                        compareToCheck=100.0*match.lcs2/sCheck.length();
                bufferedWriter.write(
                        i.getName()+"\t"+j.getName()+"\t"+match.lcs+"\t"+ match.lcs2+
                                "\t"+ String.format("%.2f",100.0*match.lcs/sCheck.length())+
                                "\t"+ String.format("%.2f",100.0*match.lcs/sSubmit.length())+
                                "\t"+String.format("%.02f",compareToSubmit)+
                                "\t"+String.format("%.02f",compareToCheck));
                bufferedWriter.newLine();
                bufferedWriter.flush();
                if(compareToCheck>80&&compareToSubmit>80){
                    match.getSubmitAndCheck();
                    WriteHML writeHML = new WriteHML(htmlPath + i.getName().replace(".txt", "") + "_" +
                            j.getName().replace(".txt", "") + ".html", sSubmit.toString(), sCheck.toString(), match);
                    writeHML.write();
                }
            }
            System.out.println(i.getName());
        }
    }
    @Test
    public void textExcel() throws IOException {
        byte[] bytes = "..\\..\\HTML\\1589181303541489664\\362黄绍炜3222704126_133.html".getBytes("GBK");
        FileOutputStream fileOutputStream = new FileOutputStream("D:\\算法集训队\\第二周周测\\查重结果\\1589181303541489664\\" + "test0.xls");
        HSSFWorkbook sheets = new HSSFWorkbook();
        HSSFSheet test0 = sheets.createSheet("test0");
        HSSFCreationHelper creationHelper = sheets.getCreationHelper();
        HSSFHyperlink hyperlink = creationHelper.createHyperlink(HyperlinkType.URL);
        hyperlink.setAddress("https://www.baidu.com/");
        HSSFRow row = test0.createRow(0);
        row.createCell(0).setHyperlink(hyperlink);
        row.createCell(0).setCellValue("百度");
        hyperlink = creationHelper.createHyperlink(HyperlinkType.FILE);
        hyperlink.setAddress(new String(bytes, StandardCharsets.ISO_8859_1));
        HSSFCell cell = row.createCell(1);
        cell.setCellType(CellType.STRING);
        cell.setHyperlink(hyperlink);
        cell.setCellValue("html");
        row = test0.createRow(1);
        row.createCell(0).setHyperlink(hyperlink);
        row.createCell(0).setCellValue("百度");
        hyperlink = creationHelper.createHyperlink(HyperlinkType.FILE);
        hyperlink.setAddress(new String(bytes, StandardCharsets.ISO_8859_1));
        cell = row.createCell(1);
        cell.setCellType(CellType.STRING);
        cell.setHyperlink(hyperlink);
        cell.setCellValue("html");
        sheets.write(fileOutputStream);
        fileOutputStream.close();
    }

    @Test
    public void testPair(){
        Pair[] pairs = new Pair[3];
        Pair[][] pairs1 = new Pair[3][3];
    }

    @Test
    public void JsonIsSame() throws IOException {
        StringBuilder fileString = getFileString("D:\\javacode\\PullLuoGuScoreboard\\json1.json");
        StringBuilder fileString1 = getFileString("D:\\javacode\\PullLuoGuScoreboard\\json2.json");
        Match match = new Match(fileString.toString(), fileString1.toString());
        match.LCS2();
        match.getSubmitAndCheck();
        WriteHML writeHML = new WriteHML("D:\\javacode\\PullLuoGuScoreboard\\compare.html", fileString.toString(), fileString1.toString(), match);
        writeHML.write();
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
