import java.io.*;
import java.util.*;

class Main{
    static InputReader reader;
    static OutputWriter writer;

    public static void main(String[] args){
        reader = new InputReader(System.in);
        writer = new OutputWriter(System.out);
        int m, n, t, c, s;
        int cube[][];
        m = reader.nextInt();
        while(m-->0){
            n = reader.nextInt();
            cube = new int[n][n];
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    cube[i][j] = reader.nextInt();
                }
            }
            t = (n + 1)/2;
            for(int i=0;i<t;i++){
                c = reader.nextInt();
                for(int j=0;j<c;j++){
                    s = reader.nextInt();
                    transform(cube, n, i, s);
                }
            }
            for(int i=0;i<n;i++){
                writer.println(join(cube[i]));
            }
        }
        writer.flush();
    }

    private static void transform(int[][] cube, int n, int t, int s) {
        switch(s){
            case 1:
                //upside down
                for(int k=t;k<n/2;k++){
                    swap(cube, k, t, n-k-1, t);
                    swap(cube, k, n-t-1, n-k-1, n-t-1);
                }
                for(int k=t+1;k<n-t-1;k++){
                    swap(cube, t, k, n-t-1, k);
                }
                break;
            case 2:
                //left right
                for(int k=t;k<n/2;k++){
                    swap(cube, t, k, t, n-k-1);
                    swap(cube, n-t-1, k, n-t-1, n-k-1);
                }
                for(int k=t+1;k<n-t-1;k++){
                    swap(cube, k, t, k, n-t-1);
                }
                break;
            case 3:
                //main diagonal
                for(int k=t+1;k<=n-t-1;k++) {
                    swap(cube, t, k, k, t);
                    swap(cube, k, n - t - 1, n - t - 1, k);
                }
                break;
            case 4:
                //main inverse diagonal
                for(int k=t;k<n-t-1;k++) {
                    swap(cube, t, k, n - k - 1, n - t - 1);
                }
                for(int k=t+1;k<n-t-1;k++) {
                    swap(cube, k, t, n-t-1, n-k-1);
                }
                break;
        }
    }

    static void swap(int[][] c, int i, int j, int k, int l){
        int temp = c[i][j];
        c[i][j] = c[k][l];
        c[k][l] = temp;
    }

    static String join(int[] c){
        StringBuilder sb = new StringBuilder();
        sb.append(c[0]);
        for(int i=1;i<c.length;i++){
            sb.append(" ").append(c[i]);
        }
        return sb.toString();
    }
    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream));
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (Exception e) {
                    return null;
                }
            }
            return tokenizer.nextToken();
        }
        public String nextLine(){
            if(tokenizer != null && tokenizer.hasMoreTokens()){
                StringBuilder sb = new StringBuilder(tokenizer.nextToken());
                while(tokenizer.hasMoreTokens()){
                    sb.append(" ").append(tokenizer.nextToken());
                }
                return sb.toString();
            }else{
                try {
                    return reader.readLine();
                }catch(Exception e){
                    return null;
                }
            }
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }
    }

    static class OutputWriter {
        public BufferedWriter writer;
        OutputWriter(OutputStream stream) {
            writer = new BufferedWriter(new OutputStreamWriter(stream));
        }
        public void print(String string){
            try {
                writer.write(string, 0, string.length());
            }catch(Exception e){e.printStackTrace();}
        }
        public void print(char c){
            try {
                writer.write(c);
            }catch(Exception e){e.printStackTrace();}
        }
        public void print(String format, Object... args) {
            try {
                String string = String.format(format, args);
                writer.write(string, 0, string.length());
            }catch(Exception e){e.printStackTrace();}
        }
        public void print(int x){
            try {
                String string = String.format("%d", x);
                writer.write(string, 0, string.length());
            }catch(Exception e){e.printStackTrace();}
        }
        public void print(double x){
            try {
                int x_int = (int) (x * 100 + 1e-6); // add 1e-6 to avoid precision error when converting to int
                String string = String.format("%d.%02d", x_int/100, x_int%100);
                writer.write(string, 0, string.length());
            }catch(Exception e){e.printStackTrace();}
        }
        public void print(long x){
            try {
                String string = String.format("%d", x);
                writer.write(string, 0, string.length());
            }catch(Exception e){e.printStackTrace();}
        }
        public void println(String string){
            print(string);
            newline();
        }
        public void println(char c){
            print(c);
            newline();
        }
        public void println(String format, Object... args) {
            print(format, args);
            newline();
        }
        public void println(int x){
            print(x);
            newline();
        }
        public void println(long x){
            print(x);
            newline();
        }
        public void println(double x){
            print(x);
            newline();
        }
        public void println(){
            newline();
        }

        private void newline() {
            try {
                writer.newLine();
            }catch(Exception e){e.printStackTrace();}
        }
        private void flush(){
            try {
                writer.flush();
            }catch(Exception e){e.printStackTrace();}
        }
    }
}
