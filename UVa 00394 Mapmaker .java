import java.io.*;
import java.util.*;

class Main{
    static InputReader reader;
    static OutputWriter writer;

    public static void main(String[] args){
        reader = new InputReader(System.in);
        writer = new OutputWriter(System.out);

        HashMap<String, Integer[]> map = new HashMap<String, Integer[]>();
        int n = reader.nextInt();
        int r = reader.nextInt();
        String name;
        int base, dimen, lowers[], uppers[], elSize;
        Integer[] coeffs;
        while(n-->0){
            name = reader.next();
            base = reader.nextInt();
            elSize = reader.nextInt();
            dimen = reader.nextInt();
            lowers = new int[dimen];
            uppers = new int[dimen];
            for(int i=0;i<dimen;i++){
                lowers[i] = reader.nextInt();
                uppers[i] = reader.nextInt();
            }
            coeffs = new Integer[dimen+1];
            coeffs[dimen] = elSize;
            for(int i = dimen-1;i>0;i--){
                coeffs[i] = coeffs[i+1] * (uppers[i] - lowers[i] + 1);
            }
            coeffs[0] = base;
            for(int i=1;i<=dimen;i++){
                coeffs[0] -= coeffs[i] * lowers[i-1];
            }
            map.put(name, coeffs);
        }
        int[] indexes;
        int sum;
        while(r-->0){
            name = reader.next();
            coeffs = map.get(name);
            indexes = new int[coeffs.length-1];
            sum = coeffs[0];
            for(int i=0;i<indexes.length;i++){
                indexes[i] = reader.nextInt();
                sum += coeffs[i+1] * indexes[i];
            }
            writer.println("%s[%s] = %d", name, join(indexes), sum);
        }
        writer.flush();
    }
    static String join(int[] indexes){
        StringBuilder sb = new StringBuilder();
        sb.append(indexes[0]);
        for(int i=1;i<indexes.length;i++){
            sb.append(", ").append(indexes[i]);
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
