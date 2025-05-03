package com.houserent.view;

import com.houserent.domain.House;
import com.houserent.service.HouseService;
import com.houserent.utils.Utility;

import java.util.stream.Stream;

/**
 * 1.显示界面
 * 2.接收用户的输入
 * 3.调用HouseService完成对房屋信息的各种操作
 */
public class HouseView {

    private boolean loop = true; //控制显示菜单
    private char key = ' '; //接受用户选择
    private HouseService houseService = new HouseService(10); //设置数组大小为10

    //根据id修改房屋信息
    public void updateHouse() {
        System.out.println("-------------修改房屋信息-------------");
        System.out.print("请输入待修改房屋的编号(-1退出)：");
        int updateId = Utility.readInt();
        if (updateId == -1) {
            System.out.println("-------------放弃房屋信息-------------");
            return;
        }

        //查找对象
        House updateHouse = houseService.findById(updateId);
        if (updateHouse == null) {
            System.out.println("------------没有该房屋信息------------");
            return;
        }

        System.out.print("姓名(" + updateHouse.getName() + ")：");
        updateHouse.setName(Utility.readString(8, updateHouse.getName()));
        System.out.print("电话(" + updateHouse.getPhone() + ")：");
        updateHouse.setPhone(Utility.readString(12, updateHouse.getPhone()));
        System.out.print("地址(" + updateHouse.getAddress() + ")：");
        updateHouse.setAddress(Utility.readString(16, updateHouse.getAddress()));
        System.out.print("月租(" + updateHouse.getRent() + ")：");
        updateHouse.setRent(Utility.readInt(updateHouse.getRent()));
        System.out.print("状态(" + updateHouse.getState() + ")：");
        updateHouse.setState(Utility.readString(3, updateHouse.getState()));

        System.out.println("-----------修改房屋信息成功-----------");
    }

    //根据id查找房屋信息
    public void findHouse() {
        System.out.println("--------------------查找房屋--------------------");
        System.out.print("请输入要查找的房屋编号：");
        int findId = Utility.readInt();
        House house = houseService.findById(findId);
        if (house != null) {
            System.out.println("-----------------房屋信息已找到-----------------");
            System.out.println(house);
            System.out.println("------------------------------------------------");
        } else {
            System.out.println("-----------------房屋信息不存在-----------------");
        }
    }

    //编写退出系统时二次确认
    public void exit() {
        char c = Utility.readConfirmSelection();
        if (c == 'Y') {
            System.out.println("退出系统");
            loop = false;
        }
    }

    //编写delHouse接收输入的id，调用del方法
    public void delHouse() {
        System.out.println("----------------删除房屋----------------");
        System.out.print("请输入要删除的房屋的编号(-1退出)：");
        int delId = Utility.readInt();
        if (delId == -1) {
            System.out.println("--------------放弃删除房屋--------------");
            return;
        }
        char choice = Utility.readConfirmSelection();
        if (choice == 'Y') {
            if (houseService.del(delId)) {
                System.out.println("----------------删除成功----------------");
            } else {
                System.out.println("--------房屋编号不存在，删除失败--------");
            }
        } else {
            System.out.println("--------------放弃删除房屋--------------");
        }
    }

    //编写addHouse接收输入，创建House对象，调用add方法
    public void addHouse() {
        System.out.println("------添加房屋------");
        System.out.print("姓名：");
        String name = Utility.readString(8);
        System.out.print("电话：");
        String phone = Utility.readString(12);
        System.out.print("地址：");
        String address = Utility.readString(16);
        System.out.print("月租：");
        int rent = Utility.readInt();
        System.out.print("状态：");
        String state = Utility.readString(3);
        //创建一个新的House对象，注意id是系统分配的
        House newHouse = new House(0, name, phone, address, rent, state);
        if (houseService.add(newHouse)) {
            System.out.println("------添加成功------");
        } else {
            System.out.println("------添加失败------");
        }
    }

    //编写listHouses显示房屋列表
    public void listHouses() {
        System.out.println("--------------------房屋列表--------------------");
        System.out.println("编号\t房主\t电话\t地址\t月租\t状态");
        House[] houses = houseService.list(); //得到所有房屋信息
        for (int i = 0; i < houses.length; i++) {
            if (houses[i] == null)
                continue;
            System.out.println(houses[i]);
        }
        System.out.println("----------------房屋列表显示完毕----------------");
    }

    //显示主菜单
    public void mainMenu() {
        do {
            System.out.println("========== 房屋出租系统 ==========");
            System.out.println("=          1.新增房屋            =");
            System.out.println("=          2.查找房屋            =");
            System.out.println("=          3.删除房屋            =");
            System.out.println("=        4.修改房屋信息          =");
            System.out.println("=          5.房屋列表            =");
            System.out.println("=          6.退出系统            =");
            System.out.println("==================================");

            System.out.print("请输入你的选择(1-6)：");
            key = Utility.readChar();
            switch (key) {
                case '1':
                    addHouse();
                    break;
                case '2':
                    findHouse();
                    break;
                case '3':
                    delHouse();
                    break;
                case '4':
                    updateHouse();
                    break;
                case '5':
                    listHouses();
                    break;
                case '6':
                    exit();
                    break;
            }
        } while (loop);
    }
}
