package dao;

import dao.UserOperate;

public class Test {
    public static void main(String[] args) {
        UserOperate userOperate = new UserOperate();
        userOperate.connect2DB();
        userOperate.userFindAll();
        userOperate.closeDB();
    }
}
