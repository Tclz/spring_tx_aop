package com.service.impl;

import com.dao.IAccountDao;
import com.domain.Account;
import com.service.IAccountService;

import java.util.List;

/**
 * 事务的控制应该在业务层
 *
 * 对繁杂的事务代码进行简化
 */

public class AccountServiceImpl implements IAccountService {

    // 业务层调用持久层
    private  IAccountDao accountDao;

    public void setAccountDao(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }


    public List<Account> findAllAccount() {

            return accountDao.findAllAccount();


    }

    public Account findAccountById(Integer AccountId) {

            return accountDao.findAccountById(AccountId);

    }

    public void saveAccount(Account account) {
            accountDao.saveAccount(account);
    }

    public void updateAccount(Account account) {

            accountDao.updateAccount(account);
    }

    public void deleteAccount(Integer accountId) {

            accountDao.deleteAccount(accountId);
    }

    public void transfer(String sourceName, String targetName, float money) {

            // 2.1根据名称查询转出账户
            Account source = accountDao.findAccountByName(sourceName);
            // 2.2根据名称查询转入账户
            Account target = accountDao.findAccountByName(targetName);
            //2.3转出账户减钱
            source.setMoney(source.getMoney()-money);
            //2.4转入账户加钱
            target.setMoney(target.getMoney()+money);
            // 2.5更新转出账户
            accountDao.updateAccount(source);
            // 测试动态代理是否正常工作
            //int i = 1/0;
            accountDao.updateAccount(target);


    }
}
