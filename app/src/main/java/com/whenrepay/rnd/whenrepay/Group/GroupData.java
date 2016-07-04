package com.whenrepay.rnd.whenrepay.Group;

import java.io.Serializable;
import java.util.List;

/**
 * Created by RND on 2016-06-23.
 */
public class GroupData implements Serializable{
    String groupName;
    String paymentType;
    String paymentDate;
    String account;
    int moneyPerPerson;
    int totalPaidMoney;
    List<PersonData> personList;
}
