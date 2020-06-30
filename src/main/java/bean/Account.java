package bean;

import java.util.Comparator;

public class Account implements Comparable<Account> {
    private int accountID;
    private int account;
    private int userID;

    public Account(int accountID, int account, int userID) {
        this.accountID = accountID;
        this.account = account;
        this.userID = userID;
    }


    @Override
    public String toString() {
        return "Account{" +
                "accountID=" + accountID +
                ", account=" + account +
                ", userID=" + userID +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account1 = (Account) o;

        if (accountID != account1.accountID) return false;
        if (account != account1.account) return false;
        return userID == account1.userID;
    }

    @Override
    public int hashCode() {
        int result = accountID;
        result = 31 * result + account;
        result = 31 * result + userID;
        return result;
    }

    @Override
    public int compareTo(Account o) {
        if (this.getAccount()==o.getAccount()){
            return 0;
        }
        else if (this.getAccount()>o.getAccount()){
            return 1;
        }
        else return -1;
    }


    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
