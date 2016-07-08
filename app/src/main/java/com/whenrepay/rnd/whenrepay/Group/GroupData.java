package com.whenrepay.rnd.whenrepay.Group;

import java.io.Serializable;
import java.util.List;

/**
 * Created by RND on 2016-06-23.
 */
public class GroupData implements Serializable{
    public static final int INVALID_ID = -1;
    public int _id = INVALID_ID;
    private String groupName;
//    private String paymentType;
    private String paymentDate;
    private String account;
    private int moneyPerPerson;
    private int totalPaidMoney;
    private List<PersonData> personList;

    public String getGroupName() {
        return groupName;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public String getAccount() {
        return account;
    }

    public int getMoneyPerPerson() {
        return moneyPerPerson;
    }

    public int getTotalPaidMoney() {
        return totalPaidMoney;
    }

    public List<PersonData> getPersonList() {
        return personList;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setMoneyPerPerson(int moneyPerPerson) {
        this.moneyPerPerson = moneyPerPerson;
    }

    public void setTotalPaidMoney(int totalPaidMoney) {
        this.totalPaidMoney = totalPaidMoney;
    }

    public void setPersonList(List<PersonData> personList) {
        this.personList = personList;
    }
}
