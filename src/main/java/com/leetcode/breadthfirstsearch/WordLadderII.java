package com.leetcode.breadthfirstsearch;

import com.leetcode.datastructures.Node;

import java.util.*;

/**
 * Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from beginWord to endWord, such that:
 *
 * Only one letter can be changed at a time
 * Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 * Note:
 *
 * Return an empty list if there is no such transformation sequence.
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 * You may assume no duplicates in the word list.
 * You may assume beginWord and endWord are non-empty and are not the same.
 * Example 1:
 *
 * Input:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 *
 * Output:
 * [
 *   ["hit","hot","dot","dog","cog"],
 *   ["hit","hot","lot","log","cog"]
 * ]
 */
public class WordLadderII {

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> result = new ArrayList<List<String>>();

        HashSet<String> unvisited = new HashSet<>();
        unvisited.addAll(wordList);

        if(!unvisited.contains(endWord)) return result;

        LinkedList<Node> queue = new LinkedList<>();
        Node node = new Node(beginWord,0,null);
        queue.offer(node);

        int minLen = Integer.MAX_VALUE;
        while(!queue.isEmpty()){
            Node top = queue.poll();

            if(top.word.equals(endWord)){
                //add to result
                List<String> aResult = new ArrayList<>();
                Node p = top;
                while(p!=null){
                    aResult.add(p.word);
                    p = p.prev;
                }



                //stop if get shorter result
                if(top.depth<=minLen){
                    minLen=top.depth;
                    Collections.reverse(aResult);
                    result.add(aResult);
                }else{
                    return result;
                }
            }

            for(int i=0; i<top.word.length(); i++){
                char c = top.word.charAt(i);
                char[] arr = top.word.toCharArray();
                for(char j='a'; j <='z'; j++){
                    if(j==c){
                        continue;
                    }
                    arr[i]=j;
                    String t = new String(arr);

                    if(unvisited.contains(t)){
                        //System.out.println(top.word + ">" + t);
                        Node n=new Node(t,top.depth+1,top);
                        queue.offer(n);
                        //unvisited.remove(t);
                    }
                }
            }
        }

        return result;
    }
}
