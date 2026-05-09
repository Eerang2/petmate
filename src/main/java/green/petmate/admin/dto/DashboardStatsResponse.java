package green.petmate.admin.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DashboardStatsResponse {
    private long totalUsers;
    private long totalPosts;
    private long totalMatches;
    private long totalRevenue;
    private long todaySignups;
    private long todayMatches;
    private long todayRevenue;
}
