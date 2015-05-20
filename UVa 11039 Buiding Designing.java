import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Main{
    static InputReader reader;
    static OutputWriter writer;

    public static void main(String[] args){
        reader = new InputReader(System.in);
        writer = new OutputWriter(System.out);
        int n = reader.nextInt();
        int f, s;
        int r, b, count, max;
        boolean rb;
        ArrayList<Integer> red, blue;
        while(n-->0){
            f = reader.nextInt();
            red = new ArrayList<Integer>();
            blue = new ArrayList<Integer>();
            for(int i=0;i<f;i++){
                s = reader.nextInt();
                if(s < 0)   red.add(-s);
                else        blue.add(s);
            }
            Collections.sort(red);
            Collections.sort(blue);
            r = red.size() - 1;
            b = blue.size() - 1;
            count = 0;
            max = Integer.MAX_VALUE;
            if(red.get(r) > blue.get(b)){
                rb = true;
            }else{
                rb = false;
            }
            while(true){
                if(rb){
                    if(r<0)
                        break;
                    while(r >= 0 && red.get(r) >= max)
                        r--;
                    if(r >= 0){
                        max = red.get(r);
                        count ++;
                        rb = false;
                    }
                }else{
                    if(b<0)
                        break;
                    while(b >= 0 && blue.get(b) >= max)
                        b--;
                    if(b >= 0){
                        max = blue.get(b);
                        count ++;
                        rb = true;
                    }
                }
            }
            writer.println(count);
        }
        writer.flush();
    }
    static class INT implements Comparable<INT>{
        public String number;

        public INT(String number) {
            this.number = number;
        }

        @Override
        public int compareTo(INT o) {
            char[] n1, n2;
            n1 = (number+o.number).toCharArray();
            n2 = (o.number+number).toCharArray();
            int i;
            for(i=0;i<n1.length;i++){
                if(n1[i] != n2[i])  return n2[i] - n1[i];
            }
            return 0;
        }
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
