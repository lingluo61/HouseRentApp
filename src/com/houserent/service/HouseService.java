package com.houserent.service;

import com.houserent.domain.House;

/**
 * 定义House[]，保存House对象
 * 1.响应HouseView的调用
 * 2.完成对房屋信息的各种操作
 */
public class HouseService {

    private House[] houses; //保存House对象
    private int HouseCount = 1; //记录房屋数量
    private int idCounter = 1; //记录id增长

    //构造器
    public HouseService(int size) {
        houses = new House[size];
        houses[0] = new House(1, "竹笙", "193", "广东", 2000, "未出租");
    }

    //findById方法，返回一个House对象或null
    public House findById(int findId) {
        for (int i = 0; i < HouseCount; i++) {
            if (findId == houses[i].getId()) {
                return houses[i];
            }
        }
        return null;
    }

    //del方法，删除一个房屋信息，返回boolean
    public boolean del(int delId) {
        //找到要删除房屋信息的下标
        int index = -1;
        for (int i = 0; i < HouseCount; i++) {
            if (delId == houses[i].getId()) {
                index = i; //记录待删除id
            }
        }
        //假如不存在该id
        if (index == -1) {
            return false;
        }
        //如果找到
        for (int i = index; i < HouseCount - 1; i++) {
            houses[i] = houses[i + 1];
        }
        //将当前房屋信息最后一位置空
        houses[--HouseCount] = null;
        return true;
    }

    //add方法，添加新对象，返回boolean
    public boolean add(House newHouse) {
        //暂不考虑数组扩容问题
        if (HouseCount == houses.length) {
            System.out.println("数组已满，无法继续添加");
            return false;
        }
        //把newHouse对象加入，新增一个房屋
        houses[HouseCount++] = newHouse;
        newHouse.setId(++idCounter);
        return true;
    }

    //list方法，返回houses
    public House[] list() {
        return houses;
    }
}
