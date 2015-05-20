import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.TreeMap;

class Main{
    static InputReader reader;
    static OutputWriter writer;

    public static void main(String[] args){
        reader = new InputReader(System.in);
        writer = new OutputWriter(System.out);
        int n = reader.nextInt();
        String tournament;
        int t, g;
        String line, teamname1, teamname2;
        int score1, score2;
        while(true){
            TreeMap<String, Team> map = new TreeMap<String, Team>();
            ArrayList<Team> teams = new ArrayList<Team>();
            tournament = reader.nextLine();
            t = reader.nextInt();
            while(t-->0){
                line = reader.nextLine();
                Team team = new Team(line);
                map.put(line, team);
                teams.add(team);
            }
            g = reader.nextInt();
            while(g-->0){
                line = reader.nextLine();
                String[] parts = line.split("[#@]");
                teamname1 = parts[0];
                teamname2 = parts[3];
                score1 = Integer.parseInt(parts[1]);
                score2 = Integer.parseInt(parts[2]);
                map.get(teamname1).addGame(score1, score2);
                map.get(teamname2).addGame(score2, score1);
            }
            Collections.sort(teams);
            writer.println(tournament);
            for(int i=1;i<=teams.size();i++){
                Team team = teams.get(i-1);
                writer.println("%d) %s %dp, %dg (%d-%d-%d), %dgd (%d-%d)", i, team.name, team.points, team.gp, team.wins, team.ties, team.loses, team.gd, team.gs, team.ga);
            }
            if(--n>0) {
                writer.println();
            }else{
                break;
            }
        }
        writer.flush();
    }
    static class Team implements Comparable {
        public String name;
        public int points, gp, wins, ties, loses, gd, gs, ga;
        public Team(String name) {
            this.name = name;
        }
        public void addGame(int gs, int ga){
            this.gs += gs;
            this.ga += ga;
            this.gd += (gs - ga);
            this.gp ++;
            if(gs>ga){
                wins ++;
                points += 3;
            }else if(gs < ga){
                loses ++;
            }else{
                ties ++;
                points ++;
            }
        }
        @Override
        public int compareTo(Object o) {
            if(o instanceof Team){
                Team t = (Team) o;
                if(points > t.points)   return -1;
                if(points < t.points)  return 1;
                if(wins > t.wins)       return -1;
                if(wins < t.wins)       return 1;
                if(gd > t.gd)           return -1;
                if(gd < t.gd)           return 1;
                if(gs > t.gs)           return -1;
                if(gs < t.gs)           return 1;
                if(gp < t.gp)           return -1;
                if(gp > t.gp)           return 1;
                return name.toLowerCase().compareTo(t.name.toLowerCase());
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