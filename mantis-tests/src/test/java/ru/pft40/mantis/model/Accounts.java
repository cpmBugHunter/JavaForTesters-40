package ru.pft40.mantis.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Accounts extends ForwardingSet<AccountData> {

    private Set<AccountData> delegate;

    public Accounts(Accounts groups) {
        this.delegate = new HashSet<AccountData>(groups.delegate);
    }

    public Accounts() {
        this.delegate = new HashSet<AccountData>();
    }

    public Accounts(Collection<AccountData> groups) {
        this.delegate = new HashSet<AccountData>(groups);
    }

    @Override
    protected Set<AccountData> delegate() {
        return delegate;
    }
}
