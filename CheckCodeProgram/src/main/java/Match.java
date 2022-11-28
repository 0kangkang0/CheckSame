import java.util.*;

public class Match {
    private String s1 = "", s2 = "";
    private int m, n;
    int lcs = 0,lcs2=0;
    private int[][] dp,dp2;
    int [][]preFirst,preSecond;
    ArrayList<Pair> pair,submits,checks;
    ArrayList<Integer>submit,check;

    /*LCS*/
    public void LCS2() {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                        dp2[i][j] = 1;
                    } else {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                        dp2[i][j] = dp2[i - 1][j - 1] + 1;
                    }
                    preFirst[i][j]=i-1;
                    preSecond[i][j]=j-1;
                }else{
                    if(i==0&&j==0){
                        preFirst[i][j]=i;
                        preSecond[i][j]=j-1;
                    } else if(i == 0){
                        dp[i][j]=dp[i][j-1];
                        preFirst[i][j]=i;
                        preSecond[i][j]=j-1;
                    }else if(j == 0){
                        dp[i][j]=dp[i-1][j];
                        preFirst[i][j]=i-1;
                        preSecond[i][j]=j;
                    }else{
                        if(dp[i-1][j]<dp[i][j-1]){
                            dp[i][j]=dp[i][j-1];
                            preFirst[i][j]=i;
                            preSecond[i][j]=j-1;
                        }else {
                            dp[i][j]=dp[i-1][j];
                            preFirst[i][j]=i-1;
                            preSecond[i][j]=j;
                        }
                    }
                }
                lcs = Math.max(lcs, dp2[i][j]);
            }
        }
        lcs2=dp[m-1][n-1];
    }

    public void getSubmitAndCheck(){
        int i=m-1,j=n-1;
        while (i>=0&&j>=0){
            Pair p1 = new Pair(i, j);
            Pair p2 = new Pair(preFirst[i][j],preSecond[i][j]);
            if(p1.first-1==p2.first&&p1.second-1==p2.second){
                submit.add(i);
                check.add(j);
            }
            i=p2.first;
            j=p2.second;
        }
        submit.sort(Comparator.comparingInt(i2 -> i2));
        check.sort(Comparator.comparingInt(i2 -> i2));
        int st=submit.get(0);
        toPair(st, submit, submits);
        st=check.get(0);
        toPair(st, check, checks);
    }

    private void toPair(int st, ArrayList<Integer> check, ArrayList<Pair> checks) {
        for (int k = 1; k < check.size(); k++) {
            while (k<check.size()&&Objects.equals(check.get(k - 1), check.get(k)-1))
                k++;
            checks.add(new Pair(st, check.get(k-1)));
            if(k< check.size()){
                st = check.get(k);
            }
        }
        //checks.add(new Pair(st, check.get(check.size()-1)));
    }

    public Match() {
    }

    public Match(String s1, String s2) {
        this.s1 = s1;
        this.s2 = s2;
        m = s1.length();
        n = s2.length();
        dp = new int[m][n];
        dp2 = new int[m][n];
        pair = new ArrayList<>();
        submit=new ArrayList<>();
        check=new ArrayList<>();
        submits=new ArrayList<>();
        checks=new ArrayList<>();
        preFirst =new int[m][n];
        preSecond=new int[m][n];
        //distance = new HashMap<>();
    }

    public String getS1() {
        return s1;
    }

    public void setS1(String s1) {
        this.s1 = s1;
    }

    public String getS2() {
        return s2;
    }

    public void setS2(String s2) {
        this.s2 = s2;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int[][] getDp() {
        return dp;
    }

    public void setDp(int[][] dp) {
        this.dp = dp;
    }
}

class Pair {
    int first, second;

    public Pair() {
    }

    public Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pair pair = (Pair) o;

        if (first != pair.first) return false;
        return second == pair.second;
    }

    @Override
    public int hashCode() {
        int result = first;
        result = 31 * result + second;
        return result;
    }
}
