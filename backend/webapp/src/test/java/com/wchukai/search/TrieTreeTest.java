package com.wchukai.search;

import com.wchukai.search.TrieTree;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author chukai
 */
public class TrieTreeTest {
    @Test
    public void test() throws IOException {
        List<String> list=new ArrayList<>();
        String word=null;
        BufferedReader bufferedReader=new BufferedReader(new FileReader(this.getClass().getResource("/").getPath()+"word.txt"));
        while((word=bufferedReader.readLine())!=null){
            list.add(word);
        }
        TrieTree trieTree = new TrieTree();
        for(String s: list){
            trieTree.insert(s);
        }
        List<String> matching = trieTree.prefixeMatching("啊");
        matching.forEach(System.out::println);
        assertThat(matching).contains("啊呀","啊哟");
        trieTree.delete("啊呀");
        matching = trieTree.prefixeMatching("啊");
        matching.forEach(System.out::println);
        assertThat(matching).doesNotContain("啊呀").contains("啊哟");
    }

}