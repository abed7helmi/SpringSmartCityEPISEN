package com.pds.smartUs.BackEnd.appback.models.mapAlgorithm;

import java.util.LinkedList;
import java.util.List;

public class TreeNode {
    String val;
    String intersection;
    String direction;
    int id_hallway;
    int inter_x;
    int inter_y;
    List<TreeNode> children = new LinkedList<>();

    public TreeNode(String data,String intersection,String direction){
        val = data;
        this.intersection=intersection;
        this.direction=direction;
    }
    public TreeNode(String data,String intersection,String direction, int id_hallway, int x,int y){
        val = data;
        this.intersection=intersection;
        this.direction=direction;
        this.id_hallway=id_hallway;
        this.inter_x=x;
        this.inter_y=y;
    }
    public TreeNode(String data,List<TreeNode> children){
        val = data;
        this.children = children;
    }

    public int getInter_x() {
        return inter_x;
    }

    public void setInter_x(int inter_x) {
        this.inter_x = inter_x;
    }

    public int getInter_y() {
        return inter_y;
    }

    public void setInter_y(int inter_y) {
        this.inter_y = inter_y;
    }

    public int getId_hallway() {
        return id_hallway;
    }

    public void setId_hallway(int id_hallway) {
        this.id_hallway = id_hallway;
    }

    public TreeNode getChild(int x){
        return children.get(x);
    }
    public void addChild(TreeNode child){
        children.add(child);
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }
    public boolean isLeaf(){
        return children.size() == 0;
    }

    public String getIntersection() {
        return intersection;
    }

    public void setIntersection(String intersection) {
        this.intersection = intersection;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
