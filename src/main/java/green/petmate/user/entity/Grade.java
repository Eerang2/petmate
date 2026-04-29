package green.petmate.user.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Grade {
    SILVER("silver"),     // 브론즈 집사 (기본, 무료)
    GOLD("gold"),       // 실버 집사
    DIAMOND("diamond")     // 실버 집사
    ;
    public final String type;

}
