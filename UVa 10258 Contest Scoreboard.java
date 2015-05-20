import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main{
    static InputReader reader;
    static OutputWriter writer;

    public static void main(String[] args){
        reader = new InputReader(System.in);
        writer = new OutputWriter(System.out);
        int n = reader.nextInt();
        reader.nextLine();
        Team[] teams;
        String submission, s[];
        int contestant, time, question;
        while(n-->0){
            teams = new Team[100];
            for(int i=0;i<100;i++){
                teams[i] = new Team(i+1);
            }
            while((submission = reader.nextLine()) != null && submission.length() > 0){
                s = submission.split(" ");
                contestant = Integer.parseInt(s[0]);
                question = Integer.parseInt(s[1]);
                time = Integer.parseInt(s[2]);
                teams[contestant-1].submit(question, time, s[3]);
            }
            Arrays.sort(teams);

            for(int i=0;i<100;i++){
                if(teams[i].submitted){
                    writer.println("%d %d %d", teams[i].teamNumber, teams[i].correct, teams[i].timePenalty);
                }
            }
            if(n>0) writer.println();
        }
        writer.flush();
    }
    static class Team implements Comparable<Team>{
        public int timePenalty, correct, teamNumber;
        public boolean[] answered;
        public int[] wrongCount;
        public boolean submitted;

        public Team(int teamNumber) {
            this.teamNumber = teamNumber;
            timePenalty = correct = 0;
            answered = new boolean[10];
            for(int i=0;i<10;i++)   answered[i] = false;
            wrongCount = new int[10];
            submitted = false;
        }
        public void submit(int question, int time, String status){
            submitted = true;
            if(status.equals("I")){
                if(!answered[question]){
                    wrongCount[question] ++;
                }
            }else if(status.equals("C")){
                if(!answered[question]){
                    answered[question] = true;
                    correct++;
                    timePenalty += time + wrongCount[question]*20;
                }
            }
        }
        @Override
        public int compareTo(Team t) {
            if(correct != t.correct)
                return t.correct - correct;
            if(timePenalty != t.timePenalty)
                return timePenalty - t.timePenalty;
            return teamNumber - t.teamNumber;
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
