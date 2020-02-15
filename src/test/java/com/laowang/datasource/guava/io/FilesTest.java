package com.laowang.datasource.guava.io;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import com.google.common.io.Files;
import com.google.common.io.LineProcessor;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Test;

public class FilesTest {

  private static final String SOURCE_FILE = "src/test/resources/io/source.txt";
  private static final String TARGET_FILE = "D:\\test\\悼李文亮.txt";

  @Test
  public void testCopyFileWithGuava() throws IOException {
    File targetFile = new File(TARGET_FILE);
    File sourceFile = new File(SOURCE_FILE);
    Files.copy(new File(SOURCE_FILE), targetFile);
    assertThat(targetFile.exists(), equalTo(true));
    // 鉴定2个文件是不是一样
    HashCode targetCode = Files.asByteSource(targetFile).hash(Hashing.sha256());
    HashCode sourceCode = Files.asByteSource(sourceFile).hash(Hashing.sha256());
    assertThat(targetCode,equalTo(sourceCode));
  }

  @Test
  public void testCopyFileWithJDKNio2() throws IOException {
    java.nio.file.Files.copy(
        Paths.get(SOURCE_FILE),
        Paths.get(TARGET_FILE),
        StandardCopyOption.REPLACE_EXISTING
    );
    File targetFile = new File(TARGET_FILE);

    assertThat(targetFile.exists(), equalTo(true));
  }

  @Test
  public void testMoveFile() throws IOException {
    try {
      //prepare for data.
      Files.move(new File(SOURCE_FILE), new File(TARGET_FILE));
      assertThat(new File(TARGET_FILE).exists(), equalTo(true));
      assertThat(new File(SOURCE_FILE).exists(), equalTo(false));
    } finally {
      Files.move(new File(TARGET_FILE), new File(SOURCE_FILE));
    }
  }

  @Test
  public void testToString() throws IOException {

    final String expectedString = "悼念李文亮\n"
        + "作者/余秀华\n"
        + "\n"
        + "且安息！\n"
        + "没有比“以言获罪”更厉害的病毒\n"
        + "没有比黑白不分更丑陋的人间\n"
        + "\n"
        + "且安息！\n"
        + "长江之水载舟也覆舟\n"
        + "黄河之浪渡人也渡鬼\n"
        + "\n"
        + "且安息！\n"
        + "且允许我苟且偷生\n"
        + "还允许我长歌当哭\n"
        + "\n"
        + "我们不怕死\n"
        + "我们怕死于非命\n"
        + "你死了，我的命非命\n"
        + "\n"
        + "如果天堂还有病毒\n"
        + "如果你再喊一声\n"
        + "你会去向何处？\n"
        + "\n"
        + "我希望收容你的地方\n"
        + "还是有人\n"
        + "说着汉语";

    // 这个方法好哇
    List<String> strings = Files.readLines(new File(SOURCE_FILE), Charsets.UTF_8);

    String result = Joiner.on("\n").join(strings);
    assertThat(result, equalTo(expectedString));
  }

  @Test
  public void testToProcessString() throws IOException {
    final LineProcessor<List<String>> processor = new LineProcessor<List<String>>() {

      private List<String> poemList = new ArrayList<>();

      @Override
      public boolean processLine(String line) throws IOException {
        // 这个方法返回true就是要处理, 返回false就是不处理.
        poemList.add(line.length() + " you are a hero. I will always remember you. \n");
        return true;
      }

      @Override
      public List<String> getResult() {
        return poemList;
      }
    };
    // 先读, 然后事后处理:
    final List<String> result = Files.asCharSource(new File(SOURCE_FILE), Charsets.UTF_8)
        .readLines(processor);
    System.out.println(result);
  }

  @Test
  public void testFileMd5() {

  }


  @After
  public void tearDown() {
    File target = new File(TARGET_FILE);
    if (target.exists()) {
      target.delete();
    }
  }
}
