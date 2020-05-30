package com_1.service;

import com_1.domain.Account;

import java.util.List;

/**
 * 账户的业务层接口
 */
public interface IAccountService {
    // 查询所有
    List<Account>findAllAccount();
    // 查询一个
    Account findAccountById(Integer AccountId);
    // 保存
    void saveAccount(Account account);
    // 更新
    void updateAccount(Account account);
    // 删除
    void deleteAccount(Integer accountId);

    /**
     * 转账操作
     * @param sourceName 转出账户名称
     * @param targetName 装入账户名称
     * @param money 转账金额
     */
    void transfer(String sourceName, String targetName, float money);



}
