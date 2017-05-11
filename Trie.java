package database;
import java.util.*;
class TrieNode {
    TrieNode[] arr;
    boolean isEnd;
    public TrieNode() {
        this.arr = new TrieNode[26];
    }
 
}
 
public class Trie {
    private TrieNode root;
 
    public Trie() {
        root = new TrieNode();
    }
 
    public void insert(String word) {
        TrieNode pNode = root;
        for(int i=0; i<word.length(); i++){
            char c = word.charAt(i);
            int index = c-'a';
            if(index > 0)
            if(pNode.arr[index]==null){
                TrieNode temp = new TrieNode();
                pNode.arr[index]=temp;
                pNode = temp;
            }else{
            	pNode=pNode.arr[index];
            }
        }
        pNode.isEnd=true;
    }
 
    public boolean search(String word) {
        TrieNode pSearchedNode = searchNode(word);
        if(pSearchedNode == null){
            return false;
        }else{
            if(pSearchedNode.isEnd)
                return true;
        }
 
        return false;
    }
 
    
 
    public TrieNode searchNode(String s){
        TrieNode p = root;
        for(int i=0; i<s.length(); i++){
            char c= s.charAt(i);
            int index = c-'a';
            if(p.arr[index]!=null){
                p = p.arr[index];
            }else{
                return null;
            }
        }
 
        if(p==root)
            return null;
 
        return p;
    }
}



