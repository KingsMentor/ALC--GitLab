package com.appzonegroup.alc_gitlab.Models;

import java.util.ArrayList;

/**
 * Created by zone2 on 3/6/17.
 */

public class GitUserRequestData {
    int totalCount;
    boolean completeResult;
    ArrayList<GitUser> gitUsers;

    public GitUserRequestData(int totalCount, boolean completeResult) {
        this.totalCount = totalCount;
        this.completeResult = completeResult;
        gitUsers = new ArrayList<>();
    }

    public int getTotalCount() {
        return this.totalCount;
    }

    public GitUserRequestData setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        return this;
    }

    public boolean isCompleteResult() {
        return this.completeResult;
    }

    public GitUserRequestData setCompleteResult(boolean completeResult) {
        this.completeResult = completeResult;
        return this;
    }

    public ArrayList<GitUser> getGitUsers() {
        return this.gitUsers;
    }

    public GitUserRequestData setGitUsers(ArrayList<GitUser> gitUsers) {
        this.gitUsers = gitUsers;
        return this;
    }

    public GitUserRequestData addGitUser(GitUser gitUser) {
        this.gitUsers.add(gitUser);
        return this;
    }
}
