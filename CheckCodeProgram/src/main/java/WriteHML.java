import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WriteHML {
    private String path, submit, check;
    StringBuilder fSubmit, fCheck;
    Match match;

    public void write() throws IOException {
        append();
        submit=fSubmit.toString();
        check=fCheck.toString();
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path));
        submit=replace(submit);
        check=replace(check);
        String end =
                "</body>\n" +
                "</html>";
        String pre = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "    <style>\n" +
                "        html, body {\n" +
                "            height: 100%;\n" +
                "            margin: 0;\n" +
                "        }\n" +
                "\n" +
                "        .left {\n" +
                "            position: absolute;\n" +
                "            left: 0;\n" +
                "            right: 0;\n" +
                "            width: 50%;\n" +
                "            background: darkgray;\n" +
                "        }\n" +
                "\n" +
                "        .right {\n" +
                "            margin-left: 50%;\n" +
                "            background: gray;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div class=\"left\">\n" +
                "    <p style=\"font-size:16px;white-space: pre;\">";

       bufferedWriter.write(pre  + submit +"</p>\n</div>\n"+"<div class=\"right\">\n"+
               "    <p style=\"font-size:16px;white-space: pre;\">"+check+
               "</p>\n"+"</div>\n"+ end);
        bufferedWriter.close();
    }
    private String replace(String s){
        s = s.replace("<=", "&le;");
        s = s.replace(">=", "&ge;");
        s = s.replace("<", "&lt;");
        s = s.replace(">", "&gt;");
        s = s.replace("faeljkfont style=\"background: rgba(255,200,210,0.6)\"geauif", "<font style=\"background: rgba(255,200,210,0.6)\">");
        s = s.replace("hnjklafefontafnjk", "</font>");
        return s;
    }
    public void append() {
        String clStart = "faeljkfont style=\"background: rgba(255,200,210,0.6)\"geauif", clEnd = "hnjklafefontafnjk";
        ArrayList<Pair> submits = match.submits;
        ArrayList<Pair> checks = match.checks;
        fCheck = new StringBuilder();
        fSubmit = new StringBuilder();
        appendLoop(clStart, clEnd, submits, fSubmit, submit);
        appendLoop(clStart, clEnd, checks, fCheck, check);
    }

    private void appendLoop(String clStart, String clEnd, ArrayList<Pair> checks, StringBuilder fCheck, String check) {
        for (int i = 0; i < checks.size(); i++) {
            if(i!=0){
                fCheck.append(check, checks.get(i-1).second+1, checks.get(i).first);
            }else if(checks.get(i).first != 0)
                fCheck.append(check, 0, checks.get(i).first);
            fCheck.append(clStart).append(check,checks.get(i).first,checks.get(i).second+1).append(clEnd);
        }
    }

    public WriteHML() {
    }

    public WriteHML(String path, String submit, String check, Match match) {
        this.path = path;
        this.submit = submit;
        this.check = check;
        this.match = match;
    }
}
