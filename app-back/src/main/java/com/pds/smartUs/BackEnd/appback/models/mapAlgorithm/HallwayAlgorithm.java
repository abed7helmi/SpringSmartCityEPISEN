package com.pds.smartUs.BackEnd.appback.models.mapAlgorithm;


import com.sun.source.tree.Tree;

import java.util.*;

public class HallwayAlgorithm {

    public static Map<Integer,List<TreeNode>> paths;
    static int k;

    public static void init(){
        paths = new HashMap<>();
        k=0;
    }

    public static List<TreeNode> getBestPath(){
        int min=paths.get(0).size();
        List<TreeNode> path= paths.get(0);
        for(int n:paths.keySet()){
            if(paths.get(n).size()<min){
                min= paths.get(n).size();
                path = paths.get(n);
            }
        }
        return path;
    }
    public static void printNAryTree(TreeNode root){
        if(root == null) return;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            int len = queue.size();
            for(int i=0;i<len;i++) { // so that we can reach each level
                TreeNode node = queue.poll();
                System.out.print(node.val + " ");
                for (TreeNode item : node.children) { // for-Each loop to iterate over all children
                    queue.offer(item);
                }
            }
            System.out.println();
        }
    }
    public static boolean findTreeNode(List<TreeNode> list, String value){
        for(TreeNode node: list){
            if(node.getVal().equals(value)){
                return true;
            }
        }
        return false;
    }
    public static List<TreeNode> findPath(TreeNode node, List<TreeNode> path, String valueToFind){

        if(node.isLeaf() && !node.getVal().equals(valueToFind)){
            path.remove(node);
            return path;
        }

        if(node.getVal().equals(valueToFind)){
            return path;
        }

        if(findTreeNode(path,valueToFind)){
            path.remove(node);
            return path;
        }
        else{

            for(TreeNode nodeToExplore: node.getChildren()){
                path.add(nodeToExplore);
                path = findPath(nodeToExplore,path,valueToFind);
            }

            if(findTreeNode(path,valueToFind)){
                return path;
            }

            path.remove(node);

        }

        return path;

    }
    public static void findPaths(TreeNode node, List<TreeNode> path, String valueToFind){

        if(node.isLeaf() && !node.getVal().equals(valueToFind)){
            path.remove(node);
            return;
        }

        if(node.getVal().equals(valueToFind)){
            List<TreeNode> foundPath = new LinkedList<>();
            foundPath.addAll(path);
            paths.put(k,foundPath);
            k++;
            path.remove(node);
            return;
        }

        else{

            for(TreeNode nodeToExplore: node.getChildren()){
                path.add(nodeToExplore);
                findPaths(nodeToExplore,path,valueToFind);
            }

            path.remove(node);

        }
    }

    public static void main(String[] args){
        TreeNode root = new TreeNode("H1","origin","O");
        TreeNode h7=new TreeNode("H7","I1","N");
        TreeNode h5=new TreeNode("H5","I1","S");
        TreeNode h4=new TreeNode("H4","I1","E");
        TreeNode h8=new TreeNode("H8","I4","N");
        TreeNode h6=new TreeNode("H6","I4","S");
        TreeNode h3=new TreeNode("H3","I2","E");
        TreeNode h2=new TreeNode("H2","I2","O");
        TreeNode h8_2=new TreeNode("H8","I3","S");
        root.addChild(h7);
        root.addChild(h5);
        root.addChild(h4);
        h4.addChild(h8);
        h4.addChild(h6);
        h7.addChild(h3);
        h7.addChild(h2);
        h3.addChild(h8_2);


        printNAryTree(root);
        List<TreeNode> path= new LinkedList<>();
        path.add(root);
        findPaths(root,path,"H6");


        for(int n:paths.keySet()){
            System.out.println("\n--");
            for(TreeNode node: paths.get(n)){
                System.out.print( node.getIntersection()+" -> "+node.getVal()+ " -> ");
            }
        }
    }
}
