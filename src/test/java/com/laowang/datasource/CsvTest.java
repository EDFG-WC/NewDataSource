package com.laowang.datasource;

import com.laowang.datasource.utils.CsvUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CsvTest {
    public static void main(String[] args) throws IOException {
        LinkedList<List<String>> csvCells = new LinkedList<>();
        CsvUtils parser = new CsvUtils("D://file1.csv");
        List<String> heads = parser.getHeads();
        if (!heads.isEmpty()) {
            csvCells.add(heads);
        }
        for (List<String> record : parser) {
            final List<String> rows = new ArrayList<>(heads.size());
            record.forEach(value->{
                rows.add(value);
            });
            csvCells.add(rows);
        }
        System.out.println(csvCells);
    }
}
