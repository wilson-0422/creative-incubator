package com.creative.incubator.dto;

public class DashboardStats {

    private long totalProposals;
    private long pendingReviews;
    private long activeProjects;
    private long completedTasks;
    private long archivedProjects;
    private long totalUsers;

    public DashboardStats() {}

    public DashboardStats(long totalProposals, long pendingReviews, long activeProjects,
                          long completedTasks, long archivedProjects, long totalUsers) {
        this.totalProposals = totalProposals;
        this.pendingReviews = pendingReviews;
        this.activeProjects = activeProjects;
        this.completedTasks = completedTasks;
        this.archivedProjects = archivedProjects;
        this.totalUsers = totalUsers;
    }

    public long getTotalProposals() { return totalProposals; }
    public void setTotalProposals(long totalProposals) { this.totalProposals = totalProposals; }

    public long getPendingReviews() { return pendingReviews; }
    public void setPendingReviews(long pendingReviews) { this.pendingReviews = pendingReviews; }

    public long getActiveProjects() { return activeProjects; }
    public void setActiveProjects(long activeProjects) { this.activeProjects = activeProjects; }

    public long getCompletedTasks() { return completedTasks; }
    public void setCompletedTasks(long completedTasks) { this.completedTasks = completedTasks; }

    public long getArchivedProjects() { return archivedProjects; }
    public void setArchivedProjects(long archivedProjects) { this.archivedProjects = archivedProjects; }

    public long getTotalUsers() { return totalUsers; }
    public void setTotalUsers(long totalUsers) { this.totalUsers = totalUsers; }
}
