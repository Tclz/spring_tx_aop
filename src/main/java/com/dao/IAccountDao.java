package com.dao;

import com.domain.Account;

import java.util.List;

public interface IAccountDao {

    // 查询所有
    List<Account> findAllAccount();
    // 查询一个
    Account findAccountById(Integer AccountId);
    // 保存
    void saveAccount(Account account);
    // 更新
    void updateAccount(Account account);
    // 删除
    void deleteAccount(Integer accountId);

    /**
     * 根据名称查询账户
     * @param accountName
     * @return 如果有唯一的结果就返回，如果没有结果就返回null,如果结果集超过一个就抛出异常
     */
    Account findAccountByName(String accountName);
}
