package main;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import model.Edge;

public class Main {

    public static void main(String[] args) throws IOException{
        String everything;
        ArrayList<Edge> edges = new ArrayList<Edge>();
        int idi = 0;
        BufferedReader br = new BufferedReader(new FileReader("test01.uwg"));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                if(line.length() == 1) {
                    line = br.readLine();
                    continue;
                }
                String[] split = line.split("\\s+");

                edges.add(new Edge(Integer.parseInt(split[0]),
                        Integer.parseInt(split[1]),
                        Integer.parseInt(split[2]),
                        idi));
                idi++;

                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            everything = sb.toString();
        } finally {
            br.close();
        }
        //Set mirrorWeight
        for(int i = 0; i < edges.size(); i++) {
            edges.get(i).setMirrorWeight(edges.get(edges.size() - 1 - i).getWeight());
        }


        for(int i = 0; i < edges.size(); i++) {
            System.out.println(edges.get(i).getWeight());
            System.out.println(edges.get(i).getMirrorWeight());
        }

        System.out.println(everything);
    }


    private void MFSFT() {
        Stack<Edge> stack = new Stack<Edge>();

    }
}
