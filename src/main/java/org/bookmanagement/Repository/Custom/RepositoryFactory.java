package org.bookmanagement.Repository.Custom;

import org.bookmanagement.Repository.SuperRepository;

public class RepositoryFactory {
    private static RepositoryFactory repositoryFactory;
    private RepositoryFactory(){}

    public static RepositoryFactory getDaoFactory() {
        return  (repositoryFactory == null) ? repositoryFactory = new RepositoryFactory() : repositoryFactory;
    }

    public enum DaoType{
        User ,admin, Books, Branch,Book_Transaction, BorrowBook, Pending
    }

    public SuperRepository getDao(DaoType daoType ){
        switch ( daoType ){
            case  User:
                return new UserRepositoryImpl();

            case admin:
                return new AdminRepositoryImpl();
            case Books:
                return new BookRepositoryImpl();
            case Branch:
                return new BranchRepositoryImpl();
            case Book_Transaction:
                return new BookTransactionRepositoryImpl();
                case BorrowBook:
                    return new BorrowBookRepositoryImpl();
            case Pending:
                return new CustomRepositoryImpl();

            default:
                return null;
        }
    }
}
