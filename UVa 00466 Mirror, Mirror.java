import java.io.*;
import java.util.*;

class Main{
    static InputReader reader;
    static OutputWriter writer;

    public static void main(String[] args){
        reader = new InputReader(System.in);
        writer = new OutputWriter(System.out);

        int n;
        char[][] oldp, newp;
        boolean passed;
        int p = 0;
        while(true){
            p++;
            try {
                n = reader.nextInt();
                oldp = new char[n][n];
                newp = new char[n][n];
                for(int i=0;i<n;i++){
                    oldp[i] = reader.next().toCharArray();
                    newp[i] = reader.next().toCharArray();
                }
                //test preservation
                if (testPreservation(n, oldp, newp)){
                    writer.println("Pattern %d was preserved.", p);
                    continue;
                }
                //rotating 90
                if(test90(n, oldp, newp)){
                    writer.println("Pattern %d was rotated 90 degrees.", p);
                    continue;
                }
                //rotating 180
                if(test180(n, oldp, newp)){
                    writer.println("Pattern %d was rotated 180 degrees.", p);
                    continue;
                }
                //rotating 270
                if(test270(n, oldp, newp)){
                    writer.println("Pattern %d was rotated 270 degrees.", p);
                    continue;
                }
                //vertical reflection
                if(testReflect(n, oldp, newp)){
                    writer.println("Pattern %d was reflected vertically.", p);
                    continue;
                }
                //combination
                char temp;
                for(int i=0;i<n/2;i++){
                    for(int j=0;j<n;j++){
                        temp = oldp[i][j];
                        oldp[i][j] = oldp[n-i-1][j];
                        oldp[n-i-1][j] = temp;
                    }
                }
                //rotating 90
                if(test90(n, oldp, newp)){
                    writer.println("Pattern %d was reflected vertically and rotated 90 degrees.", p);
                    continue;
                }
                //rotating 180
                if(test180(n, oldp, newp)){
                    writer.println("Pattern %d was reflected vertically and rotated 180 degrees.", p);
                    continue;
                }
                //rotating 270
                if(test270(n, oldp, newp)){
                    writer.println("Pattern %d was reflected vertically and rotated 270 degrees.", p);
                    continue;
                }
                writer.println("Pattern %d was improperly transformed.", p);
            }catch(Exception e){break;}
        }
        writer.flush();
    }

    private static boolean testReflect(int n, char[][] oldp, char[][] newp) {
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++) {
                if(newp[i][j] != oldp[n-i-1][j]){
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean test90(int n, char[][] oldp, char[][] newp) {
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++) {
                if(newp[i][j] != oldp[n-j-1][i]){
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean test180(int n, char[][] oldp, char[][] newp) {
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++) {
                if(newp[i][j] != oldp[n-i-1][n-j-1]){
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean test270(int n, char[][] oldp, char[][] newp) {
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++) {
                if(newp[i][j] != oldp[j][n-i-1]){
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean testPreservation(int n, char[][] oldp, char[][] newp) {
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(oldp[i][j] != newp[i][j]){
                    return false;
                }
            }
        }
        return true;
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
