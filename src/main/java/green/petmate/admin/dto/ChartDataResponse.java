package green.petmate.admin.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ChartDataResponse {
    private List<String> labels;
    private List<Long> signups;
    private List<Long> matches;
    private List<Long> revenue;
    private List<Long> posts;
}
