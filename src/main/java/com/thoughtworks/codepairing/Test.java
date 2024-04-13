package com.thoughtworks.codepairing;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;



class Result {

    /*
     * Complete the 'getVisibleProfilesCount' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER connection_nodes
     *  2. INTEGER_ARRAY connection_from
     *  3. INTEGER_ARRAY connection_to
     *  4. INTEGER_ARRAY queries
     */

    public static List<Integer> getVisibleProfilesCount(int connection_nodes, List<Integer> connection_from, List<Integer> connection_to, List<Integer> queries) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i = 0; i < connection_from.size(); i++) {
            int f = connection_from.get(i);
            int t = connection_from.get(i);
            graph.computeIfAbsent(f, k -> new HashSet<>()).add(t);
            graph.computeIfAbsent(t, k -> new HashSet<>()).add(f);

        }
        List<Integer> count = new ArrayList<>();
        for (int q : queries) {
            Set<Integer> visited = new HashSet<>();
            Queue<Integer> queue = new LinkedList<>();
            queue.add(q);
            visited.add(q);
            while (!queue.isEmpty()) {
                int c = queue.poll();
                for (int n : graph.getOrDefault(c, Collections.emptySet())) {
                    if (!visited.contains(n)) {
                        visited.add(n);
                        queue.add(n);
                    }
                }
            }
            count.add(visited.size() - 1);
        }
        return count;
    }

}

public class Test {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int connection_nodes = Integer.parseInt(bufferedReader.readLine().trim());

        int connection_fromCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> connection_from = IntStream.range(0, connection_fromCount).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine().replaceAll("\\s+$", "");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        int connection_toCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> connection_to = IntStream.range(0, connection_toCount).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine().replaceAll("\\s+$", "");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        int queriesCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> queries = IntStream.range(0, queriesCount).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine().replaceAll("\\s+$", "");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        List<Integer> result = Result.getVisibleProfilesCount(connection_nodes, connection_from, connection_to, queries);

        bufferedWriter.write(
                result.stream()
                        .map(Object::toString)
                        .collect(joining("\n"))
                        + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
