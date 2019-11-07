package com.laowang.datasource.utils;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class CsvUtils implements Iterable<List<String>> {
    private static final Logger LOGGER = LogManager.getLogger(CsvUtils.class);

    private CSVReader reader;

    private Deque<List<String>> bufferRows;

    private List<String> heads;

    private Map<String, Integer> headMap;

    private int maxHeadSize;

    public CsvUtils(String filePath) throws IOException {
        reader = new CSVReader(new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "GBK")));
        bufferRows = new LinkedList<List<String>>();
        initHeaders();
        cacheRows(100);
    }

    private void initHeaders() {
        heads = new ArrayList<>();
        headMap = new HashMap<>();
        for (int index = 0; index < 15; index++) {
            String[] row = readNext();
            if (row == null) {
                break;
            }
            if (!isInvalidRow(row, 3)) {
                for (int jndex = 0; jndex < row.length; jndex++) {
                    if (StringUtils.isEmpty(row[jndex])) {
                        row[jndex] = "None" + jndex;
                    }
                    heads.add(row[jndex]);
                    headMap.put(row[jndex], Integer.valueOf(jndex));
                }
                break;
            }
        }
        maxHeadSize = heads.size();
    }

    /**
     * 缓存i行数据
     *
     * @param cacheLines
     */
    private void cacheRows(int cacheLines) {
        for (int index = 0; index < cacheLines; index++) {
            String[] CacheList = readNext();
            if (CacheList != null) {
                cacheLine(CacheList);
            } else {
                break;
            }
        }
    }

    /**
     * 读取一行数据
     *
     * @return
     */
    private String[] readNext() {
        try {
            return reader.readNext();
        } catch (IOException | CsvValidationException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 判断列头是否有效
     *
     * @param rows
     * @param effectiveLength
     * @return
     */
    private boolean isInvalidRow(String[] rows, int effectiveLength) {
        if (rows == null || rows.length < effectiveLength) {
            return true;
        }
        int effectiveValueLength = 0;
        for (String row : rows) {
            if (StringUtils.isNotEmpty(row)) {
                effectiveValueLength++;
            }
        }
        if (effectiveValueLength < effectiveLength) {
            return true;
        }
        return false;
    }

    /**
     * 缓存一行数据
     *
     * @param cacheList
     */
    private void cacheLine(String[] cacheList) {
        if (cacheList.length != maxHeadSize) {
            String[] newCacheList = new String[maxHeadSize];
            for (int index = 0; index < newCacheList.length; index++) {
                if (cacheList.length <= index) {
                    newCacheList[index] = "";
                } else {
                    newCacheList[index] = cacheList[index];
                }
            }
            cacheList = newCacheList;
        }
        bufferRows.push(Arrays.asList(cacheList));
    }

    /**
     * 获取csv表头和对应索引的映射
     *
     * @return
     */
    public Map<String, Integer> getHeadMap() {
        return headMap;
    }

    public List<String> getHeads() {
        return new ArrayList<>(heads);
    }


    private List<String> poll() {
        return bufferRows.poll();
    }


    private List<String> peek() {
        if (bufferRows.size() == 0) {
            cacheRows(50);
        }
        return bufferRows.peek();
    }

    @Override
    public Iterator<List<String>> iterator() {
        return new CsvIterator(this);
    }

    private class CsvIterator implements Iterator<List<String>> {
        private CsvUtils resource;

        public CsvIterator(CsvUtils resource) {
            this.resource = resource;
        }

        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            return resource.peek() != null;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public List<String> next() {
            return resource.poll();
        }
    }
}
